package com.testcreditservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class CreditRequestDto {
    @JsonProperty("amount")
    @Getter
    @Setter
    Long amount; // In cents

    @JsonProperty("term")
    @Getter
    @Setter
    Long term; // In months

    @JsonProperty("country")
    @Getter
    @Setter
    String country;

}
