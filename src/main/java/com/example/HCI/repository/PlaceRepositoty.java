package com.example.HCI.repository;

import com.example.HCI.model.Blog;
import com.example.HCI.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlaceRepositoty extends JpaRepository<Place,Long> {
    Place findById(long id);
    List<Place> getAllByOrderByView();
    List<Place> findByUsarname(String username);

}
