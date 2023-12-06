package tn.esprit.eventsproject;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.controllers.EventRestController;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.services.IEventServices;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventRestControllerTest {

    @InjectMocks
    private EventRestController eventRestController;

    @Mock
    private IEventServices eventServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addEvent() {

        Event eventRequest = new Event();
        eventRequest.setDescription("Test Event");
        eventRequest.setDateDebut(LocalDate.of(2024, 5, 12));
        eventRequest.setDateFin(LocalDate.of(2024, 6, 15));
        eventRequest.setCout(100);


        when(eventServices.addAffectEvenParticipant(Mockito.any())).thenReturn(eventRequest);


        Event result = eventRestController.addEvent(eventRequest);


        assertNotNull(result);
        assertEquals("Test Event", result.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), result.getDateDebut());
        assertEquals(LocalDate.of(2023, 1, 5), result.getDateFin());
        assertEquals(100, result.getCout());

        verify(eventServices).addAffectEvenParticipant(Mockito.any());

        System.err.println("addEvent: SUCCESS");
    }


}
