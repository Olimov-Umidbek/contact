package uz.umidbek.contact_dins.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uz.umidbek.contact_dins.dto.contact.ContactDetailsDTO;
import uz.umidbek.contact_dins.dto.phonebook.PhoneBookDetailsDTO;
import uz.umidbek.contact_dins.service.ContactService;
import uz.umidbek.contact_dins.service.PhoneBookService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ContactController controller;

    @Autowired
    private ContactService service;

    @Autowired
    private PhoneBookService phoneBookService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCreateExpectStatusOk() throws Exception {
        List<PhoneBookDetailsDTO> detailsDTOList = phoneBookService.findAll();
        mockMvc.perform(post("/contacts/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"phoneBookId\":" + detailsDTOList.get(1).getId() + ",\n" +
                        "\t\"firstName\":\"Umidbek\",\n" +
                        "    \"phoneNumber\":\"+998937441477\"\n" +
                        "    \n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateExpectStatusOk() throws Exception {
        List<ContactDetailsDTO> list = service.findAll();
        mockMvc.perform(put("/contacts/" + list.get(0).getId())
                .content("{\n" +
                        "\t\"lastName\":\"Olimov\",\n" +
                        "\t\"homeNumber\": \"+79959870470\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindOneExpectStatusOk() throws Exception {
        List<ContactDetailsDTO> list = service.findAll();
        mockMvc.perform(get("/contacts/" + list.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAllExpectStatusOk() throws Exception {
        mockMvc.perform(get("/contacts/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByNumber() throws Exception {
        mockMvc.perform(get("/contacts/findByNumber/79959870470")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteExpectStatusOk() throws Exception {
        List<ContactDetailsDTO> list = service.findAll();
        mockMvc.perform(delete("/contacts/" + list.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}