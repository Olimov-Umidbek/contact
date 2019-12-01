package uz.umidbek.contact_dins.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.umidbek.contact_dins.entity.PhoneBook;

import java.util.List;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {

    List<PhoneBook> findByUserId(Long id);
}
