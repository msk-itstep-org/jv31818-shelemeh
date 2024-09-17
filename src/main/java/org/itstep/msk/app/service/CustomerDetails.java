package org.itstep.msk.app.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itstep.msk.app.entity.Customer;
import org.itstep.msk.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * +
 *
 * @author shele
 */
@Service
@Slf4j
public class CustomerDetails implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepo;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //Get such customer with his name from db , if not exists throw exception
        Customer customer = customerRepo.findByName(name);
        return new CustomerDetailsImpl(customer);


    }
}
