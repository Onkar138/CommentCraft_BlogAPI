package com.myblogrestapi.Service;

import com.myblogrestapi.Payload.PageResponse;
import com.myblogrestapi.Payload.PostDTO;

import java.util.List;

public interface PostService {

    public PostDTO createData(PostDTO postDTO);

    // public List<PostDTO> getdata();

    public PostDTO getDataById(long id);

    public PostDTO updateData(long id, PostDTO postDTO);

    public void deletePostById(long id);

    public PageResponse getDataWithPagination(int pageNo, int pageSize, String sortBy, String sortDir);
}
