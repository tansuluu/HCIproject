package com.example.HCI.repository;


import com.example.HCI.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("LikeRepository")
public interface LikeRepository extends JpaRepository<Likes,Long>{
    Likes removeByUsernameAndAppId(String name,long id);
}
