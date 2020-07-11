package com.testcreditservice.service;

import com.testcreditservice.dao.ConfigurationDao;
import com.testcreditservice.entity.ConfigurationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    public static final String CREDIT_APPLICATIONS_LIMIT = "credit_applications_limit";

    @Autowired
    private ConfigurationDao configurationDao;

    @Override
    @Transactional
    public void setConfig(String name, String value) {
        ConfigurationEntity configurationEntity = new ConfigurationEntity();
        configurationEntity.setParam(name);
        configurationEntity.setValue(value);
        configurationDao.save(configurationEntity);
    }

    @Override
    @Transactional
    public String getConfig(String name) {
        Optional<ConfigurationEntity> configurationEntity = configurationDao.findById(CREDIT_APPLICATIONS_LIMIT);
        if (!configurationEntity.isPresent() || configurationEntity.get() == null)
            return null;
        return configurationEntity.get().getValue();
    }
}
