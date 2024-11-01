package com.sicario.hunters_league.web.api;

import com.sicario.hunters_league.service.impl.UserServiceImpl;
import com.sicario.hunters_league.service.dto.UserDTO;
import com.sicario.hunters_league.web.vm.AuthResponseVm;
import com.sicario.hunters_league.web.vm.LoginVm;
import com.sicario.hunters_league.web.vm.RegisterVm;
import com.sicario.hunters_league.web.vm.UserSearchResponseVm;
import com.sicario.hunters_league.web.vm.mapper.LoginMapper;
import com.sicario.hunters_league.web.vm.mapper.RegisterMapper;
import com.sicario.hunters_league.web.vm.mapper.SearchResponseMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;
    private final SearchResponseMapper searchResponseMapper;

    public UserController(UserServiceImpl userService, RegisterMapper registerMapper, LoginMapper loginMapper, PasswordEncoder passwordEncoder, SearchResponseMapper searchResponseMapper) {
        this.userService = userService;
        this.registerMapper = registerMapper;
        this.loginMapper = loginMapper;
        this.passwordEncoder = passwordEncoder;
        this.searchResponseMapper = searchResponseMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterVm registerVm, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            UserDTO userDTO = registerMapper.toDTO(registerVm);
            userService.createUser(userDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Username or email already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginVm loginVm, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        UserDTO userDTO = userService.findByUsername(loginVm.getUsername());
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        boolean passwordMatch = passwordEncoder.matches(loginVm.getPassword(),
                userDTO.getPassword());

        if (passwordMatch) {
            AuthResponseVm response = new AuthResponseVm();
            response.setId(userDTO.getId());
            response.setUsername(userDTO.getUsername());
            response.setEmail(userDTO.getEmail());

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        log.debug("REST request to search users with username: {} or email: {}",
                username, email);

        try {
            if (username != null && email != null) {
                return ResponseEntity.badRequest()
                        .body("Please provide either username or email, not both");
            }

            if (username != null) {
                return searchByUsername(username);
            } else if (email != null) {
                return searchByEmail(email);
            } else {
                return ResponseEntity.badRequest()
                        .body("Please provide either username or email parameter");
            }
        } catch (Exception e) {
            log.error("Error during user search", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while searching for user");
        }
    }

    private ResponseEntity<?> searchByUsername(String username) {
        log.debug("Searching user by username: {}", username);
        UserDTO userDTO = userService.findByUsername(username);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with username: " + username);
        }
        return ResponseEntity.ok(searchResponseMapper.fromDTO(userDTO));
    }

    @GetMapping("/users/search/email")
    public ResponseEntity<?> searchByEmail(@RequestParam String email) {
        log.debug("Searching user by email: {}", email);
        UserDTO userDTO = userService.findByEmail(email);
        if (userDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with email: " + email);
        }
        return ResponseEntity.ok(searchResponseMapper.fromDTO(userDTO));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserSearchResponseVm>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.debug("REST request to get all Users");
        List<UserDTO> users = userService.getAllUsers(page, size);
        List<UserSearchResponseVm> response = users.stream()
                .map(searchResponseMapper::fromDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        log.debug("REST request to delete User : {}", id);
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting user with id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id,
                                        @Valid @RequestBody UserDTO userDTO,
                                        BindingResult result) {
        log.debug("REST request to update User : {}", id);

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(searchResponseMapper.fromDTO(updatedUser));
        } catch (Exception e) {
            log.error("Error updating user with id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the user");
        }
    }
}