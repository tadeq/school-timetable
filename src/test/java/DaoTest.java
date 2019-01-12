import com.schooltimetable.dao.*;
import com.schooltimetable.service.SessionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DaoTest {
    private final SchooldayDao schooldayDao;

    private final SchoolClassDao schoolClassDao;

    private final ClassroomDao classroomDao;

    private final SubjectDao subjectDao;

    private final TeacherDao teacherDao;

    private final LessonDao lessonDao;

    @Before
    public void before() {
        SessionService.openSession();
    }

    @After
    public void after() {
        SessionService.closeSession();
    }

    @Test
    public void createSchooldayTest(){

    }

    @Test
    public void createLessonTest(){

    }

    @Test
    public void creteTeacherTest(){

    }


}
