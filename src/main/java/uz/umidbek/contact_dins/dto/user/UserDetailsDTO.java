package uz.umidbek.contact_dins.dto.user;

import lombok.Data;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDetailsDTO;

import java.util.List;

@Data
public class UserDetailsDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private List<PhoneBookDetailsDTO> phoneBookDetailsDTOList;

}

