package uz.umidbek.contact_dins.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(name = "phone_number", length = 13, nullable = false)
    private String phoneNumber;

    @Column(name = "home_number", length = 13)
    private String homeNumber;

    @Column(name = "office_number", length = 13)
    private String officeNumber;

    @Email
    @Column
    private String email;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "phone_book_id", nullable = false, updatable = false, referencedColumnName = "id")
    private PhoneBook phoneBook;
}
