package modelTest;

import com.schooltimetable.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LessonTest {
    @Test
    public void testConstructing() {
        SchoolDay schoolDay = new SchoolDay(Weekday.THURSDAY);
        SchoolClass firstClass = new SchoolClass("1");
        Lesson lesson = new Lesson(schoolDay, 1, firstClass);
        assertEquals(schoolDay, lesson.getSchoolDay());
        assertEquals(firstClass, lesson.getSchoolClass());
        assertEquals(1, (int) lesson.getNumber());
        //throws Lesson lesson2 = new Lesson(schoolDay, 1, firstClass);
    }

    @Test
    public void testSetters() {
        SchoolDay schoolDay = new SchoolDay(Weekday.THURSDAY);
        SchoolClass firstClass = new SchoolClass("1");
        Lesson lesson = new Lesson(schoolDay, 1, firstClass);
        Teacher teacher = new Teacher("John", "Smith");
        lesson.setTeacher(teacher);
        assertEquals(teacher, lesson.getTeacher());
        Classroom classroom = new Classroom("44");
        lesson.setClassroom(classroom);
        assertEquals(classroom, lesson.getClassroom());
    }
}