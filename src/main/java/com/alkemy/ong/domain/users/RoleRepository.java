package com.alkemy.ong.domain.users;

public interface RoleRepository {
    RoleModel findById(long id);
    RoleModel findByName(String name);
}