package com.testcreditservice.service;

import com.testcreditservice.entity.UserEntity;
import com.testcreditservice.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    PasswordEncoder getPasswordEncoder();

    void registerUser(UserDto user);

    String loginUser(String username, String Password);

    void logoutCurrentUser();

    UserEntity findUserByAccessToken(String token);

    UserDto findUserByUsername(String username);

    List<UserDto> getUsers();

    void activateUser(String username);

    void blockUser(String username);
}
