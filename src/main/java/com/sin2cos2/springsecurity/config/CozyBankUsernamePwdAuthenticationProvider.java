package com.sin2cos2.springsecurity.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CozyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CozyBankUsernamePwdAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    // Custom implementation of Authentication provider if it needs custom logic
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Successful authentication
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException("Invalid password");
            }

            // loadUserByUsername throw an error if user is not founded in DB
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException("No user registered with this details!", ex);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
