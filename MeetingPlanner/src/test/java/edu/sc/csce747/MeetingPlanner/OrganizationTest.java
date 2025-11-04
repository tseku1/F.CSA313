package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class OrganizationTest {

    private Organization org;

    @Before
    public void setup() {
        org = new Organization();
    }

    @Test
    public void testEmployeesListNotEmpty() {
        assertNotNull("Employees list should not be null", org.getEmployees());
        assertEquals(5, org.getEmployees().size());
    }

    @Test
    public void testRoomsListNotEmpty() {
        assertNotNull("Rooms list should not be null", org.getRooms());
        assertEquals(5, org.getRooms().size());
    }

    @Test
    public void testGetEmployeeSuccess() throws Exception {
        Person p = org.getEmployee("John Rose");
        assertNotNull(p);
        assertEquals("John Rose", p.getName());
    }

    @Test
    public void testGetEmployeeFail() {
        try {
            org.getEmployee("Nonexistent Employee");
            fail("Expected Exception for non-existent employee");
        } catch (Exception e) {
            assertEquals("Requested employee does not exist", e.getMessage());
        }
    }

    @Test
    public void testGetRoomSuccess() throws Exception {
        Room r = org.getRoom("2A03");
        assertNotNull(r);
        assertEquals("2A03", r.getID());
    }

    @Test
    public void testGetRoomFail() {
        try {
            org.getRoom("NonexistentRoom");
            fail("Expected Exception for non-existent room");
        } catch (Exception e) {
            assertEquals("Requested room does not exist", e.getMessage());
        }
    }
}
