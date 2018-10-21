package com.example.HCI.service;


import com.example.HCI.model.Post;
import com.example.HCI.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostService")
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> findAllByUser(String n){
        return postRepository.findAllByUser(n);
    }
}
