package com.sicario.hunters_league.web.api;

import com.sicario.hunters_league.service.UserService;
import com.sicario.hunters_league.service.dto.UserDTO;
import com.sicario.hunters_league.web.vm.AuthResponseVm;
import com.sicario.hunters_league.web.vm.LoginVm;
import com.sicario.hunters_league.web.vm.RegisterVm;
import com.sicario.hunters_league.web.vm.mapper.LoginMapper;
import com.sicario.hunters_league.web.vm.mapper.RegisterMapper;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final RegisterMapper registerMapper;
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RegisterMapper registerMapper, LoginMapper loginMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.registerMapper = registerMapper;
        this.loginMapper = loginMapper;
        this.passwordEncoder = passwordEncoder;
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
}
