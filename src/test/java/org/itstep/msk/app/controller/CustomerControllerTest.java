package org.itstep.msk.app.controller;

import lombok.SneakyThrows;
import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@PropertySource(value = "classpath:application.properties")
class CustomerControllerTest {

    @Autowired
   private MockMvc testMvc;
    
    @MockBean
    private CustomerRepository repository;

    @Test
    @SneakyThrows
    public void findByIDTest(Integer id){
        var customer = new Customer();
        customer.setName("jessy");
        customer.setPassword("1234");
    given(this.repository.findById(id)).willReturn(java.util.Optional.of(new Customer("1234", "jessy",null)));
        testMvc.perform(get("/customers/{id}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}