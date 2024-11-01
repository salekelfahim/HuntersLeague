package com.sicario.hunters_league.service.impl;

import com.sicario.hunters_league.domain.User;
import com.sicario.hunters_league.repository.UserRepository;
import com.sicario.hunters_league.service.UserService;
import com.sicario.hunters_league.service.dto.UserDTO;
import com.sicario.hunters_league.service.dto.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserDTO userDTO) {
        log.debug("Request to create User : {}", userDTO.getUsername());

        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Transactional(readOnly = true)
    public UserDTO findByUsername(String username) {
        log.debug("Request to find User by username : {}", username);

        return userRepository.findByUsername(username)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public UserDTO findByEmail(String email) {
        log.debug("Request to find User by email : {}", email);

        return userRepository.findByEmail(email)
                .map(userMapper::toDTO)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers(int page, int size) {
        log.debug("Request to get all Users");

        Page<User> result = userRepository.findAll(PageRequest.of(page, size));
        return result.getContent().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteUser(String id) {
        log.debug("Request to delete User : {}", id);

        try {
            UUID uuid = UUID.fromString(id);
            userRepository.findById(uuid).ifPresent(user -> {
                userRepository.delete(user);
                log.debug("Deleted User: {}", id);
            });
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID string: {}", id);
            throw new IllegalArgumentException("Invalid user ID format");
        }
    }

    public UserDTO updateUser(String id, UserDTO userDTO) {
        log.debug("Request to update User : {}", id);

        try {
            UUID uuid = UUID.fromString(id);
            return userRepository.findById(uuid)
                    .map(existingUser -> {
                        existingUser.setFirstName(userDTO.getFirstName());
                        existingUser.setLastName(userDTO.getLastName());
                        existingUser.setNationality(userDTO.getNationality());
                        existingUser.setLicenseExpirationDate(userDTO.getLicenseExpirationDate());

                        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                        }
                        return userMapper.toDTO(userRepository.save(existingUser));
                    })
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID string: {}", id);
            throw new IllegalArgumentException("Invalid user ID format");
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return userRepository.findById(uuid)
                    .map(userMapper::toDTO)
                    .orElse(null);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID string: {}", id);
            throw new IllegalArgumentException("Invalid user ID format");
        }
    }
}