package org.itstep.msk.app.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerRegisterService {

    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    public void registerCustomer(Customer customer) {
        encodePassword(customer);
        String makeRandomString = RandomString.make(64);
        customer.setVerifyCode(makeRandomString);
    }

    private void encodePassword(Customer customer) {
        String encodedPassword = encoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }
}
