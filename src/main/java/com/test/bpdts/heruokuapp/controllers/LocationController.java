package com.test.bpdts.heruokuapp.controllers;

import com.test.bpdts.heruokuapp.components.DistanceCalculator;
import com.test.bpdts.heruokuapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DistanceCalculator distanceCalculator;

    @RequestMapping(value = "/")
    public List<User> getUsers() {
        List<User> londonUsers = Arrays.asList(restTemplate.getForEntity("https://bpdts-test-app.herokuapp.com/city/London/users", User[].class).getBody());
        List<User> allUsers = Arrays.asList(restTemplate.getForEntity("https://bpdts-test-app.herokuapp.com/users", User[].class).getBody());
        List<User> doubleList = londonUsers.stream().flatMap(it -> allUsers.stream().filter(ti -> distanceCalculator.distance(it.getLatitude(), it.getLongitude(), ti.getLatitude(), ti.getLongitude()) <= 50)).collect(Collectors.toList());

        return doubleList;

    }
}
