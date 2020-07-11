package com.testcreditservice.configuration;

import com.testcreditservice.entity.RoleEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    private static final List<RoleEntity> AUTHORITIES
            = new ArrayList<RoleEntity>();

    public static RoleEntity ROLE_ADMIN = new RoleEntity(0L, "ROLE_ADMIN");
    public static RoleEntity ROLE_USER = new RoleEntity(1L, "ROLE_USER");

    static {
        AUTHORITIES.add(ROLE_ADMIN);
        AUTHORITIES.add(ROLE_USER);
    }

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {

        if (auth.getName().equals(auth.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), AUTHORITIES);
        }

        throw new BadCredentialsException("Bad Credentials");

    }

    @Override
    public boolean supports(Class<?> authentication) {

        if (authentication == null) return false;
        return Authentication.class.isAssignableFrom(authentication);

    }
}
