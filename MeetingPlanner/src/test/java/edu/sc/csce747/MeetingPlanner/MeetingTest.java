package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class MeetingTest {

    private Person alice;
    private Person bob;

    @Before
    public void setup() {
        alice = new Person("Alice");
        bob = new Person("Bob");
    }

    // @Test
    // public void testDefaultConstructor() {
    //     Meeting m = new Meeting();
    //     assertNotNull("Attendees list should be null initially", m.getAttendees());
    //     assertNull("Room should be null", m.getRoom());
    //     assertNull("Description should be null", m.getDescription());
    // }
    @Test
    public void testDefaultConstructor() {
        Meeting m = new Meeting();
        assertNull("Attendees list should be null initially", m.getAttendees());
        assertNull("Room should be null", m.getRoom());
        assertNull("Description should be null", m.getDescription());
    }

    @Test
    public void testDayBlockingConstructor() {
        Meeting m = new Meeting(5, 10);
        assertEquals(5, m.getMonth());
        assertEquals(10, m.getDay());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
    }

    @Test
    public void testDescriptionConstructor() {
        Meeting m = new Meeting(6, 15, "Team Meeting");
        assertEquals("Team Meeting", m.getDescription());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
    }

    @Test
    public void testDetailedConstructor() {
        Meeting m = new Meeting(7, 20, 9, 11);
        assertEquals(7, m.getMonth());
        assertEquals(20, m.getDay());
        assertEquals(9, m.getStartTime());
        assertEquals(11, m.getEndTime());
    }

    @Test
    public void testFullConstructorAndAttendees() {
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(alice);
        Room room = new Room("101");

        Meeting m = new Meeting(8, 5, 10, 12, attendees, room, "Project Review");
        assertEquals("Project Review", m.getDescription());
        assertEquals(room, m.getRoom());
        assertTrue(m.getAttendees().contains(alice));

        m.addAttendee(bob);
        assertTrue(m.getAttendees().contains(bob));

        m.removeAttendee(alice);
        assertFalse(m.getAttendees().contains(alice));
    }

    @Test
    public void testToString() {
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(alice);
        Room room = new Room("202");
        Meeting m = new Meeting(9, 1, 14, 16, attendees, room, "Planning");

        String output = m.toString();
        assertTrue(output.contains("9/1"));
        assertTrue(output.contains("14 - 16"));
        assertTrue(output.contains("202"));
        assertTrue(output.contains("Planning"));
        assertTrue(output.contains("Alice"));
    }

    @Test
    public void testSettersAndGetters() {
        Meeting m = new Meeting();
        m.setMonth(12);
        m.setDay(31);
        m.setStartTime(8);
        m.setEndTime(10);
        m.setDescription("End of Year");

        Room room = new Room("303");
        m.setRoom(room);

        assertEquals(12, m.getMonth());
        assertEquals(31, m.getDay());
        assertEquals(8, m.getStartTime());
        assertEquals(10, m.getEndTime());
        assertEquals("End of Year", m.getDescription());
        assertEquals(room, m.getRoom());
    }
}
