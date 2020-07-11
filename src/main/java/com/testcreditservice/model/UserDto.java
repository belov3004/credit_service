package com.testcreditservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class UserDto {
    @JsonProperty("username")
    @Getter
    @Setter
    private String username = null;

    @JsonProperty("firstName")
    @Getter
    @Setter
    private String firstName = null;

    @JsonProperty("lastName")
    @Getter
    @Setter
    private String lastName = null;

    @JsonProperty("email")
    @Getter
    @Setter
    private String email = null;

    @JsonProperty("password")
    @Getter
    @Setter
    private String password = null;

    @JsonProperty("personalCode")
    @Getter
    @Setter
    private String personalCode;

    @JsonProperty("active")
    @Getter
    @Setter
    private Boolean active;

}

