package uz.umidbek.contact_dins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDTO;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDetailsDTO;
import uz.umidbek.contact_dins.service.PhoneBookService;

import java.util.List;

@RestController
@RequestMapping("/phone/books")
public class PhoneController {

    @Autowired
    private PhoneBookService service;

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody PhoneBookDTO dto) {
        service.create(dto);
        return ResponseEntity.ok("Phone book created!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PhoneBookDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok("Phone book updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Phone book deleted!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneBookDetailsDTO> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PhoneBookDetailsDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<List<PhoneBookDetailsDTO>> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByUserId(id));
    }
}
