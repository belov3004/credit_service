package com.testcreditservice.service;

import org.springframework.stereotype.Service;

@Service
public interface ConfigurationService {
    void setConfig(String name, String value);

    String getConfig(String name);
}
