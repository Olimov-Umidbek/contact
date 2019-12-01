package uz.umidbek.contact_dins.service;

import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDTO;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDetailsDTO;
import uz.umidbek.contact_dins.entity.PhoneBook;

import java.util.List;

public interface PhoneBookService {

    void create(PhoneBookDTO dto);

    void update(Long id, PhoneBookDTO dto);

    void delete(Long id);

    PhoneBookDetailsDTO findOne(Long id);

    List<PhoneBookDetailsDTO> findAll();

    List<PhoneBookDetailsDTO> findByUserId(Long id);

    PhoneBookDetailsDTO convertToDTO(PhoneBook phoneBook);

}
