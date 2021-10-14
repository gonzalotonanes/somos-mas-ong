package com.alkemy.ong.database.jparepositories;

import com.alkemy.ong.database.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findById(long id);
    RoleEntity findByName(String name);
}