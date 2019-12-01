package uz.umidbek.contact_dins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.umidbek.contact_dins.dto.contact.ContactDTO;
import uz.umidbek.contact_dins.dto.contact.ContactDetailsDTO;
import uz.umidbek.contact_dins.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ContactDTO dto) {
        service.create(dto);
        return ResponseEntity.ok("Contact created !");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ContactDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok("Contact updated !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Contact deleted !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDetailsDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDetailsDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findByNumber/{number}")
    public ResponseEntity<List<ContactDetailsDTO>> findByNumber(@PathVariable String number) {
        return ResponseEntity.ok(service.findByNumber(number));
    }
}
