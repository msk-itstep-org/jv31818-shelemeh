package org.itstep.msk.app.service;

import org.itstep.msk.app.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Customer details service for authorization
 */

public class CustomerDetailsImpl implements UserDetails {

    private Customer c;

    public CustomerDetailsImpl(Customer c) {
        this.c = c;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return c.getRole()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return c.getPassword();
    }

    @Override
    public String getUsername() {
        return c.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
