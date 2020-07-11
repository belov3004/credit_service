package com.testcreditservice.api;

import com.testcreditservice.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/")
public interface UserApi {

    @GetMapping(value = "/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<UserDto> getUserByName(@PathVariable("username") String username);

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<List<UserDto>> getUsers();

    @PutMapping(value = "/users/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> updateUser(@PathVariable("username") String username, @RequestBody UserDto body);

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> createUser(@RequestBody UserDto body);

    @GetMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<String> loginUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);

    @GetMapping(value = "/logout_user")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> logoutUser();

    @GetMapping(value = "/user/{username}/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> activateUser(@PathVariable("username") String username);

    @GetMapping(value = "/user/{username}/block")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> blockUser(@PathVariable("username") String username);


}
