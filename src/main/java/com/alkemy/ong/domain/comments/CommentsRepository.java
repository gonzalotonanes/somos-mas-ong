package com.alkemy.ong.domain.comments;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository {
    CommentModel createComment(CommentModel commentModel);

    CommentModel updateComment(CommentModel comment);

    void deleteComment(CommentModel comment);

    Optional<CommentModel> findById(int ind);

    List<CommentModel> findAll();

    List<CommentModel> getAllOrdered();

    List<CommentModel> findByNewsId(int id);

}
