package uz.umidbek.contact_dins.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDTO;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDetailsDTO;
import uz.umidbek.contact_dins.entity.PhoneBook;
import uz.umidbek.contact_dins.exceptions.NotFoundException;
import uz.umidbek.contact_dins.repository.PhoneBookRepository;
import uz.umidbek.contact_dins.repository.UserRepository;
import uz.umidbek.contact_dins.service.ContactService;
import uz.umidbek.contact_dins.service.PhoneBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

    @Autowired
    private PhoneBookRepository repository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(PhoneBookDTO dto) {
        PhoneBook book = new PhoneBook();
        BeanUtils.copyProperties(dto, book, "user", "userId");
        book.setUser(userRepository.findById(dto.getUserId()).get());
        repository.save(book);
    }

    @Override
    public void update(Long id, PhoneBookDTO dto) {
        PhoneBook phoneBook = repository.findById(id).orElseThrow(() -> new NotFoundException("Phone book not found !"));
        BeanUtils.copyProperties(dto, phoneBook);
        repository.save(phoneBook);
    }

    @Override
    public void delete(Long id) {
        PhoneBook phoneBook = repository.findById(id).orElseThrow(() -> new NotFoundException("Phone book not found !"));
        repository.delete(phoneBook);
    }

    @Override
    public PhoneBookDetailsDTO findOne(Long id) {
        PhoneBook phoneBook = repository.findById(id).orElseThrow(() -> new NotFoundException("Phone book not found !"));
        return convertToDTO(phoneBook);
    }

    @Override
    public List<PhoneBookDetailsDTO> findAll() {
        List<PhoneBook> list = repository.findAll();
        List<PhoneBookDetailsDTO> dtoList = new ArrayList<>();
        list.parallelStream().forEach(phoneBook -> dtoList.add(convertToDTO(phoneBook)));
        return dtoList;
    }

    @Override
    public List<PhoneBookDetailsDTO> findByUserId(Long id) {
        List<PhoneBook> phoneBooks = repository.findByUserId(id);
        List<PhoneBookDetailsDTO> dtoList = new ArrayList<>();
        phoneBooks.parallelStream().forEach(phoneBook -> dtoList.add(convertToDTO(phoneBook)));
        return dtoList;
    }

    @Override
    public PhoneBookDetailsDTO convertToDTO(PhoneBook phoneBook) {
        PhoneBookDetailsDTO dto = new PhoneBookDetailsDTO();
        dto.setContacts(new ArrayList<>());
        BeanUtils.copyProperties(phoneBook, dto, "contacts");
        phoneBook.getContacts().stream()
                .filter(Objects::nonNull)
                .map(contact -> contactService.convertToDTO(contact))
                .collect(Collectors.toCollection(dto::getContacts));
        return dto;
    }
}
