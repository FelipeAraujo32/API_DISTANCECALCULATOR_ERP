package com.distancecalculator.distance_calculator_api.service.distanceprocessing;

import org.springframework.stereotype.Service;

import com.distancecalculator.distance_calculator_api.dto.ApiCepClientDTO;
import com.distancecalculator.distance_calculator_api.models.DistanceModel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Service
public class HaversineDistanceCalculator {

    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public HaversineDistanceCalculator() {
    }

    public DistanceModel calculateDistance(ApiCepClientDTO product, ApiCepClientDTO customer) {
        double lat1Rad = Math.toRadians(product.getLat());
        double lon1Rad = Math.toRadians(product.getLng());
        double lat2Rad = Math.toRadians(customer.getLat());
        double lon2Rad = Math.toRadians(customer.getLng());

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                        * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        DistanceModel distanceModel = new DistanceModel();
        distanceModel.setDistance(distance);
        distanceModel.setUnit("KM");
        return distanceModel;
    }

    public double formattedDistance(double distance) {
        String fomorttedString = df.format(distance);
        double formattedDistanceDouble = Double.parseDouble(fomorttedString);
        return formattedDistanceDouble;
    }
}
