package com.myblogrestapi.Service.Impl;

import com.myblogrestapi.Entities.Post;
import com.myblogrestapi.Exception.ResourceNotFoundException;
import com.myblogrestapi.Payload.PageResponse;
import com.myblogrestapi.Payload.PostDTO;
import com.myblogrestapi.Repository.PostRepository;
import com.myblogrestapi.Service.PostService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    public PostServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    private ModelMapper mapper;

    @Override
    public PostDTO createData(PostDTO postDTO) {
        Post post = mapToEntity(postDTO);
        Post newpost = postRepo.save(post);
        PostDTO postDTO1 = mapToDTO(newpost);
        return postDTO1;
    }

    @Override
    public PostDTO getDataById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", id));
        return mapToDTO(post);
    }

//    @Override
//    public List<PostDTO> getdata() {
//        List<Post> posts =postRepo.findAll();
//        return posts.stream().map(newpost->mapToDTO(newpost)).collect(Collectors.toList());
//    }


    @Override
    public PageResponse getDataWithPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> listOfPosts  = posts.getContent();
        List<PostDTO> content = listOfPosts .stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PageResponse pageResponse=new PageResponse();

        pageResponse.setContent(content);
        pageResponse.setPageNumber(posts.getNumber());
        pageResponse.setPageSize(posts.getSize());
        pageResponse.setTotalElements(posts.getTotalElements());
        pageResponse.setTotalPage(posts.getTotalPages());
        pageResponse.setLastPage(posts.isLast());

        return pageResponse;
    }

    @Override
    public PostDTO updateData(long id, PostDTO postDTO) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", id));

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        Post newpost = postRepo.save(post);
        return mapToDTO(newpost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "Id", id));
        postRepo.deleteById(id);
    }

    private Post mapToEntity(PostDTO postDTO) {
        Post post = mapper.map(postDTO, Post.class);

//        Post post=new Post();
//
//        post.setTitle(postDTO.getTitle());
//        post.setDescription(postDTO.getDescription());
//        post.setContent(postDTO.getContent());
        return post;
    }

    private PostDTO mapToDTO(Post newpost) {
        PostDTO postDTO = mapper.map(newpost, PostDTO.class);

//        PostDTO postDTO=new PostDTO();
//
//        postDTO.setPost_id(newpost.getPost_id());
//        postDTO.setTitle(newpost.getTitle());
//        postDTO.setDescription(newpost.getDescription());
//        postDTO.setContent(newpost.getContent());
        return postDTO;
    }
}
