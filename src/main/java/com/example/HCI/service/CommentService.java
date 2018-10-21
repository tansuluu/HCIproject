package com.example.HCI.service;

import com.example.HCI.model.Comment;
import com.example.HCI.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service("commentService")
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllByIdPlace(long id){
        return commentRepository.getAllByIdPlace(id);
    }

    public Comment save(Comment comment){
        comment.setDateCom(Calendar.getInstance().getTime());
        return commentRepository.save(comment);
    }

    public Comment findById(long id){
        return commentRepository.findById(id);
    }

}
