package com.example.HCI.service;

import com.example.HCI.model.Comment;
import com.example.HCI.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllByIdPlace(long id){
        return commentRepository.getAllByIdPlace(id);
    }
}