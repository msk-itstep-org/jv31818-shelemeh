package org.itstep.msk.app.repotests;


import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
@Transactional
@TestPropertySource("/application-test.properties")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepoTest {

    @Autowired
    private CustomerRepository customRepository;

    @Autowired
    private TestEntityManager testEntityManager;


    private Customer customer;

    @BeforeEach
    void setUp(){
        customRepository.deleteAll();
        customer = new Customer("12344ert","Ivan","zreo041990@gmai.com");
        customer.setId(2);
        customer.setPhoneNumber("+38745699934");
        customRepository.save(customer);
    }


    @Test
    @Sql("/customer_test.sql")
    public void testShouldWorkWell(){
        Customer saveCustomer = testEntityManager.persistAndFlush(customRepository.findByName("Ivan"));
        assertThat(customer.getName()).isEqualTo(saveCustomer.getName());

    }
    @Test
    @Sql("/customer_test.sql")
    public void updateTest(){
        customer.setName("Mike");
        customRepository.save(customer);

        var foundCustomer= customRepository.findByName(customer.getName());
        assertEquals("Mike",foundCustomer.getName());
    }

    @Test
    @Sql("/customer_test.sql")
    public void deleteTest(){
        customRepository.delete(customer);
        assertFalse(customRepository.existsById(customer.getId()));
    }
}
