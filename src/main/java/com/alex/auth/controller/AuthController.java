package com.alex.auth.controller;

import com.alex.auth.dto.JwtDto;
import com.alex.auth.dto.SignInDto;
import com.alex.auth.dto.SignUpDto;
import com.alex.auth.dto.UserDto;
import com.alex.auth.entity.User;
import com.alex.auth.service.UserDetailsService;
import com.alex.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/signin")
    public JwtDto signIn(@RequestBody SignInDto signInDto) {
        return userService.login(signInDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> signUp(@PathVariable Long id) {
        var user=userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id));
    }

    @PostMapping("/signup")
    public UserDto logUp(@RequestBody SignUpDto signUpDto) {
        return userService.addUser(signUpDto);
    }
}
