package com.alkemy.ong.database.jparepositories;

import com.alkemy.ong.database.entities.TestimonialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialJpaRepository extends JpaRepository<TestimonialEntity, Long> {
}
