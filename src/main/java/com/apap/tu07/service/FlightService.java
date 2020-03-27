package com.apap.tu07.service;

import java.util.Optional;
import java.util.List;

import com.apap.tu07.model.FlightModel;
import com.apap.tu07.model.PilotModel;

/**
 * FlightService
 */
public interface FlightService {
	Optional<FlightModel> getFlightDetailByFlightNumber(String flightNumber);
	
    FlightModel addFlight(FlightModel flight);
    
    void updateFlight(FlightModel flightModel);
    
    FlightModel flightById(long id);
    
    void removeFlight(long id);
    
    List<FlightModel> getAllFlight();
}