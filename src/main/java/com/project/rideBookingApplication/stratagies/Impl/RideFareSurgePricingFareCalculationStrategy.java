package com.project.rideBookingApplication.stratagies.Impl;

import com.project.rideBookingApplication.entities.RideRequest;
import com.project.rideBookingApplication.services.DistanceService;
import com.project.rideBookingApplication.stratagies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance= distanceService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_PRICE;
    }
}
