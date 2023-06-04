package com.myblogrestapi.Repository;

import com.myblogrestapi.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class PostRepository implements JpaRepository<Post,Long> {

}
