package com.testcreditservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "configuration")
public class ConfigurationEntity {
    @Id
    @Getter
    @Setter
    private String param;

    @Getter
    @Setter
    @Column
    private String value;
}
