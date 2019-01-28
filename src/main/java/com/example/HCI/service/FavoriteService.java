package com.example.HCI.service;


import com.example.HCI.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("favoriteService")
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

}
