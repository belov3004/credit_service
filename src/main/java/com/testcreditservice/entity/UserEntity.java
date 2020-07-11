package com.testcreditservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "users", indexes = {@Index(name = "user_name_idx",  columnList="user_name", unique = true)})
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue
    @Nullable
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "user_name")
    private String userName;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "personal_code")
    private String personalCode;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH}
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "roles_id",
                    referencedColumnName = "id"
            )}
    )
    @Getter
    @Setter
    private Set<RoleEntity> roles = new HashSet();

    public void grantRole(RoleEntity role) {
        this.roles.add(role);
    }

    public void block() {
        this.roles.clear();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
