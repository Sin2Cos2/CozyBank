package com.sin2cos2.springsecurity.config;

import com.sin2cos2.springsecurity.models.Authority;
import com.sin2cos2.springsecurity.models.Customer;
import com.sin2cos2.springsecurity.repositories.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CozyBankUserDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CozyBankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password;
        List<Customer> customers = customerRepository.findByEmail(username);

        if (customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user: " + username);
        } else {
            password = customers.get(0).getPwd();
        }

        return new User(username, password, getAuthorities(customers.get(0).getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Authority> authorities) {
        return authorities
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
