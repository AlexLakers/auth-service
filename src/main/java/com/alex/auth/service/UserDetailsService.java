package com.alex.auth.service;

import com.alex.auth.dto.JwtDto;
import com.alex.auth.dto.SignInDto;
import com.alex.auth.dto.SignUpDto;
import com.alex.auth.dto.UserDto;
import com.alex.auth.entity.User;
import com.alex.auth.mapper.UserMapper;
import com.alex.auth.repository.UserRepository;
import com.alex.auth.security.SecurityUser;
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
