package com.example.HCI.service;


import com.example.HCI.model.Place;
import com.example.HCI.repository.PlaceRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("placeService")
public class PlaceService {

    @Autowired
    PlaceRepositoty placeRepositoty;

    public Place findById(long id){
        return placeRepositoty.findById(id);
    }
    public Place updateView(long id){
        Place place=findById(id);
        place.setView(place.getView()+1);
        return place;
    }

    public Place save(Place place){
        return placeRepositoty.save(place);
    }

}
