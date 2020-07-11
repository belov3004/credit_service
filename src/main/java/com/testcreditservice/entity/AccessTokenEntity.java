package com.testcreditservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "access_tokens")
public class AccessTokenEntity {
    @Id
    @GeneratedValue
    @Nullable
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    private UserEntity user;


    @Getter
    @Setter
    @Column
    private String token;
}
