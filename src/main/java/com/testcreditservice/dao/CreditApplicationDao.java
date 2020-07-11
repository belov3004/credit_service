package com.testcreditservice.dao;

import com.testcreditservice.entity.CreditApplicationEntity;
import com.testcreditservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditApplicationDao extends JpaRepository<CreditApplicationEntity, Long>, JpaSpecificationExecutor<CreditApplicationEntity> {
    List<CreditApplicationEntity> findAllByUser(UserEntity user);

    List<CreditApplicationEntity> findAllByConfirmed(Boolean confirmed);

    List<CreditApplicationEntity> findAllByUserAndConfirmed(UserEntity user, Boolean confirmed);

    Long countAllByTimestampAfterAndCountry(Date timestamp, String country);
}
