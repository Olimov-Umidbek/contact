package uz.umidbek.contact_dins.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.umidbek.contact_dins.dto.user.UserDTO;
import uz.umidbek.contact_dins.dto.user.UserDetailsDTO;
import uz.umidbek.contact_dins.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody UserDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok("User updated !");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("User deleted !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDetailsDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<UserDetailsDTO>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

}
