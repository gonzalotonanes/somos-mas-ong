package com.alkemy.ong.database.jparepositories;

import com.alkemy.ong.database.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationJPARepository extends JpaRepository<OrganizationEntity, Integer> {
}
