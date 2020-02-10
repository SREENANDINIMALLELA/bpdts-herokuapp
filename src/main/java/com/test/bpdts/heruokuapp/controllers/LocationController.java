package com.test.bpdts.heruokuapp.controllers;

import com.test.bpdts.heruokuapp.components.DistanceCalculator;
import com.test.bpdts.heruokuapp.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LocationController {
    private static Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DistanceCalculator distanceCalculator;

    @RequestMapping(value = "/city/{name}")
    public List<User> getUsers(@PathVariable("name") String name) {
        List<User> cityUsers = Arrays.asList(restTemplate.getForEntity("https://bpdts-test-app.herokuapp.com/city/" + name + "/users", User[].class).getBody());
        List<User> allUsers = Arrays.asList(restTemplate.getForEntity("https://bpdts-test-app.herokuapp.com/users", User[].class).getBody());
        long start = System.currentTimeMillis();
        List<User> doubleList = cityUsers.parallelStream().flatMap(it -> allUsers.stream().filter(ti -> distanceCalculator.distance(it.getLatitude(), it.getLongitude(), ti.getLatitude(), ti.getLongitude()) <= 50)).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        long timeTaken = end - start;
        logger.info("Processed in ms: {}",timeTaken);
        return doubleList;

    }
}
