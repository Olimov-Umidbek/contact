package uz.umidbek.contact_dins;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.umidbek.contact_dins.entity.Contact;
import uz.umidbek.contact_dins.entity.PhoneBook;
import uz.umidbek.contact_dins.entity.User;
import uz.umidbek.contact_dins.repository.ContactRepository;
import uz.umidbek.contact_dins.repository.PhoneBookRepository;
import uz.umidbek.contact_dins.repository.UserRepository;

import java.util.List;

@Configuration
@SpringBootApplication
public class ContactApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactApplication.class, args);
    }

    /**
     * Inserting sample data to database
     **/
    @Bean
    public CommandLineRunner run(
            UserRepository userRepository, PhoneBookRepository phoneBookRepository, ContactRepository contactRepository) throws Exception {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) return (String[] args) -> {
        };

        return (String[] args) -> {
            userRepository.save(new User("arion", "umi1d3k"));
            for (int i = 0; i < 4; i++) {
                User user = new User();
                user.setFirstName("Um1db3k" + i);
                userRepository.save(user);

                for (int j = 0; j < 2; j++) {
                    PhoneBook phoneBook = new PhoneBook("My contact " + j, user);
                    phoneBookRepository.save(phoneBook);

                    for (int k = 0; k < 10; k++) {
                        Contact contact = new Contact();
                        contact.setFirstName("Umidbek" + k);
                        contact.setLastName("Olimov" + k);
                        contact.setPhoneNumber("+79959870470");
                        contact.setEmail("umidbek1477@gmail.com");
                        contact.setPhoneBook(phoneBook);
                        contactRepository.save(contact);
                    }

                }
            }
        };
    }
}

