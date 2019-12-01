package uz.umidbek.contact_dins.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.umidbek.contact_dins.entity.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByPhoneNumberIgnoreCaseContainingOrHomeNumberIgnoreCaseContainingOrOfficeNumberIgnoreCaseContaining(String phoneNumber, String homeNumber, String officeNumber);
}
