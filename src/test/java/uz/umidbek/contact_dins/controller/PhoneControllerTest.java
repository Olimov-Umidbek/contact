package uz.umidbek.contact_dins.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDetailsDTO;
import uz.umidbek.contact_dins.service.PhoneBookService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PhoneControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private PhoneController controller;

    @Autowired
    private PhoneBookService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCreatePhoneBookExpectStatusOk() throws Exception {
        mockMvc.perform(post("/phone/books/")
                .content("{\n" +
                        "\t\"name\":\"PhoneBook1\",\n" +
                        "\t\"userId\": 1\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePhoneBookExpectStatusOk() throws Exception {
        List<PhoneBookDetailsDTO> list = service.findAll();
        mockMvc.perform(put("/phone/books/" + list.get(1).getId())
                .content("{\n" +
                        "\t\"name\":\"My contacts\",\n" +
                        "\t\"userId\": 2\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindOnePhoneBookExpectStatusOk() throws Exception {
        List<PhoneBookDetailsDTO> list = service.findAll();
        mockMvc.perform(get("/phone/books/" + list.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAllPhoneBookExpectStatusOk() throws Exception {
        mockMvc.perform(get("/phone/books/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByUserIdPhoneBookExpectStatusOk() throws Exception {
        mockMvc.perform(get("/phone/books/findByUserId/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePhoneBookExpectStatusOk() throws Exception {
        List<PhoneBookDetailsDTO> list = service.findAll();
        mockMvc.perform(delete("/phone/books/" + list.get(list.size() - 1).getId()))
                .andExpect(status().isOk());
    }
}