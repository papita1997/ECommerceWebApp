package com.pawan.ECommerceWebApp.service;

import com.pawan.ECommerceWebApp.model.CustomUserDetails;
import com.pawan.ECommerceWebApp.model.User;
import com.pawan.ECommerceWebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        optionalUser.orElseThrow(()-> new UsernameNotFoundException("User Not Found!"));
        return optionalUser.map(CustomUserDetails::new).get();
    }
}
