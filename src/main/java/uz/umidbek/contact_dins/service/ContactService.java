package uz.umidbek.contact_dins.service;

import uz.umidbek.contact_dins.dto.contact.ContactDTO;
import uz.umidbek.contact_dins.dto.contact.ContactDetailsDTO;
import uz.umidbek.contact_dins.entity.Contact;

import java.util.List;

public interface ContactService {

    void create(ContactDTO dto);

    void update(Long id, ContactDTO dto);

    void delete(Long id);

    ContactDetailsDTO findOne(Long id);

    List<ContactDetailsDTO> findAll();

    ContactDetailsDTO convertToDTO(Contact contact);

    List<ContactDetailsDTO> findByNumber(String number);
}
