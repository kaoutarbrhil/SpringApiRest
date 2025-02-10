package com.api.springapirest.service;

import com.api.springapirest.dto.request.EmployeeCreateDTO;
import com.api.springapirest.dto.response.EmployeeResponseDTO;
import com.api.springapirest.entity.Employee;
import com.api.springapirest.entity.User;
import com.api.springapirest.exception.LoginUserException;
import com.api.springapirest.exception.LogoutUserException;
import com.api.springapirest.exception.SalaryBadException;
import com.api.springapirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("user_service")
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;

    public User saveUser(final User user) {
        log.info("Start to create a new user");

        User savedUser = userRepository.save(user);
        log.info("End to create a new user");
        return savedUser;
    }


    public User login(String email, String password) {
        log.info("Start of login for user: {}", email);
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            log.info("User {} logged in successfully", email);
            return userOptional.get();
        }

        log.warn("Login failed for user: {}", email);
        throw new LoginUserException(email);
    }

    public void logout(String email) {
        log.info("Start of logout for user: {}", email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            log.info("User {} logged out successfully", email);
        } else {
            log.error("Logout failed for user: {}", email);
            throw new LogoutUserException(email);
        }
    }
}
