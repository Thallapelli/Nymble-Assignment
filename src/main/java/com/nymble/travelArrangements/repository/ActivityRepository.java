package com.nymble.travelArrangements.repository;

import com.nymble.travelArrangements.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {
    @Query("SELECT a FROM Activity a WHERE a.capacity > SIZE(a.passengers)")
    List<Activity> findAvailableActivities();
}
