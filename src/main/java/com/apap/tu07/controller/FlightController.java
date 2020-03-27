package com.apap.tu07.controller;

import java.util.List;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tu07.model.FlightModel;
import com.apap.tu07.model.PilotModel;
import com.apap.tu07.service.FlightService;
import com.apap.tu07.service.PilotService;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;

   
    @PostMapping(value="/add")
    public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
    	
    	return flightService.addFlight(flight);
    }
    
    @PutMapping(value= "/update/{flightId}")
    public String updatePilotSubmit(@PathVariable(value="flightId") long id, 
    								@RequestParam("destination") String destination,
    								@RequestParam("origin") String origin,
    								@RequestParam("time") Date time) {
    	FlightModel flight = flightService.flightById(id);
    	if(flight.equals(null)) {
    		return "Couldn't find your flight";
    	}
    	flight.setOrigin(origin);
    	flight.setDestination(destination);
    	flight.setTime(time);
    	flightService.updateFlight(flight);
    	
    	return "flight update success";
    }

    @GetMapping(value="/view/{flightNumber}")
    public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
    	FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber).get();
    	return flight;
    }
    
   	
    @GetMapping(value = "/all")
    public List<FlightModel> flightViewAll(){
    	List<FlightModel> flight = flightService.getAllFlight();
    	return flight;
    	
    }
    
    @DeleteMapping(value = "/{flightId}")
    public String deleteFlightSubmit(@PathVariable("flightId") long id) {
    	flightService.removeFlight(id);
    	return "flight has been deleted";
    }
    
    
}