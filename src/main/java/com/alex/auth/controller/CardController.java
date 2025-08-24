package com.alex.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Bank Card Application";
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        //cardRepository.delete(id);

    }
}
