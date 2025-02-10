package com.api.springapirest.repository;

import com.api.springapirest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("user_repository")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
