package com.stackoverflow.services.jwt;

import com.stackoverflow.entities.User;
import com.stackoverflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Write the logic to get the user form DB
        Optional<User> optionalUser=  userRepository.findFirstByEmail(username);

        if (optionalUser == null){
            throw new UsernameNotFoundException("User Not found");
        }else {
            return new org.springframework.security.core.userdetails.User(
                    optionalUser.get().getEmail(),
                    optionalUser.get().getPassword(),
                    new ArrayList<>()
            );
        }
    }
}
