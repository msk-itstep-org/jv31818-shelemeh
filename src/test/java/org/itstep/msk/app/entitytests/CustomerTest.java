package org.itstep.msk.app.entitytests;

import org.itstep.msk.app.entity.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {

    @Test
    public void constructorShouldSetCustomer(){
        Customer customer = new Customer("1234","Enrih",null);

        assertEquals("1234", customer.getPassword());
        assertEquals("Enrih",customer.getName());
        assertNotNull(customer.getPassword());
    }
}
