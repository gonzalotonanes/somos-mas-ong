package com.alkemy.ong.database.jparepositories;

import com.alkemy.ong.database.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactEntity, Long> {

}
