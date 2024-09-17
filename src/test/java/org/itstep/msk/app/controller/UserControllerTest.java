package org.itstep.msk.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    @WithAnonymousUser
    public void testLogin(){
        String body = this.restTemplate.getForObject("/login",String.class);
        assertThat(body).contains("register");

    }

}
