package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.CommentEntity;
import com.alkemy.ong.database.jparepositories.CommentJpaRepository;
import com.alkemy.ong.domain.comments.CommentModel;
import com.alkemy.ong.domain.comments.CommentsRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.Optional;

@Repository
public class DefaultCommentRepository implements CommentsRepository {

    private CommentJpaRepository jpaRepository;

    public DefaultCommentRepository(CommentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public CommentModel createComment(CommentModel comment) {
        return toModel(jpaRepository.save(createModelEntity(comment)));
    }

    @Override
    public void deleteComment(CommentModel comment) {
        jpaRepository.delete(toEntity(comment));
    }

    @Override
    public Optional<CommentModel> findById(int id) {
        return jpaRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<CommentModel> getAllOrdered() {
        return jpaRepository.getAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toModel)
                .collect(toList());
    }

    private CommentEntity createModelEntity(CommentModel commentModel) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setBody(commentModel.getBody());
        commentEntity.setIdNews(commentModel.getIdNews());
        commentEntity.setIdUser(commentModel.getIdUser());
        commentEntity.setCreatedAt(LocalDateTime.now());
        commentEntity.setUpdatedAt(LocalDateTime.now());
        return commentEntity;
    }

    @Override
    public List<CommentModel> findByNewsId(int id) {
        return jpaRepository.findByIdNews(id)
                .stream()
                .map(this::toModel)
                .collect(toList());
    }

    @Override
    public CommentModel updateComment(CommentModel commentModel) {
        return toModel(jpaRepository.save(toEntity(commentModel)));
    }

    @Override
    public List<CommentModel> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toModel)
                .collect(toList());
    }

    private CommentEntity toEntity(CommentModel commentModel) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentModel.getId());
        commentEntity.setBody(commentModel.getBody());
        commentEntity.setIdNews(commentModel.getIdNews());
        commentEntity.setIdUser(commentModel.getIdUser());
        commentEntity.setCreatedAt(LocalDateTime.now());
        commentEntity.setUpdatedAt(LocalDateTime.now());
        return commentEntity;
    }

    private CommentModel toModel(CommentEntity commentEntity) {
        CommentModel comment = new CommentModel();
        comment.setBody(commentEntity.getBody());
        comment.setIdNews(commentEntity.getIdNews());
        comment.setIdUser(commentEntity.getIdUser());
        comment.setId(commentEntity.getId());
        comment.setCreatedAt(commentEntity.getCreatedAt());
        comment.setUpdatedAt(commentEntity.getUpdatedAt());
        return comment;
    }
}
