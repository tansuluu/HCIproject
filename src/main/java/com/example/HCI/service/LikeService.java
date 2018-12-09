package com.example.HCI.service;


import com.example.HCI.model.Likes;
import com.example.HCI.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LikeService")
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    public Likes save(Likes likes){
        return likeRepository.save(likes);
    }

    public int removeByUsernameAndAppId(String name,long id){
            return likeRepository.removeByUsernameAndAppId(name,id);

    }

    public boolean existsByAppIdAndUsername(long id, String name){
        return likeRepository.existsByAppIdAndUsername(id,name);
    }

    public int removeByUsernameAndBlogIDd(String name,long id){
        return likeRepository.removeByUsernameAndBlogID(name,id);

    }

    public boolean existsByBlogIDAndUsername(long id, String name){
        return likeRepository.existsByBlogIDAndUsername(id,name);
    }
}
