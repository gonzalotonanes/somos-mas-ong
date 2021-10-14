package com.alkemy.ong.database.jparepositories;


import com.alkemy.ong.database.entities.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ActivityJpaRepository extends JpaRepository<ActivityEntity, Long> {

}
