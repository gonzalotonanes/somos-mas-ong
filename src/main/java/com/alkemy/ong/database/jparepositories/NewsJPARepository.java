package com.alkemy.ong.database.jparepositories;

import com.alkemy.ong.database.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsJPARepository extends JpaRepository<NewsEntity, Long> {
}
