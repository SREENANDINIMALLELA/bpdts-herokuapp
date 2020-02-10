package com.test.bpdts.heruokuapp.components;

import org.springframework.stereotype.Component;

@Component
public
class DistanceCalculator {
    public Double distance(Double lat1, Double lon1, Double lat2, Double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0.0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            return (dist * 3958.8);
        }
    }
}
