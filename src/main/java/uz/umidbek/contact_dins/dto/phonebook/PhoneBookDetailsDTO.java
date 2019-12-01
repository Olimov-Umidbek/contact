package uz.umidbek.contact_dins.dto.phonebook;

import lombok.Data;
import uz.umidbek.contact_dins.dto.contact.ContactDetailsDTO;

import java.util.List;

@Data
public class PhoneBookDetailsDTO {

    private Long id;
    private String name;
    private Long userId;
    private List<ContactDetailsDTO> contacts;

}
