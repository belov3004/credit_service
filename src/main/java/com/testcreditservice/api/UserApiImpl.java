package com.testcreditservice.api;

import com.testcreditservice.model.UserDto;
import com.testcreditservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApiImpl implements UserApi {
    private static final Logger log = LoggerFactory.getLogger(UserApiImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<Void> createUser(@RequestBody UserDto body) {
        userService.registerUser(body);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateUser(@PathVariable("username") String username, @RequestBody UserDto body) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<UserDto> getUserByName(@PathVariable("username") String username) {
        UserDto user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> loginUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        return new ResponseEntity<String>(userService.loginUser(username, password), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> logoutUser() {
        userService.logoutCurrentUser();
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> activateUser(String username) {
        userService.activateUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> blockUser(String username) {
        userService.blockUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
