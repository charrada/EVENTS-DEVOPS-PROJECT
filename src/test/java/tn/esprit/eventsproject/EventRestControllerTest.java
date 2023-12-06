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
        Event event = new Event();
        event.setIdEvent(1);
        when(eventServices.addAffectEvenParticipant(Mockito.any())).thenReturn(event);
        Event result = eventRestController.addEvent(new Event());
        assertNotNull(result);
        assertEquals(1, result.getIdEvent());
        verify(eventServices).addAffectEvenParticipant(Mockito.any());
        System.err.println("addEvent: SUCCESS");
    }

}
