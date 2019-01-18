import com.schooltimetable.dao.SubjectDao;
import com.schooltimetable.dao.TeacherDao;
import com.schooltimetable.model.Teacher;
import com.schooltimetable.model.Subject;
import com.schooltimetable.service.SessionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class SubjectTeacherIntTest {
    private final SubjectDao subjectDao = new SubjectDao();

    private final TeacherDao teacherDao = new TeacherDao();

    @Before
    public void before() {
        SessionService.openSession();
    }

    @After
    public void after() {
        teacherDao.deleteAll();
        subjectDao.deleteAll();
        SessionService.closeSession();
    }

    @Test
    public void addTeacherTest() {
        Optional<Teacher> teacher1 = teacherDao.create("John", "Smith");
        checkTeacher(teacher1);
        Optional<Subject> subject1 = subjectDao.create("Maths");
        checkSubject(subject1);
        assertTrue(subjectDao.addTeacher(subject1.get(), teacher1.get()));
        assertEquals(1, subject1.get().getTeachers().size());
        assertEquals(1, teacher1.get().getSubjects().size());
        assertEquals(subject1.get().getTeachers().get(0), teacher1.get());
        Optional<Teacher> teacher2 = teacherDao.findById(teacher1.get().getId());
        checkTeacher(teacher2);
        assertEquals(subject1.get().getTeachers().get(0), teacher2.get());
        assertFalse(subjectDao.addTeacher(subject1.get(), teacher2.get()));
        Teacher teacher3 = new Teacher("John", "Doe");
        subjectDao.addTeacher(subject1.get(), teacher3);
        assertEquals(subject1.get(), teacher3.getSubjects().get(0));
        assertEquals(1, teacherDao.findByNameSurname("John", "Doe").size());
    }

    @Test
    public void removeTeacherTest() {
        Optional<Teacher> teacher1 = teacherDao.create("John", "Smith");
        checkTeacher(teacher1);
        Optional<Subject> subject1 = subjectDao.create("Maths");
        checkSubject(subject1);
        assertTrue(subjectDao.addTeacher(subject1.get(), teacher1.get()));
        assertTrue(teacherDao.deleteOne(teacher1.get()));
        assertTrue(subject1.get().getTeachers().isEmpty());
    }

    private void checkTeacher(Optional<Teacher> teacher) {
        assertTrue(teacher.isPresent());
        teacher.ifPresent(t -> {
            assertTrue(t.getId() > 0);
            assertNotNull(t.getName());
            assertNotNull(t.getSurname());
        });
    }

    private void checkSubject(Optional<Subject> subject) {
        assertTrue(subject.isPresent());
        subject.ifPresent(s -> {
            assertTrue(s.getId() > 0);
            assertNotNull(s.getName());
        });
    }
}
