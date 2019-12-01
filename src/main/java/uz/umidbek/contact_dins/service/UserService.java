package uz.umidbek.contact_dins.service;

import uz.umidbek.contact_dins.dto.user.UserDTO;
import uz.umidbek.contact_dins.dto.user.UserDetailsDTO;

import java.util.List;

public interface UserService {

    String create(UserDTO dto);

    void update(Long id, UserDTO dto);

    void delete(Long id);

    UserDetailsDTO findOne(Long id);

    List<UserDetailsDTO> findAll();

    List<UserDetailsDTO> findByName(String name);

}
