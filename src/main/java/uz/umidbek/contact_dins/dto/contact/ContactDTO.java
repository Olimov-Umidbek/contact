package uz.umidbek.contact_dins.dto.contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO {

    private Long phoneBookId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String homeNumber;
    private String officeNumber;
    private String email;
    private String description;
}
