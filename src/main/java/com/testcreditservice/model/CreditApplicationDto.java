package com.testcreditservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CreditApplicationDto extends CreditRequestDto {
    @JsonProperty("id")
    @Getter
    @Setter
    Long id;

    @JsonProperty("firstName")
    @Getter
    @Setter
    String firstName;

    @JsonProperty("lastName")
    @Getter
    @Setter
    String lastName;

    @JsonProperty("personalCode")
    @Getter
    @Setter
    String personalCode;

    @JsonProperty("confirmed")
    @Getter
    @Setter
    Boolean confirmed;

    @JsonProperty("timestamp")
    @Getter
    @Setter
    String timestamp;

}
