package uz.umidbek.contact_dins.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.umidbek.contact_dins.dto.contact.ContactDTO;
import uz.umidbek.contact_dins.dto.contact.ContactDetailsDTO;
import uz.umidbek.contact_dins.entity.Contact;
import uz.umidbek.contact_dins.exceptions.NotFoundException;
import uz.umidbek.contact_dins.repository.ContactRepository;
import uz.umidbek.contact_dins.repository.PhoneBookRepository;
import uz.umidbek.contact_dins.service.ContactService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    @Override
    public void create(ContactDTO dto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(dto, contact, "phone_book_id");
        contact.setPhoneBook(phoneBookRepository.findById(dto.getPhoneBookId()).get());
        repository.save(contact);
    }

    @Transactional
    @Override
    public void update(Long id, ContactDTO dto) {
        Contact contact = repository.findById(id).orElseThrow(() -> new NotFoundException("Contact not found!"));

        contact.setPhoneNumber(dto.getPhoneNumber() == null ? contact.getPhoneNumber() : dto.getPhoneNumber());
        contact.setHomeNumber(dto.getHomeNumber() == null ? contact.getHomeNumber() : dto.getHomeNumber());
        contact.setOfficeNumber(dto.getOfficeNumber() == null ? contact.getOfficeNumber() : dto.getOfficeNumber());
        contact.setFirstName(dto.getFirstName() == null ? contact.getFirstName() : dto.getFirstName());
        contact.setLastName(dto.getLastName() == null ? contact.getLastName() : dto.getLastName());
        repository.save(contact);
    }

    @Override
    public void delete(Long id) {
        Contact contact = repository.findById(id).orElseThrow(() -> new NotFoundException("Contact not found!"));
        repository.delete(contact);
    }

    @Override
    public ContactDetailsDTO findOne(Long id) {
        Contact contact = repository.findById(id).orElseThrow(() -> new NotFoundException("Contact not found!"));
        return convertToDTO(contact);
    }

    @Override
    public List<ContactDetailsDTO> findAll() {
        List<Contact> contacts = repository.findAll();
        return contacts.parallelStream()
                .filter(Objects::nonNull)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDetailsDTO convertToDTO(Contact contact) {
        ContactDetailsDTO detailsDTO = new ContactDetailsDTO();
        BeanUtils.copyProperties(contact, detailsDTO);
        detailsDTO.setPhoneBookId(contact.getPhoneBook().getId());
        return detailsDTO;
    }

    @Override
    public List<ContactDetailsDTO> findByNumber(String number) {
        List<Contact> contacts =
                repository.findByPhoneNumberIgnoreCaseContainingOrHomeNumberIgnoreCaseContainingOrOfficeNumberIgnoreCaseContaining(number, number, number);
        return contacts.parallelStream()
                .filter(Objects::nonNull)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
