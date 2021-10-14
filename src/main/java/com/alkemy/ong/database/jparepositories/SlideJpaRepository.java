package com.alkemy.ong.database.jparepositories;

import com.alkemy.ong.database.entities.SlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SlideJpaRepository extends JpaRepository<SlideEntity, Integer> {
    List<SlideEntity> getAllByIdOrganization(int idOrganization);
}
