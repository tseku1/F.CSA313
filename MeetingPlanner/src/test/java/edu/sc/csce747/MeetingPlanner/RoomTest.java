package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    private Room room;
    // private Meeting meeting;

    @Before
    public void setup() throws TimeConflictException {
        room = new Room("101");
        java.util.ArrayList<Person> attendees = new java.util.ArrayList<>();
        attendees.add(new Person("Alice"));
        Meeting meeting = new Meeting(5, 10, 9, 11, attendees, room, "Team Meeting");
        room.addMeeting(meeting);
    }

    @Test
    public void testConstructorAndGetID() {
        assertEquals("101", room.getID());
        Room r = new Room();
        assertEquals("", r.getID());
    }

   @Test
    public void testAddMeetingAndIsBusy() throws TimeConflictException {
        assertTrue(room.isBusy(5, 10, 9, 10));
        assertTrue(room.isBusy(5, 10, 10, 11));
        assertTrue(room.isBusy(5, 10, 11, 12));
    }

    @Test
    public void testRemoveMeeting() throws TimeConflictException {
        room.removeMeeting(5, 10, 0);
        assertFalse(room.isBusy(5, 10, 9, 11));
    }

    @Test
    public void testPrintAgenda() {
        String agendaMonth = room.printAgenda(5);
        String agendaDay = room.printAgenda(5, 10);
        assertTrue(agendaMonth.contains("Team Meeting"));
        assertTrue(agendaDay.contains("Team Meeting"));
    }

    @Test
    public void testAddConflictingMeetingThrowsException() {
        try {
            Meeting conflict = new Meeting(5, 10, 10, 12, null, null, "Conflict Meeting");
            room.addMeeting(conflict);
            fail("Expected TimeConflictException due to overlapping meeting");
        } catch (TimeConflictException e) {
            assertTrue(e.getMessage().contains("Conflict for room 101"));
        }
    }
}
