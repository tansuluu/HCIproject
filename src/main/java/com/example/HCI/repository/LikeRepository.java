package com.example.HCI.repository;


import com.example.HCI.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("LikeRepository")
public interface LikeRepository extends JpaRepository<Likes,Long>{
    int removeByUsernameAndAppId(String name,long id);
    boolean existsByAppIdAndUsername(long id, String name);
}
