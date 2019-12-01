package uz.umidbek.contact_dins.dto.phonebook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneBookDTO {

    private Long userId;
    private String name;
}
