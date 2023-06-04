package com.myblogrestapi.Controller;

import com.myblogrestapi.Payload.PageResponse;
import com.myblogrestapi.Payload.PostDTO;
import com.myblogrestapi.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Post")
public class PostController {

    @Autowired
    private PostService postService;

    // http://localhost:8080/api/Post

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDTO postDTO, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDTO DTO = postService.createData(postDTO);
        return new ResponseEntity<>(DTO, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/Post/1  Onkar8132

    @GetMapping("/{post_id}")
    public PostDTO getPostById(@PathVariable("post_id") long id){
        return postService.getDataById(id);
    }

    // http://localhost:8080/api/Post

//    @GetMapping
//    public List<PostDTO> getAllPost(){
//
//        return postService.getdata();
//    }

   // http://localhost:8080/api/Post?pageNo=0&pageSize=2&sortBy=id&sortDir=asc

    @GetMapping
    public PageResponse getPostWithPagination(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "title",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
        return postService.getDataWithPagination(pageNo,pageSize,sortBy,sortDir);
    }

    // http://localhost:8080/api/Post/1/Update

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{post_id}/Update")
    public PostDTO updatePost(@PathVariable("post_id") long id,@RequestBody PostDTO postDTO){
       return postService.updateData(id,postDTO);
    }

    // http://localhost:8080/api/Post/1/Delete

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{post_id}/Delete")
    public ResponseEntity<String> deletePost(@PathVariable("post_id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post Deleted Successfully !!",HttpStatus.OK);
    }
}
