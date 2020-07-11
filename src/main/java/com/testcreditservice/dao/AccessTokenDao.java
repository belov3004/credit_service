package com.testcreditservice.dao;

import com.testcreditservice.entity.AccessTokenEntity;
import com.testcreditservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenDao extends JpaRepository<AccessTokenEntity, Long>, JpaSpecificationExecutor<AccessTokenEntity> {
    AccessTokenEntity findByToken(String token);

    void deleteAccessTokenEntitiesByUser(UserEntity user);
}
