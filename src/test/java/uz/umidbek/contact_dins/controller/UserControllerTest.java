package uz.umidbek.contact_dins.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uz.umidbek.contact_dins.dto.user.UserDetailsDTO;
import uz.umidbek.contact_dins.status.HttpStatus;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateUserExpectStatusOk() throws Exception {
        mockMvc.perform(post("/users/")
                .content("{\n" +
                        "\t\"firstName\":\"Umidbek\",\n" +
                        "\t\"lastName\":\"Olimov\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(HttpStatus.OK));
    }

    @Test
    void testCreateUserExpectRequiredMessage() throws Exception {
        mockMvc.perform(post("/users/")
                .content("{\n" +
                        "\t\"lastName\":\"Olimov\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(HttpStatus.FIRST_NAME_REQUIRED));
    }

    @Test
    void testUpdateUserExpectStatusOk() throws Exception {
        mockMvc.perform(post("/users/")
                .content("{\n" +
                        "\t\"firstName\":\"Umidbek\",\n" +
                        "\t\"lastName\":\"Olimov\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(
                put("/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"firstName\":\"Um1db3k\",\n" +
                                "\t\"lastName\":\"0l1m0v\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdExpectUserEntity() throws Exception {
        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByName() throws Exception {
        String name = "rio";
        mockMvc.perform(get("/users/findByName/" + name))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUserExpectStatusOk() throws Exception {
        List<UserDetailsDTO> list = userController.service.findAll();
        mockMvc.perform(delete("/users/" + list.get(list.size() - 1).getId()))
                .andExpect(status().isOk());
    }
}