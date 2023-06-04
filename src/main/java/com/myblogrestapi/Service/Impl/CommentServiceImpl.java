package com.myblogrestapi.Service.Impl;

import com.myblogrestapi.Entities.Comment;
import com.myblogrestapi.Entities.Post;
import com.myblogrestapi.Exception.BlogAPIException;
import com.myblogrestapi.Exception.ResourceNotFoundException;
import com.myblogrestapi.Payload.CommentDTO;
import com.myblogrestapi.Repository.CommentRepository;
import com.myblogrestapi.Repository.PostRepository;
import com.myblogrestapi.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {

        Comment comment = mapToEntity(commentDTO);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);

        // comment entity to DB
        Comment newComment =  commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
//        // retrieve comments by postId
//        List<Comment> comments = commentRepository.findByPostid(postId);
//
//        // convert list of comment entities to list of comment dto's
//        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
        return null;
    }

    @Override
    public CommentDTO getDataByCommentId(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", commentId));

        if(comment.getPost().getPostid()!=post.getPostid()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Does Not Belong To Post");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId,CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", commentId));

        if(comment.getPost().getPostid()!=post.getPostid()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Does Not Belong To Post");
        }

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        Comment newcomment = commentRepository.save(comment);
        return mapToDTO(newcomment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", commentId));

        if(comment.getPost().getPostid()!=post.getPostid()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment Does Not Belong To Post");
        }
        commentRepository.deleteById(commentId);
    }


    private CommentDTO mapToDTO(Comment comment){

        CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);

//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setC_id(comment.getC_id());
//        commentDTO.setName(comment.getName());
//        commentDTO.setEmail(comment.getEmail());
//        commentDTO.setBody(comment.getBody());
//        commentDTO.setPost_id(comment.getPost().getPost_id());
        return  commentDTO;
    }

    private Comment mapToEntity(CommentDTO commentDTO){
        Comment comment = mapper.map(commentDTO, Comment.class);

//        Comment comment = new Comment();
//        comment.setC_id(commentDTO.getC_id());
//        comment.setName(commentDTO.getName());
//        comment.setEmail(commentDTO.getEmail());
//        comment.setBody(commentDTO.getBody());
        return  comment;
    }

}
