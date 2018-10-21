package com.example.HCI.service;


import com.example.HCI.model.Comment;
import com.example.HCI.model.Place;
import com.example.HCI.repository.PlaceRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("PlaceService")
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
        place.setDate(Calendar.getInstance().getTime());
        return placeRepositoty.save(place);
    }

    public List<Place> getTop3PlaceByOrderByView(){
        List<Place> list=placeRepositoty.getAllByOrderByView();
        List<Place> listTop=new ArrayList<>();
        listTop.add(list.get(0));
        listTop.add(list.get(1));
        listTop.add(list.get(2));
        return listTop;
    }

    public List<Place> findByUsarname(String username){
        return placeRepositoty.findByUsarname(username);
    }

}
