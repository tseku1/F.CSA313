Total time: 0 seconds
PS C:\Users\tseku\Downloads\MeetingPlanner\MeetingPlanner> ant run-tests
Buildfile: C:\Users\tseku\Downloads\MeetingPlanner\MeetingPlanner\build.xml

clean:

compile:
    [mkdir] Created dir: C:\Users\tseku\Downloads\MeetingPlanner\MeetingPlanner\build\main
    [javac] Compiling 7 source files to C:\Users\tseku\Downloads\MeetingPlanner\MeetingPlanner\build\main

compile-tests:
    [mkdir] Created dir: C:\Users\tseku\Downloads\MeetingPlanner\MeetingPlanner\build\test
    [javac] Compiling 5 source files to C:\Users\tseku\Downloads\MeetingPlanner\MeetingPlanner\build\test

run-tests:
    [junit] Running edu.sc.csce747.MeetingPlanner.CalendarTest
    [junit] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.014 sec
    [junit] Running edu.sc.csce747.MeetingPlanner.MeetingTest
    [junit] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.006 sec
    [junit] Test edu.sc.csce747.MeetingPlanner.MeetingTest FAILED
    [junit] Running edu.sc.csce747.MeetingPlanner.OrganizationTest
    [junit] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.007 sec
    [junit] Test edu.sc.csce747.MeetingPlanner.OrganizationTest FAILED
    [junit] Running edu.sc.csce747.MeetingPlanner.PersonTest
    [junit] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.005 sec
    [junit] Test edu.sc.csce747.MeetingPlanner.PersonTest FAILED
    [junit] Running edu.sc.csce747.MeetingPlanner.RoomTest
    [junit] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.005 sec
    [junit] Test edu.sc.csce747.MeetingPlanner.RoomTest FAILED

BUILD SUCCESSFUL
Total time: 2 seconds
