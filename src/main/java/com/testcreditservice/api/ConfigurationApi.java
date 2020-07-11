package com.testcreditservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/")
public interface ConfigurationApi {

    @GetMapping(value = "/config")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> configure(@RequestParam(value = "param") String param, @RequestParam(value = "value") String value);

}
