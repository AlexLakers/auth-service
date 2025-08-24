package com.alex.bank.service;

import com.alex.bank.dto.JwtDto;
import com.alex.bank.dto.SignInDto;
import com.alex.bank.dto.SignUpDto;
import com.alex.bank.dto.UserDto;
import com.alex.bank.entity.User;
import com.alex.bank.mapper.UserMapper;
import com.alex.bank.repository.UserRepository;
import com.alex.bank.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;


    // Method to load user details by username (email)
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Fetch user from the database by email (username)
        // Convert UserInfo to UserDetails (SecurityUser)
        return userRepository.findUserByUsername(username)
                .map(user -> new SecurityUser(user.getUsername(), user.getPassword(),
                                              user.getRoles(), user.isEnabled(),
                                              user.getId()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

}
