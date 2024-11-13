package com.sicario.hunters_league.service;

import com.sicario.hunters_league.domain.Enum.Role;
import com.sicario.hunters_league.domain.User;
import com.sicario.hunters_league.repository.UserRepository;
import com.sicario.hunters_league.util.PasswordHash;
import com.sicario.hunters_league.web.error.InvalidUsernameOrPasswordException;
import com.sicario.hunters_league.web.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        user.setRole(Role.MEMBER);
        user.setJoinDate(java.time.LocalDateTime.now());
        user.setLicenseExpirationDate(java.time.LocalDateTime.now().plusYears(2));
        String password = PasswordHash.hashPassword(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public User login(User user) {
        return userRepository.findByUsername(user.getUsername())
                .filter(u -> PasswordHash.checkPassword(user.getPassword(), u.getPassword()))
                .orElseThrow(InvalidUsernameOrPasswordException::new);
    }

    public User update(User user) {
        findById(user.getId());
        return userRepository.save(user);
    }

    public void delete(UUID id) {
        findById(id);
        userRepository.deleteUser(id);
    }

    public Page<User> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
        return userRepository.findAll(pageable);
    }


    public Page<User> search(String username, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.search(username, email, pageable);
    }
}
