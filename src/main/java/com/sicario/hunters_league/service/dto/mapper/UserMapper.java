package com.sicario.hunters_league.service.dto.mapper;

import com.sicario.hunters_league.domain.User;
import com.sicario.hunters_league.service.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .nationality(user.getNationality())
                .joinDate(user.getJoinDate())
                .licenseExpirationDate(user.getLicenseExpirationDate())
                .build();
    }

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .nationality(userDTO.getNationality())
                .joinDate(userDTO.getJoinDate())
                .licenseExpirationDate(userDTO.getLicenseExpirationDate())
                .build();
    }
}