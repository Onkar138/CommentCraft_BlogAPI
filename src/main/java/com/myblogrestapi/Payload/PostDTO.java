package com.myblogrestapi.Payload;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDTO {

    private long postid;
    @NotEmpty
    @Size(min = 2,message = "Post title should be at least 2 character")
    private String title;
    @NotEmpty
    @Size(min = 10,message = "Post description should be at least 10 character")
    private String description;
    @NotEmpty
    @Size(min = 10,message = "Post content should be at least 10 character")
    private String content;

}
