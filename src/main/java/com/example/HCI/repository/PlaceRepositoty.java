package com.example.HCI.repository;

import com.example.HCI.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("placeRepositoty")
public interface PlaceRepositoty extends JpaRepository<Place,Long> {

}
