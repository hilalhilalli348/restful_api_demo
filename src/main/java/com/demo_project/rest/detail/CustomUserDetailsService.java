package com.demo_project.rest.detail;

import com.demo_project.rest.detail.CustomUserDetails;
import com.demo_project.rest.exception.ResourceNotFountException;
import com.demo_project.rest.model.User;
import com.demo_project.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> userFromDB = userRepository.findByUsername(username);

        if (userFromDB.isEmpty()) {
            throw new ResourceNotFountException("Belə user mövcut deyil !");
        }

        User user = userFromDB.get();

        return new CustomUserDetails(user);
    }
}
