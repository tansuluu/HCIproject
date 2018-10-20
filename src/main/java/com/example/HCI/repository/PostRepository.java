package com.example.HCI.repository;


import com.example.HCI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PostRepository")
public interface PostRepository extends JpaRepository<Post,Long>{
}
