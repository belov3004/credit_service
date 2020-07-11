package com.testcreditservice.service;

import com.testcreditservice.api.exceptions.AlreadyExistException;
import com.testcreditservice.api.exceptions.NotFoundException;
import com.testcreditservice.api.exceptions.NotValidException;
import com.testcreditservice.configuration.AuthenticationProvider;
import com.testcreditservice.dao.AccessTokenDao;
import com.testcreditservice.dao.RolesDao;
import com.testcreditservice.dao.UserDao;
import com.testcreditservice.entity.AccessTokenEntity;
import com.testcreditservice.entity.UserEntity;
import com.testcreditservice.model.UserDto;
import com.testcreditservice.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDao userDao;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private AccessTokenDao accessTokenDao;

    @Autowired
    private JWTUtil jwtUtil;

    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUserName(s);
        if (user == null)
            throw new UsernameNotFoundException(s);
        return user;
    }

    @Override
    @Transactional
    public void registerUser(UserDto userDto) {
        if (!isValidUsername(userDto.getUsername()))
            throw new NotValidException(400, "Username contains invalid characters");
        if (!isValidEmailAddress(userDto.getEmail()))
            throw new NotValidException(400, "Email address is not valid");

        if (userDao.findByUserName(userDto.getUsername()) != null)
            throw new AlreadyExistException(400, "User with username \"" + userDto.getUsername() + "\" already exist");
        userDao.save(convertToEntity(userDto));
    }

    @Override
    @Transactional
    public String loginUser(String login, String password) {
        UserEntity user = userDao.findByUserName(login);
        if (user == null)
            throw new NotFoundException(404, "User name not found or incorrect password error message");
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new NotFoundException(404, "User name not found or incorrect password error message");

        AccessTokenEntity token = new AccessTokenEntity();
        token.setToken(this.jwtUtil.generateToken(user.getUsername()));
        token.setUser(user);
        accessTokenDao.save(token);
        return token.getToken();
    }

    @Override
    @Transactional
    public void logoutCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserEntity user = null;

        try {
            user = (UserEntity) principal;
        } catch (ClassCastException e) {
            return;
        }
        accessTokenDao.deleteAccessTokenEntitiesByUser(user);
    }

    @Override
    @Transactional
    public UserEntity findUserByAccessToken(String token) {
        AccessTokenEntity accessToken = accessTokenDao.findByToken(token);
        if (accessToken == null)
            return null;
        return accessToken.getUser();
    }

    @Override
    public UserDto findUserByUsername(String username) {
        UserEntity user = userDao.findByUserName(username);
        if (user == null)
            throw new NotFoundException(404, "User name not found");
        return convertToDto(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users = new LinkedList<>();
        for (UserEntity user : userDao.findAll()) {
            users.add(convertToDto(user));
        }
        return users;
    }

    @Override
    public void activateUser(String username) {
        UserEntity user = userDao.findByUserName(username);
        if (user == null)
            throw new NotFoundException(404, "User name not found");

        user.grantRole(AuthenticationProvider.ROLE_USER);
        userDao.save(user);
    }

    @Override
    public void blockUser(String username) {
        UserEntity user = userDao.findByUserName(username);
        if (user == null)
            throw new NotFoundException(404, "User name not found");

        user.block();
        userDao.save(user);
    }

    @Override
    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    private UserDto convertToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword("<hidden>");
        userDto.setActive(!userEntity.getRoles().isEmpty());
        userDto.setPersonalCode(userEntity.getPersonalCode());
        return userDto;
    }

    private UserEntity convertToEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setUserName(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPersonalCode(userDto.getPersonalCode());
        return user;
    }

    public static boolean isValidEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidUsername(String username) {
        String usernameRegex = "^[a-zA-Z0-9_]*$";

        Pattern pat = Pattern.compile(usernameRegex);
        if (username == null)
            return false;
        return pat.matcher(username).matches();
    }
}
