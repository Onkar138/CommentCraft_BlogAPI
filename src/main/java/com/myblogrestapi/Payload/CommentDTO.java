package com.myblogrestapi.Payload;

import com.myblogrestapi.Entities.Post;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDTO {

    private long c_id;
    @NotEmpty
    @Size(min = 3,message = "Post name should be at least 3 character")
    private String name;
    @NotEmpty
    @Email(message ="Email should be mandatory!!")
    private String email;
    @NotEmpty
    @Size(min = 10,message = "Post message should be at least 10 character")
    private String body;
    private long postid;
    private Post post;
}
