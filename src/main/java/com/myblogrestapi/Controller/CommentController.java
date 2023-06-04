package com.myblogrestapi.Controller;

import com.myblogrestapi.Payload.CommentDTO;
import com.myblogrestapi.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // http://localhost:8080/api/posts/1/comments

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Object> createComment(@PathVariable(value = "postId") long postId,
                                                @Valid @RequestBody CommentDTO commentDTO, BindingResult result){

        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    // http://localhost:8080/api/posts/1/comments

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    // http://localhost:8080/api/posts/1/comments/1

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable("postId") long postId,@PathVariable("id") long commentId
    ){
        CommentDTO DTO = commentService.getDataByCommentId(postId, commentId);
        return new ResponseEntity<>(DTO,HttpStatus.OK);
    }

    // http://localhost:8080/api/posts/1/comments/1

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("postId") long postId,@PathVariable("id") long commentId,@RequestBody CommentDTO commentDTO
    ){
        CommentDTO DTO = commentService.updateComment(postId, commentId,commentDTO);
        return new ResponseEntity<>(DTO,HttpStatus.OK);
    }

    // http://localhost:8080/api/posts/2/comments/7

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") long postId,@PathVariable("id") long commentId){
            commentService.deleteComment(postId, commentId);
            return new ResponseEntity<>("Comment Deleted Sucessfully!!",HttpStatus.OK);
    }

}
