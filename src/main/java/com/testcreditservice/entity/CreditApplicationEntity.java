package com.testcreditservice.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "credit_applications")
public class CreditApplicationEntity {
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
    @Column(name = "amount")
    Long amount; // In cents

    @Getter
    @Setter
    @Column(name = "term")
    Long term; // In months

    @Getter
    @Setter
    @Column(name = "country")
    String country;

    @Getter
    @Setter
    @Column(name = "confirmed")
    Boolean confirmed;

    @Getter
    @Setter
    @Column(name = "timestamp")
    Date timestamp;

    public CreditApplicationEntity() {
        timestamp = Date.from(Instant.now());
    }

}
