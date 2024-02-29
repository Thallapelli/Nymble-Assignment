package com.nymble.travelArrangements.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Activity> activities;
    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;

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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activityList=" + activities +
                ", travelPackage=" + travelPackage +
                '}';
    }

    public Destination(Long id, String name, List<Activity> activities, TravelPackage travelPackage) {
        this.id = id;
        this.name = name;
        this.activities = activities;
        this.travelPackage = travelPackage;
    }

    public Destination() {
    }
}
