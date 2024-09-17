package org.itstep.msk.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.itstep.msk.app.service.ServiceCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ForgetPasswordControllerTest {

    @MockBean
    private ServiceCustomer mockService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Should return string page ")
    public void test_get_forget_password_page() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forgot_password"))
                .andExpect(status().isOk());

    }
}
