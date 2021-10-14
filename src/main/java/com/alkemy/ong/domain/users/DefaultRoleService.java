package com.alkemy.ong.domain.users;

import org.springframework.stereotype.Service;

@Service
public class DefaultRoleService implements RoleService {

    private final RoleRepository roleRepository;

    public DefaultRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleModel findById(long id){
        return roleRepository.findById(id);
    }

    public RoleModel findByName(String name){
        return roleRepository.findByName(name);
    }
}