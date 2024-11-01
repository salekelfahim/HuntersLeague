package com.sicario.hunters_league.service;

import com.sicario.hunters_league.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO findByUsername(String username);
    UserDTO findByEmail(String email);
    void deleteUser(String id);
    UserDTO updateUser(String id, UserDTO userDTO);
}
