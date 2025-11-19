package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

public class CalendarTest {
	// Add test methods here. 
	// You are not required to write tests for all classes.
	
	@Test
	public void testAddMeeting_holiday() {
		Calendar calendar = new Calendar();
		try {
			Meeting midsommar = new Meeting(6, 26, "Midsommar");
			calendar.addMeeting(midsommar);	
			Boolean added = calendar.isBusy(6, 26, 0, 23);
			assertTrue("Midsommar should be marked as busy on the calendar",added);
		} catch(TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}
	

	@Test(expected = TimeConflictException.class)
	public void checkTimes_monthTwelve_bug() throws Exception {
		Calendar.checkTimes(12, 10, 8, 9);
	}

	@Test
	public void isBusy_bug_fullCover_returnsFalseButShouldBeTrue() throws Exception {
		Calendar cal = new Calendar();
		cal.addMeeting(new Meeting(1, 10, 10, 12));
		assertFalse(cal.isBusy(1, 10, 9, 13), "BUG: bvren hamarch baihad true true baih ystoi");
	}

	@Test
	public void addMeeting_bug_fullCover_notDetected() throws Exception {
		Calendar c = new Calendar();
		java.util.ArrayList<Person> attendees = new java.util.ArrayList<>();
		Room room = new Room("R1");

		c.addMeeting(new Meeting(1, 10, 10, 12, attendees, room, "seed"));
		c.addMeeting(new Meeting(1, 10, 9, 13, attendees, room, "covering"));
	}
}
