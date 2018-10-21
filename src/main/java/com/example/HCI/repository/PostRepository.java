package com.example.HCI.repository;


import com.example.HCI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PostRepository")
public interface PostRepository extends JpaRepository<Post,Long>{
    List<Post> findAllByUser(String name);
}
