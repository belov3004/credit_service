package com.testcreditservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
