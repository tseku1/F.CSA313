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

# Лабораторийн ажил №08  
## Цагаан хайрцагны тест (White-box Structural Testing)

### 🎯 Зорилго
Өмнөх лабораторийн ажил №07 дээр уулзалт төлөвлөлтийн програмд **нэгжийн тест (Unit test)** бичсэн.  
Энэ удаад кодын **бүтцийн түвшинд** тест бичиж, **statement** болон **branch coverage**-ийг бүрэн хангах зорилготой.

---

### ⚙️ Ашигласан орчин
| Тохиргоо | Үзүүлэлт |
|-----------|-----------|
| IDE | VS Code |
| Build tool | Apache Ant |
| Test framework | JUnit 4 |
| Coverage tool | JaCoCo (Ant plugin) |
| JDK | OpenJDK 17 |
| Report path | `coverage/report/index.html` |

---

### 🧩 Хийсэн ажлын дараалал

1. **JaCoCo coverage** интеграц хийсэн  
   `build.xml` файлд дараах target нэмэв:
   ```xml
   <target name="coverage" depends="compile-tests">
       <mkdir dir="coverage"/>
       <jacoco:coverage destfile="coverage/jacoco.exec">
           <junit printsummary="yes" haltonfailure="false" fork="true">
               <formatter type="plain" usefile="false"/>
               <classpath>
                   <pathelement path="${build}/main"/>
                   <pathelement path="${build}/test"/>
                   <fileset dir="${lib}">
                       <include name="**/*.jar"/>
                   </fileset>
               </classpath>
               <batchtest>
                   <fileset dir="${src.test}">
                       <include name="**/*Test.java"/>
                   </fileset>
               </batchtest>
           </junit>
       </jacoco:coverage>

       <jacoco:report>
           <executiondata>
               <file file="coverage/jacoco.exec"/>
           </executiondata>
           <structure name="MeetingPlanner">
               <classfiles>
                   <fileset dir="${build}/main">
                       <exclude name="**/PlannerInterface.class"/>
                       <exclude name="**/TimeConflictException.class"/>
                   </fileset>
               </classfiles>
               <sourcefiles encoding="UTF-8">
                   <fileset dir="${src.main}"/>
               </sourcefiles>
           </structure>
           <html destdir="coverage/report"/>
       </jacoco:report>
   </target>
   

## 🧪 Хийсэн тестүүд ба үр дүн

### 1) Нэмсэн / шинэчилсэн white-box тестүүд

**CalendarTest**
- `checkTimes_monthTwelve_bug` — `mMonth == 12` үед буруу `TimeConflictException` шидэж байгааг барьсан.
- `isBusy_bug_fullCover_returnsFalseButShouldBeTrue` — бүрэн давхцах интервал (existing 10–12, new 9–13) үед `isBusy` буруу ажиллаж байгааг харуулсан.
- `addMeeting_bug_fullCover_notDetected` — бүрэн давхцах үед `addMeeting` зөрчил илрүүлж `TimeConflictException` шидэх ёстой ч илрүүлэхгүй байгааг барьсан.
- `testAddMeeting_holiday` — бүхэл өдөр блоклох (0–23) уулзалт нэмэх ба завгүй байдлыг шалгасан.

**PersonTest**
- `testAddMeetingAndIsBusy` — эндпойнт **inclusive** гэдгийг харгалзан 11–12-ыг **busy=true** гэж баталгаажуулсан.
- `testAddConflictingMeetingThrowsException` — давхцах уулзалт нэмэхэд `TimeConflictException`-ийн мессеж **“Conflict for attendee …”** префикс зөв гарч буйг шалгасан.
- Бусад: `testRemoveMeeting`, `testPrintAgenda`, `testConstructorAndGetName`.

**RoomTest**
- `testAddMeetingAndIsBusy` — эндпойнт inclusive boundary нөхцөлийг баталгаажуулсан.
- `testAddConflictingMeetingThrowsException` — `TimeConflictException` мессеж **“Conflict for room …”** префикс зөв.
- Бусад: `testRemoveMeeting`, `testPrintAgenda`, `testConstructorAndGetID`.

**MeetingTest**
- `testDefaultConstructor` — `attendees`, `room`, `description` нь **null** байдаг precondition-ийг баталгаажуулсан.
- `testFullConstructorAndAttendees`, `testToString`, `testSettersAndGetters` — `toString()` NPE-гүй ажиллахын тулд `room` болон `attendees`-ийг **null биш** өгч, найруулгыг шалгасан.
- `testDayBlockingConstructor`, `testDescriptionConstructor`, `testDetailedConstructor`.

**OrganizationTest**
- `testGetEmployeeSuccess` / `testGetEmployeeFail`
- `testGetRoomSuccess` / `testGetRoomFail`
- `testEmployeesListNotEmpty`, `testRoomsListNotEmpty`

> **Тэмдэглэл:** `PlannerInterface` (CLI main) болон `TimeConflictException` классуудыг **coverage-ээс хассан**.

---

### 2) Илэрсэн дефектүүд (white-box-оор илэрсэн)

1. **`Calendar.checkTimes(...)` сар шалгалтын алдаа**  
   Одоогийн код: `if (mMonth < 1 || mMonth >= 12)` → **12-р сарыг** “байхгүй” гэж буруу үздэг.  
   **Зөв**: `if (mMonth < 1 || mMonth > 12)`.

2. **Бүрэн давхцах интервалыг илрүүлэхгүй (isBusy/addMeeting)**  
   Одоогийн логик зөвхөн “шинэ уулзалтын **start** эсвэл **end** нь байгаа уулзалтын интервалын **дотор** байвал” гэж шалгадаг.  
   **Full cover** (ж: existing 10–12, new 9–13) үед огтлолцол байгаа ч **илрүүлэхгүй**.  
   **Зөв шалгалтын хэлбэр (санал):** `if (newStart <= oldEnd && newEnd >= oldStart)`.

Эдгээрийг бид **код өөрчлөхгүй**, зөвхөн **тестээр илрүүлж** тайлагнасан.

---

### 3) Тестийн гүйлтийн дүн

Ant + JaCoCo-гоор иж бүрэн гүйлгэв:
```bash
ant clean compile compile-tests coverage

