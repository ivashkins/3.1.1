package com.ivash.boot_ivasha.dao;


import com.ivash.boot_ivasha.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
public class RolesDaoImpl implements RolesDao {

    private final EntityManager entityManager;

    public RolesDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Role getAdminRole() {
        return entityManager.find(Role.class,(long)1);
    }

    @Override
    public Role getUserRole() {
        return entityManager.find(Role.class,(long)2);
    }
}
