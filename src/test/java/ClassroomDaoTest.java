import com.schooltimetable.dao.ClassroomDao;
import com.schooltimetable.model.Classroom;
import com.schooltimetable.model.Weekday;
import com.schooltimetable.service.SessionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ClassroomDaoTest {
    private final ClassroomDao classroomDao = new ClassroomDao();

    @Before
    public void before() {
        SessionService.openSession();
    }

    @After
    public void after() {
        classroomDao.deleteAll();
        SessionService.closeSession();
    }

    @Test
    public void createClassroomTest() {
        Optional<Classroom> classroom1 = classroomDao.create("23");
        checkClassroom(classroom1);
        Optional<Classroom> classroom2 = classroomDao.create("A1");
        checkClassroom(classroom2);
        assertNotEquals(classroom1.get(), classroom2.get());
        assertNotEquals(classroom1.get().getId(), classroom2.get().getId());
        Optional<Classroom> classroom3 = classroomDao.create("A1");
        assertFalse(classroom3.isPresent());
    }

    @Test
    public void findClassroomTest() {
        Optional<Classroom> classroom1 = classroomDao.create("A");
        checkClassroom(classroom1);
        Optional<Classroom> classroom2 = classroomDao.findByNumber("11");
        assertFalse(classroom2.isPresent());
        classroom2 = classroomDao.findByNumber("A");
        checkClassroom(classroom2);
        assertEquals(classroom1.get(), classroom2.get());
        classroom2 = classroomDao.findById(classroom1.get().getId());
        checkClassroom(classroom2);
        assertEquals(classroom1.get(), classroom2.get());
        classroomDao.create("10");
        assertEquals(2, classroomDao.findAll().size());
    }

    @Test
    public void deleteClassroomTest() {
        Optional<Classroom> classroom1 = classroomDao.create("1.47");
        checkClassroom(classroom1);
        Optional<Classroom> classroom2 = classroomDao.create("3.30");
        checkClassroom(classroom2);
        assertEquals(2, classroomDao.findAll().size());
        assertTrue(classroomDao.deleteOne(classroom1.get()));
        assertEquals(1, classroomDao.findAll().size());
        assertTrue(classroomDao.deleteAll());
        assertEquals(0, classroomDao.findAll().size());
    }

    private void checkClassroom(Optional<Classroom> classroom) {
        assertTrue(classroom.isPresent());
        classroom.ifPresent(c -> {
            assertTrue(c.getId() > 0);
            assertNotNull(c.getNumber());
        });
    }
}
