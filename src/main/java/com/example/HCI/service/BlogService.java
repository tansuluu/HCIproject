package com.example.HCI.service;

import com.example.HCI.model.Blog;
import com.example.HCI.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("BlogService")
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public Blog findById(long id){
        return blogRepository.findById(id);
    }
    public Blog updateView(long id,int n){
        Blog blog=findById(id);
        blog.setView(blog.getView()+n);
        return save(blog);
    }

    public Blog save(Blog blog){
        blog.setDate(Calendar.getInstance().getTime());
        return blogRepository.save(blog);
    }

    public List<Blog> getTop3PlaceByOrderByView(){
        List<Blog> list=blogRepository.getAllByOrderByView();
        List<Blog> listTop=new ArrayList<>();
        listTop.add(list.get(0));
        listTop.add(list.get(1));
        listTop.add(list.get(2));
        return listTop;
    }

    public List<Blog> findByUsarname(String username)
    {
        return blogRepository.findBlogByUsername(username);
    }
    public Blog updateLikes(long id, int n){
        Blog blog=findById(id);
        blog.setLikes(blog.getLikes()+n);
        return save(blog);
    }
    public Blog updateCommentNum(long id,int n) {
        Blog blog = findById(id);
        blog.setView(blog.getView());
        blog.setComNumber(blog.getComNumber() + n);
        return save(blog);
    }
}