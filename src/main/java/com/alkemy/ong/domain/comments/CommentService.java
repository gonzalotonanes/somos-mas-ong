package com.alkemy.ong.domain.comments;

import java.util.List;

public interface CommentService {
    CommentModel createComment(CommentModel commentModel);

    CommentModel updateComment(CommentModel commentModel);

    void deleteComment(CommentModel commentModel);

    CommentModel findById(int id);

    List<CommentModel> getAll();

    List<String> getAllBodiesOrdered();

    List<CommentModel> getCommentByIdOfNews(int id);
}
