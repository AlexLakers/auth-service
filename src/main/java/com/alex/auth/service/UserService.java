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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    public JwtDto login(SignInDto signInDto) {
        //Auth using Spring Security
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword());
        Authentication authentication=authenticationManager.authenticate(authRequest);
        //Set auth in Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

       // if(authentication.isAuthenticated()){
            //Generate Jwt Token
        SecurityUser securityUser=(SecurityUser) authentication.getPrincipal();
            String token = jwtService.generateToken(securityUser.getUsername());//username
       // }

        //Get Data about auth user and build dto.
        System.out.println("");
        return userMapper.toJwtDto(securityUser,token);

    }
    public User findUserById(Long id){
       var c= userRepository.findUserById(id);
        var cc=userRepository.findUserByUsername("alexeev@mail.com");
        return userRepository.findUserById(id);
    }

    @Transactional
    // Add any additional methods for registering or managing users
    public UserDto addUser(SignUpDto signUpDto) {
        // Encrypt password before saving
        User savedUser = userRepository.save(userMapper.toUser(signUpDto));

        // userInfo.setPassword(encoder.encode(userInfo.getPassword()));

        return userMapper.toUserDto(savedUser);
    }
}
