package uz.umidbek.contact_dins.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "phone_book")
@AllArgsConstructor
@NoArgsConstructor
public class PhoneBook implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, updatable = false, referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "phoneBook", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Contact> contacts;

    public PhoneBook(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
