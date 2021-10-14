package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.comments.CommentModel;
import com.alkemy.ong.domain.comments.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /*@GetMapping
    public List<CommentModel> getAll() {
        return commentService.getAll();
    }*/

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(toDto(commentService.createComment(toModel(commentDto))), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByIdOfNews(@Valid @PathVariable("id") int id) {
        return new ResponseEntity<>(commentService.getCommentByIdOfNews(id)
                .stream()
                .map(this::toDto)
                .collect(toList())
                , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentSimpleDto> deleteComment(@Valid @RequestBody CommentSimpleDto commentSimpleDto) {
        commentService.deleteComment(toModel(commentSimpleDto));
        return new ResponseEntity<>(commentSimpleDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllOrdered() {
        return ResponseEntity.ok(commentService.getAllBodiesOrdered());
    }

    //@PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('USER')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") int id,
                                                    @Valid @RequestBody CommentModel commentModel) {
        checkExistence(id);
        return new ResponseEntity<>(toDto(commentService.updateComment(commentModel)), HttpStatus.OK);
    }

    private CommentModel toModel(CommentDto commentDto) {
        CommentModel commentModel = new CommentModel();
        commentModel.setBody(commentDto.getBody());
        commentModel.setIdUser(commentDto.getIdUser());
        commentModel.setIdNews(commentDto.getIdNews());
        return commentModel;
    }

    private CommentDto toDto(CommentModel commentModel) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentModel.getId());
        commentDto.setIdNews(commentModel.getIdNews());
        commentDto.setIdUser(commentModel.getIdUser());
        commentDto.setBody(commentModel.getBody());
        return commentDto;
    }

    private CommentModel toModel(CommentSimpleDto commentSimpleDto) {
        CommentModel commentModel = new CommentModel();
        commentModel.setId(commentSimpleDto.getId());
        commentModel.setIdUser(commentSimpleDto.idUser);
        return commentModel;
    }

    @Data
    private class CommentDto {
        private int id;
        @NotNull
        @Min(value = 1, message = "Field idUser required or requires a number greater than zero ")
        private long idUser;
        @NotNull
        @Min(value = 1, message = "Field idNews required or requires a number greater than zero ")
        private int idNews;
        @NotBlank(message = "Field body is required or must not be empty")
        private String body;
    }

    @Data
    @AllArgsConstructor
    private static class CommentSimpleDto {
        private int id;
        @NotNull
        @Min(value = 1, message = "Field idUser required or requires a number greater than zero ")
        private long idUser;
    }

    private void checkExistence(int id) {
        commentService.findById(id);
    }
}