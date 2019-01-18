import com.schooltimetable.dao.*;
import com.schooltimetable.model.*;
import com.schooltimetable.service.SessionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class LessonDaoComplexTest {
    private final ClassroomDao classroomDao = new ClassroomDao();

    private final LessonDao lessonDao = new LessonDao();

    private final SchoolClassDao schoolClassDao = new SchoolClassDao();

    private final SchoolDayDao schoolDayDao = new SchoolDayDao();

    private final SubjectDao subjectDao = new SubjectDao();

    private final TeacherDao teacherDao = new TeacherDao();

    @Before
    public void before() {
        SessionService.openSession();
    }

    @After
    public void after() {
        for (Dao dao : Arrays.asList(classroomDao, lessonDao, schoolClassDao, schoolDayDao, subjectDao, teacherDao))
            dao.deleteAll();
        SessionService.closeSession();
    }

    @Test
    public void createLessonTest() {
        Optional<SchoolDay> schoolDay = schoolDayDao.create(Weekday.THURSDAY);
        Optional<SchoolClass> schoolClass = schoolClassDao.create("3A");
        assertTrue(schoolDay.isPresent());
        assertTrue(schoolClass.isPresent());
        Optional<Lesson> lesson1 = lessonDao.create(schoolDay.get(), 1, schoolClass.get());
        checkLesson(lesson1);
        Optional<Lesson> lesson2 = lessonDao.create(schoolDay.get(), 1, schoolClass.get());
        assertFalse(lesson2.isPresent());
    }

    @Test
    public void addLessonPropertiesTest() {
        Optional<SchoolDay> schoolDay = schoolDayDao.create(Weekday.THURSDAY);
        Optional<SchoolClass> schoolClass = schoolClassDao.create("3A");
        assertTrue(schoolDay.isPresent());
        assertTrue(schoolClass.isPresent());
        Optional<Lesson> lesson = lessonDao.create(schoolDay.get(), 1, schoolClass.get());
        checkLesson(lesson);
        Optional<Teacher> teacher = teacherDao.create("John", "Doe");
        Optional<Subject> subject = subjectDao.create("Maths");
        assertTrue(subject.isPresent());
        assertTrue(teacher.isPresent());
        checkLesson(lesson);
        Lesson lesson1 = lesson.get();
        Teacher teacher1 = teacher.get();
        Subject subject1 = subject.get();
        lessonDao.setSubject(lesson1, subject1);
        lessonDao.setTeacher(lesson1, teacher1);
        assertEquals(teacher1, lesson1.getTeacher());
        assertEquals(subject1, lesson1.getSubject());
        assertFalse(teacher1.getLessons().isEmpty());
        assertFalse(subject1.getLessons().isEmpty());
        teacher = teacherDao.create("Jane", "Doe");
        Teacher teacher2 = teacher.get();
        lesson1.setTeacher(teacher2);
        assertTrue(teacher1.getLessons().isEmpty());
        assertFalse(teacher2.getLessons().isEmpty());
    }

    @Test
    public void deleteLessonTest() {
        Optional<SchoolDay> schoolDay = schoolDayDao.create(Weekday.THURSDAY);
        Optional<SchoolClass> schoolClass = schoolClassDao.create("3A");
        assertTrue(schoolDay.isPresent());
        assertTrue(schoolClass.isPresent());
        Optional<Lesson> lesson = lessonDao.create(schoolDay.get(), 1, schoolClass.get());
        checkLesson(lesson);
        Optional<Teacher> teacher = teacherDao.create("John", "Doe");
        Optional<Subject> subject = subjectDao.create("Maths");
        assertTrue(subject.isPresent());
        assertTrue(teacher.isPresent());
        checkLesson(lesson);
        Lesson lesson1 = lesson.get();
        Teacher teacher1 = teacher.get();
        Subject subject1 = subject.get();
        lessonDao.setSubject(lesson1, subject1);
        lessonDao.setTeacher(lesson1, teacher1);
        lessonDao.deleteOne(lesson1);
        System.out.println(lesson1);
        System.out.println(teacher1.getLessons());
        assertTrue(teacher1.getLessons().isEmpty());
        assertTrue(subject1.getLessons().isEmpty());
    }

    private void checkLesson(Optional<Lesson> lesson) {
        assertTrue(lesson.isPresent());
        lesson.ifPresent(l -> {
            assertTrue(l.getId() > 0);
            assertNotNull(l.getSchoolDay());
            assertNotNull(l.getNumber());
            assertNotNull(l.getSchoolClass());
        });
    }
}
