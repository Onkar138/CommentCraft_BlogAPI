package com.myblogrestapi.Repository;

import com.myblogrestapi.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

       //  List<Comment> findByPostid(long post_id);

}

