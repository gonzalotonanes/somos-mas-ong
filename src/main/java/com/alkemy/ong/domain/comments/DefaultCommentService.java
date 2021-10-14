package com.alkemy.ong.domain.comments;

import com.alkemy.ong.domain.exceptions.DomainException;
import com.alkemy.ong.domain.users.UserModel;
import com.alkemy.ong.domain.users.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class DefaultCommentService implements CommentService {

    private CommentsRepository commentsRepository;

    private UserRepository userRepository;

    public DefaultCommentService(CommentsRepository commentsRepository, UserRepository userRepository) {
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentModel createComment(CommentModel commentModel) {
        return commentsRepository.createComment(commentModel);
    }

    @Override
    public List<String> getAllBodiesOrdered() {
        return commentsRepository.getAllOrdered()
                .stream()
                .map(c -> c.getBody())
                .collect(toList());
    }

    @Override
    public void deleteComment(CommentModel comment) {

        checkIfExistComment(comment);
        checkIfExistUser(comment);
        Long idUser= userRepository.getUserById(comment.getIdUser()).get().getIdUser();
        checkIfUserIsDiferent(comment, idUser);

        commentsRepository.deleteComment(comment);
    }

    private void checkIfUserIsDiferent(CommentModel comment, Long idUser){
        if (comment.getIdUser() != idUser)
            throw new AccessDeniedException("User with id " + idUser + " can't delete this comment");
    }

    private void checkIfExistComment(CommentModel commentModel) {
        CommentModel comment = commentsRepository.findById(commentModel.getId()).orElseThrow(DomainException::new);
    }

    private void checkIfExistUser(CommentModel commentModel) {
        UserModel user = userRepository.getUserById(commentModel.getIdUser()).orElseThrow(DomainException::new);
    }

    @Override
    public CommentModel updateComment(CommentModel commentModel) {
        return commentsRepository.updateComment(commentModel);
    }


    @Override
    public List<CommentModel> getCommentByIdOfNews(int id) {
        return commentsRepository.findByNewsId(id);
    }
    @Override
    public CommentModel findById(int id) {
        return commentsRepository.findById(id).orElseThrow(DomainException::new);
    }

    @Override
    public List<CommentModel> getAll() {
        return commentsRepository.findAll();
    }
}