package com.nymble.travelArrangements.controller;

import com.nymble.travelArrangements.entity.Activity;
import com.nymble.travelArrangements.service.ActivityService;
import com.nymble.travelArrangements.service.PassengerService;
import com.nymble.travelArrangements.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackageManagerController {

    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{packageId}/itinerary")
    public String printItinerary(@PathVariable Long packageId) {
        return travelPackageService.printItinerary(packageId);
    }

    @GetMapping("/{packageId}/passenger-list")
    public String printPassengerList(@PathVariable Long packageId) {
        return travelPackageService.printPassengerList(packageId);
    }

    @GetMapping("/{passengerId}/details")
    public String printPassengerDetails(@PathVariable Long passengerId) {
        return passengerService.printPassengerDetails(passengerId);
    }

    @GetMapping("/available")
    public List<Activity> getAvailableActivities() {
        return activityService.getAvailableActivities();
    }
}

