package com.alkemy.ong.database.jparepositories;

import com.alkemy.ong.database.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> getAllByOrderByCreatedAtDesc();

    List<CommentEntity> findByIdNews(int id);
}
