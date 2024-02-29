package com.nymble.travelArrangements.service;

import com.nymble.travelArrangements.entity.Activity;
import com.nymble.travelArrangements.entity.Passenger;
import com.nymble.travelArrangements.entity.TravelPackage;
import com.nymble.travelArrangements.repository.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelPackageService {

    @Autowired
    private TravelPackageRepository travelPackageRepository;
    @Autowired
    private Activity activity;

    public void addTravelPackage(TravelPackage travelPackage) {
        travelPackageRepository.save(travelPackage);
    }

    public TravelPackage getTravelPackage(Long id) {
        return travelPackageRepository.findById(id).orElse(null);
    }

    public void updateTravelPackage(Long id, TravelPackage travelPackage) {
        travelPackage.setId(id);
        travelPackageRepository.save(travelPackage);
    }

    public void deleteTravelPackage(Long id) {
        travelPackageRepository.deleteById(id);
    }

    public String printItinerary(Long packageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(packageId).orElse(null);

        if (travelPackage != null) {
            StringBuilder itineraryStringBuilder = new StringBuilder();
            itineraryStringBuilder.append("Travel Package Name: ").append(travelPackage.getName()).append("\n");

            travelPackage.getDestinations().forEach(destination -> {
                itineraryStringBuilder.append("Destination: ").append(destination.getName()).append("\n");

                destination.getActivities().forEach(activity -> {
                    itineraryStringBuilder.append("  - Activity Name: ").append(activity.getName()).append("\n");
                    itineraryStringBuilder.append("    - Cost: ").append(activity.getCost()).append("\n");
                    itineraryStringBuilder.append("    - Capacity: ").append(activity.getCapacity()).append("\n");
                    itineraryStringBuilder.append("    - Description: ").append(activity.getDescription()).append("\n");
                });
            });

            return itineraryStringBuilder.toString();
        } else {
            return "Travel Package not found!";
        }
    }
    public String printPassengerList(Long packageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(packageId).orElse(null);

        if (travelPackage != null) {
            StringBuilder passengerListStringBuilder = new StringBuilder();
            passengerListStringBuilder.append("Package Name: ").append(travelPackage.getName()).append("\n");
            passengerListStringBuilder.append("Passenger Capacity: ").append(travelPackage.getPassengerCapacity()).append("\n");

            List<Passenger> passengers = travelPackage.getPassengers();
            int numberOfPassengersEnrolled = passengers.size();

            passengerListStringBuilder.append("Number of Passengers Enrolled: ").append(numberOfPassengersEnrolled).append("\n");

            passengers.forEach(passenger ->
                    passengerListStringBuilder.append("  - Passenger Name: ").append(passenger.getName())
                            .append(", Passenger Number: ").append(passenger.getPassengerNumber()).append("\n")
            );

            return passengerListStringBuilder.toString();
        } else {
            return "Travel Package not found!";
        }
    }

}

