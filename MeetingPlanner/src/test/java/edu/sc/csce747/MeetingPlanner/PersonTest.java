package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

    private Person alice;
    private Meeting meeting;

    @Before
    public void setup() throws TimeConflictException {
        alice = new Person("Alice");
        java.util.ArrayList<Person> attendees = new java.util.ArrayList<>();
        attendees.add(alice);
        Room room = new Room("R-1");
        meeting = new Meeting(5, 10, 9, 11, attendees, room, "Team Meeting");
        alice.addMeeting(meeting);
    }

    @Test
    public void testConstructorAndGetName() {
        assertEquals("Alice", alice.getName());
        Person bob = new Person();
        assertEquals("", bob.getName());
    }

    @Test
    public void testAddMeetingAndIsBusy() throws TimeConflictException {
        assertTrue(alice.isBusy(5, 10, 9, 10));
        assertTrue(alice.isBusy(5, 10, 10, 11));
        assertTrue(alice.isBusy(5, 10, 11, 12));
    }

    @Test
    public void testRemoveMeeting() throws TimeConflictException {
        alice.removeMeeting(5, 10, 0);
        assertFalse(alice.isBusy(5, 10, 9, 11));
    }

    @Test
    public void testPrintAgenda() {
        String agendaMonth = alice.printAgenda(5);
        String agendaDay = alice.printAgenda(5, 10);
        assertTrue(agendaMonth.contains("Team Meeting"));
        assertTrue(agendaDay.contains("Team Meeting"));
    }

    @Test
    public void testAddConflictingMeetingThrowsException() {
        try {
            Meeting conflict = new Meeting(5, 10, 10, 12, null, null, "Conflict Meeting");
            alice.addMeeting(conflict);
            fail("Expected TimeConflictException due to overlapping meeting");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for attendee Alice"));
        }
    }
}
