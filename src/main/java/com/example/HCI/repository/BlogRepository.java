package com.example.HCI.repository;

import com.example.HCI.model.Blog;
import com.example.HCI.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    Blog findById(long id);
    List<Blog> getAllByOrderByView();
    List<Blog> getAllOrOrderByDate();
    List<Blog> findBlogByUsername(String username);

}