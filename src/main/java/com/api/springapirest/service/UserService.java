package com.api.springapirest.service;

import com.api.springapirest.entity.User;
import com.api.springapirest.exception.LoginUserException;
import com.api.springapirest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("user_service")
@RequiredArgsConstructor
@Slf4j
public class UserService{

    private final UserRepository userRepository;

    public User login(String email, String password) {
        log.info("Start of login");
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            log.info("End of login");
            return userOptional.get();
        }

        log.info("Login failed for user: " + email);
        throw new LoginUserException(email);
    }
}
