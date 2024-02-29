package com.nymble.travelArrangements.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double cost;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    public Passenger getPassenger() {
        return passenger;
    }
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Activity(Long id, String name, String description, double cost, int capacity, Destination destination) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
    }

    public Activity() {
    }
    @Transient
    private double totalPaidByPassengers;

    public boolean isSpaceAvailable() {
        return passenger == null || passenger.getPassengerType() == PassengerType.PREMIUM;
    }

    public void signUpPassenger(Passenger passenger) {
        // Implement logic to sign up passenger for the activity
        if (isSpaceAvailable()) {
            this.passenger = passenger;
            passenger.signUpForActivity(this);
        } else {
            // Handle no space available
            System.out.println("No space available for this activity.");
        }
    }

    public double calculateTotalPricePaidByPassengers() {
        if (passengers != null) {
            return passengers.stream()
                    .mapToDouble(Passenger::getPricePaidForActivity)
                    .sum();
        }
        return 0.0;
    }
}
