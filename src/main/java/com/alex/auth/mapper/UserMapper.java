package com.alex.auth.mapper;

import com.alex.auth.dto.JwtDto;
import com.alex.auth.dto.SignUpDto;
import com.alex.auth.dto.UserDto;
import com.alex.auth.entity.Role;
import com.alex.auth.entity.RoleName;
import com.alex.auth.entity.User;
import com.alex.auth.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        builder = @Builder(disableBuilder = true))
public abstract class UserMapper {
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Mapping(expression = "java(mapRoles(dto.roles()))", target = "roles")
    @Mapping(source = "password", qualifiedByName = {"encryptPassword"}, target = "password")
    @Mapping(target = "enabled",constant = "true")
    public abstract User toUser(SignUpDto dto);

    @Mapping(expression = "java(mapRolesToString(user.getRoles()))", target = "roles")
    public abstract UserDto toUserDto(User user);

    @Mapping(target = "authorities",expression = "java(mapRolesToString(securityUser.getAuthorities()))")
    @Mapping(target = "type", constant = "Bearer")
    public abstract JwtDto toJwtDto(SecurityUser securityUser, String token);

    public List<Role> mapRoles(List<String> roles) {
        return roles.stream()
                .map(RoleName::valueOf)
                .map(Role::new)
                .collect(Collectors.toList());
    }

    public List<String> mapRolesToString(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
              /*  .toArray(String[]::new);*/
    }

    @Named("encryptPassword")
    public String encryptPassword(String password) {
        return this.passwordEncoder.encode(password);
    }

}
