package com.testcreditservice.dao;

import com.testcreditservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesDao extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {

}
