package com.testcreditservice.api;

import com.testcreditservice.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationApiImpl implements ConfigurationApi {

    @Autowired
    ConfigurationService configurationService;

    @Override
    public ResponseEntity<Void> configure(String param, String value) {
        configurationService.setConfig(param, value);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
