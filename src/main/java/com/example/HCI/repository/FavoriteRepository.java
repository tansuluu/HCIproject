package com.example.HCI.repository;

import com.example.HCI.model.Comment;
import com.example.HCI.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("favoriteRepository")
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

}
