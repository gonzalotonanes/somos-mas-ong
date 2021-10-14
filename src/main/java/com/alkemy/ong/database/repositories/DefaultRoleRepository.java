package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.RoleEntity;
import com.alkemy.ong.database.jparepositories.RoleJpaRepository;
import com.alkemy.ong.domain.users.RoleModel;
import com.alkemy.ong.domain.users.RoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultRoleRepository implements RoleRepository {
    private final RoleJpaRepository roleJpaRepository;

    public DefaultRoleRepository(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    public RoleModel findById(long id) {
        return toModelRole(roleJpaRepository.findById(id));
    }

    public RoleModel findByName(String name) {
        return toModelRole(roleJpaRepository.findByName(name));
    }

    public RoleModel toModelRole(RoleEntity roleEntity) {
        RoleModel role = new RoleModel();
        role.setIdRole(roleEntity.getIdRole());
        role.setCreatedAt(roleEntity.getCreatedAt());
        role.setDescription(roleEntity.getDescription());
        role.setName(roleEntity.getName());
        role.setUpdatedAt(roleEntity.getUpdatedAt());
        return role;
    }
}