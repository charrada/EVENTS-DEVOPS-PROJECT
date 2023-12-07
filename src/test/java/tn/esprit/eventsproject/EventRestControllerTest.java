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
        assertEquals(LocalDate.of(2024, 5, 12), result.getDateDebut());
        assertEquals(LocalDate.of(2024, 6, 15), result.getDateFin());
        assertEquals(100, result.getCout());

        verify(eventServices).addAffectEvenParticipant(Mockito.any());

        System.err.println("addEvent: SUCCESS");
    }


    @Test
    void addEventPart() {
        Event event = new Event();
        event.setIdEvent(1);

        int participantId = 123;
        when(eventServices.addAffectEvenParticipant(Mockito.any(), Mockito.eq(participantId))).thenReturn(event);

        Event result = eventRestController.addEventPart(new Event(), participantId);

        assertNotNull(result);
        assertEquals(1, result.getIdEvent());
        System.err.println("addEventPart: SUCCESS");


        verify(eventServices).addAffectEvenParticipant(Mockito.any(), Mockito.eq(participantId));
    }

    @Test
    void addAffectLog() {
        Logistics logistics = new Logistics();
        logistics.setIdLog(1);
        String descriptionEvent = "SampleEvent";
        when(eventServices.addAffectLog(Mockito.any(), Mockito.eq(descriptionEvent))).thenReturn(logistics);

        Logistics result = eventRestController.addAffectLog(new Logistics(), descriptionEvent);

        assertNotNull(result);
        assertEquals(1, result.getIdLog());
        System.err.println("addAffectLog: SUCCESS");


        verify(eventServices).addAffectLog(Mockito.any(), Mockito.eq(descriptionEvent));
    }

    @Test
    void getLogistiquesDates() {
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = LocalDate.now().plusDays(1);
        Logistics logistics = new Logistics();
        logistics.setIdLog(1);
        List<Logistics> expectedLogistics = Arrays.asList(logistics);
        when(eventServices.getLogisticsDates(dateDebut, dateFin)).thenReturn(expectedLogistics);

        List<Logistics> result = eventRestController.getLogistiquesDates(dateDebut, dateFin);

        assertNotNull(result);
        assertEquals(expectedLogistics.size(), result.size());
        assertEquals(expectedLogistics.get(0).getIdLog(), result.get(0).getIdLog());
        System.err.println("getLogistiquesDates: SUCCESS");
    }

    @Test
    void addParticipant() {
        Participant participant = new Participant();
        participant.setIdPart(1);
        when(eventServices.addParticipant(Mockito.any())).thenReturn(participant);

        Participant result = eventRestController.addParticipant(new Participant());

        assertNotNull(result);
        assertEquals(1, result.getIdPart());
        System.err.println("addParticipant: SUCCESS");

        verify(eventServices).addParticipant(Mockito.any());
    }

    @AfterAll
    static void displaySuccessMessage() {
        System.out.println("All tests succeeded");
    }

}
