package com.alex.auth.dto;

import java.util.List;
import java.util.Set;

public record SignUpDto(String username, String password, String firstname, String lastname, List<String> roles) {
}
