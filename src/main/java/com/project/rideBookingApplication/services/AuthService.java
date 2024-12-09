package com.project.rideBookingApplication.services;

import com.project.rideBookingApplication.dto.DriverDto;
import com.project.rideBookingApplication.dto.SignUpDto;
import com.project.rideBookingApplication.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signup(SignUpDto signUpDto);

    DriverDto onboardNewDriver(Long UserId, String vehicleId);

    String refreshToken(String refreshToken);

}
