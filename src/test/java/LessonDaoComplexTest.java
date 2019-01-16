import com.schooltimetable.dao.*;
import com.schooltimetable.model.Lesson;
import com.schooltimetable.model.SchoolClass;
import com.schooltimetable.model.SchoolDay;
import com.schooltimetable.model.Weekday;
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
    }

    @Test
    public void addLessonPropertiesTest() {

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
