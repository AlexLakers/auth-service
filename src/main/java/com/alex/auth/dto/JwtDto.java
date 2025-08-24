package com.alex.auth.dto;

import java.util.List;

public record JwtDto(String token, String type, Long id, String username, List<String> authorities) {
}
