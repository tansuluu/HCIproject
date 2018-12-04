package com.example.HCI.repository;


import com.example.HCI.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CommentRepository")
public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> getAllByIdPlace(long id);
    List< Comment> getAllByBlogID(long id);
    Comment findById(long id);
    int deleteById(long id);
}
