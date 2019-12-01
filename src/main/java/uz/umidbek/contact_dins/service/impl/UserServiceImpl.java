package uz.umidbek.contact_dins.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.umidbek.contact_dins.dto.user.UserDTO;
import uz.umidbek.contact_dins.dto.user.UserDetailsDTO;
import uz.umidbek.contact_dins.entity.User;
import uz.umidbek.contact_dins.exceptions.NotFoundException;
import uz.umidbek.contact_dins.repository.UserRepository;
import uz.umidbek.contact_dins.service.PhoneBookService;
import uz.umidbek.contact_dins.service.UserService;
import uz.umidbek.contact_dins.status.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PhoneBookService phoneBookService;

    @Override
    public String create(UserDTO dto) {
        User user = new User();
        if (StringUtils.isEmpty(dto.getFirstName())) {
            return HttpStatus.FIRST_NAME_REQUIRED;
        }
        BeanUtils.copyProperties(dto, user);
        repository.save(user);
        return HttpStatus.OK;
    }

    @Override
    public void update(Long id, UserDTO dto) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        BeanUtils.copyProperties(dto, user);
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        repository.delete(user);
    }

    @Override
    public UserDetailsDTO findOne(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        UserDetailsDTO detailsDTO = new UserDetailsDTO();
        BeanUtils.copyProperties(user, detailsDTO);
        detailsDTO.setPhoneBookDetailsDTOList(phoneBookService.findByUserId(user.getId()));
        return detailsDTO;
    }

    //FIXME: 29, 23:36 We need to add phoneBookDetails dto to user details dto
    //FIXED: 30, 03:16

    @Override
    public List<UserDetailsDTO> findAll() {
        List<User> users = repository.findAll();
        List<UserDetailsDTO> detailsDTOList = new ArrayList<>();
        users.parallelStream().forEach(user -> {
            UserDetailsDTO dto = new UserDetailsDTO();

            BeanUtils.copyProperties(user, dto, "phoneBookDetailsDTOList");

            dto.setPhoneBookDetailsDTOList(
                    user.getPhoneBookList().stream()
                            .filter(Objects::nonNull)
                            .map(s -> phoneBookService.convertToDTO(s))
                            .collect(Collectors.toList()));
            detailsDTOList.add(dto);
        });
        return detailsDTOList;
    }

    @Override
    public List<UserDetailsDTO> findByName(String name) {

        List<User> users = repository.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(name, name);
        List<UserDetailsDTO> detailsDTOList = new ArrayList<>();
        users.parallelStream().forEach(user -> {
            UserDetailsDTO dto = new UserDetailsDTO();

            BeanUtils.copyProperties(user, dto, "phoneBookDetailsDTOList");
            dto.setPhoneBookDetailsDTOList(
                    user.getPhoneBookList().stream()
                            .filter(Objects::nonNull)
                            .map(s -> phoneBookService.convertToDTO(s))
                            .collect(Collectors.toList())
            );
            detailsDTOList.add(dto);
        });
        return detailsDTOList;
    }
}
