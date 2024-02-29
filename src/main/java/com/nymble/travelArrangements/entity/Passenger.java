package com.nymble.travelArrangements.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String passengerNumber;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passengerNumber='" + passengerNumber + '\'' +
                ", passengerType=" + passengerType +
                ", travelPackage=" + travelPackage +
                ", balance=" + balance +
                ", activities=" + activities +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;
    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private List<Activity> activities;

    private double balance;

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
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

    public String getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(String passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Passenger(Long id, String name, String passengerNumber, PassengerType passengerType, TravelPackage travelPackage, double balance, List<Activity> activities) {
        this.id = id;
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.passengerType = passengerType;
        this.travelPackage = travelPackage;
        this.balance = balance;
        this.activities = activities;
    }

    public Passenger() {
    }
    public double getPricePaidForActivity() {
        switch (passengerType) {
            case STANDARD:
                return activity.getCost();
            case GOLD:
                return activity.getCost() - (activity.getCost() * 0.10);
            case PREMIUM:
                return 0.0;
            default:
                return 0.0;
        }
    }

    public void signUpForActivity(Activity activity) {
        double pricePaid = getPricePaidForActivity();
        if (balance >= pricePaid) {
            activities.add(activity);
            balance -= pricePaid;
        } else {
            System.out.println("Insufficient balance to sign up for the activity.");
        }
    }
}
