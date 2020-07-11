package com.testcreditservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Nullable
    @Getter
    @Setter
    private Long id;

    @Column(name = "role_name")
    @Getter
    @Setter
    private String role;

    public RoleEntity(Long id, String role) {
        this.setId(id);
        this.role = role;
    }

    public String getAuthority() {
        return this.role;
    }

    public RoleEntity() {
    }

}
