package com.alex.bank.dto;

import java.util.List;

public record UserDto(Long id,
                      String username,
                      String firstname,
                      String lastname,
                      boolean enabled,
                      List<String> roles) {
}
