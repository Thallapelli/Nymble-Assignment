package com.nymble.travelArrangements.service;

import com.nymble.travelArrangements.entity.Passenger;
import com.nymble.travelArrangements.entity.PassengerType;
import com.nymble.travelArrangements.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public String printPassengerDetails(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId).orElse(null);

        if (passenger != null) {
            StringBuilder passengerDetailsStringBuilder = new StringBuilder();
            passengerDetailsStringBuilder.append("Passenger Name: ").append(passenger.getName()).append("\n");
            passengerDetailsStringBuilder.append("Passenger Number: ").append(passenger.getPassengerNumber()).append("\n");

            if (passenger.getPassengerType().equals(PassengerType.STANDARD) || passenger.getPassengerType().equals(PassengerType.GOLD)) {
                passengerDetailsStringBuilder.append("Balance: ").append(passenger.getBalance()).append("\n");
            }

            passengerDetailsStringBuilder.append("Activities Signed Up For: \n");

            passenger.getActivities().forEach(activity -> {
                passengerDetailsStringBuilder.append("  - Activity Name: ").append(activity.getName()).append("\n");
                passengerDetailsStringBuilder.append("    - Destination: ").append(activity.getDestination().getName()).append("\n");
                passengerDetailsStringBuilder.append("    - Price Paid: ").append(passenger.getPricePaidForActivity()).append("\n");
            });

            return passengerDetailsStringBuilder.toString();
        } else {
            return "Passenger not found!";
        }
    }
}
