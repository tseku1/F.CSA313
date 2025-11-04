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


run: ant clean compile compile-tests coverage
