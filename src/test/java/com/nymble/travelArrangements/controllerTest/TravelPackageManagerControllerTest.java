package com.nymble.travelArrangements.controllerTest;

import com.nymble.travelArrangements.controller.TravelPackageManagerController;
import com.nymble.travelArrangements.entity.Activity;
import com.nymble.travelArrangements.service.ActivityService;
import com.nymble.travelArrangements.service.PassengerService;
import com.nymble.travelArrangements.service.TravelPackageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@WebMvcTest(TravelPackageManagerController.class)
public class TravelPackageManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TravelPackageService travelPackageService;

    @Mock
    private PassengerService passengerService;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private TravelPackageManagerController travelPackageController;

    @Test
    public void testPrintItinerary() throws Exception {
        when(travelPackageService.printItinerary(anyLong())).thenReturn("Mocked Itinerary");

        mockMvc.perform(get("/travel-packages/1/itinerary"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPrintPassengerList() throws Exception {
        when(travelPackageService.printPassengerList(anyLong())).thenReturn("Mocked Passenger List");

        mockMvc.perform(get("/travel-packages/1/passenger-list"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPrintPassengerDetails() throws Exception {
        when(passengerService.printPassengerDetails(anyLong())).thenReturn("Mocked Passenger Details");

        mockMvc.perform(get("/passengers/1/details"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAvailableActivities() throws Exception {
        when(activityService.getAvailableActivities()).thenReturn(Arrays.asList(new Activity(), new Activity()));
        mockMvc.perform(get("/activities/available"))
                .andExpect(status().isOk());
    }
}
