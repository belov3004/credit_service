package com.testcreditservice.dao;

import com.testcreditservice.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationDao extends JpaRepository<ConfigurationEntity, String>, JpaSpecificationExecutor<ConfigurationEntity> {
}