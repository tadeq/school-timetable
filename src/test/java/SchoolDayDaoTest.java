import com.schooltimetable.dao.*;
import com.schooltimetable.model.SchoolDay;
import com.schooltimetable.model.Weekday;
import com.schooltimetable.service.SessionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class SchoolDayDaoTest {
    private final SchoolDayDao schoolDayDao = new SchoolDayDao();

    @Before
    public void before() {
        SessionService.openSession();
    }

    @After
    public void after() {
        schoolDayDao.deleteAll();
        SessionService.closeSession();
    }

    @Test
    public void createSchoolDayTest() {
        Optional<SchoolDay> schoolDay1 = schoolDayDao.create(Weekday.MONDAY);
        checkSchoolday(schoolDay1);
        Optional<SchoolDay> schoolDay2 = schoolDayDao.create(Weekday.FRIDAY);
        checkSchoolday(schoolDay2);
        assertNotEquals(schoolDay1.get(), schoolDay2.get());
        assertNotEquals(schoolDay1.get().getId(), schoolDay2.get().getId());
        Optional<SchoolDay> schoolDay3 = schoolDayDao.create(Weekday.MONDAY);
        assertFalse(schoolDay3.isPresent());
    }

    @Test
    public void findSchoolDayTest() {
        Optional<SchoolDay> schoolDay1 = schoolDayDao.create(Weekday.MONDAY);
        checkSchoolday(schoolDay1);
        Optional<SchoolDay> schoolDay2 = schoolDayDao.findByWeekday(Weekday.TUESDAY);
        assertFalse(schoolDay2.isPresent());
        schoolDay2 = schoolDayDao.findByWeekday(Weekday.MONDAY);
        checkSchoolday(schoolDay2);
        assertEquals(schoolDay1.get(), schoolDay2.get());
        schoolDay2 = schoolDayDao.findById(schoolDay1.get().getId());
        checkSchoolday(schoolDay2);
        assertEquals(schoolDay1.get(), schoolDay2.get());
        schoolDayDao.create(Weekday.TUESDAY);
        assertEquals(2, schoolDayDao.findAll().size());
    }

    @Test
    public void deleteSchoolDayTest() {
        Optional<SchoolDay> schoolDay1 = schoolDayDao.create(Weekday.FRIDAY);
        checkSchoolday(schoolDay1);
        Optional<SchoolDay> schoolDay2 = schoolDayDao.create(Weekday.THURSDAY);
        checkSchoolday(schoolDay2);
        assertEquals(2, schoolDayDao.findAll().size());
        schoolDayDao.deleteOne(schoolDay1.get());
        assertEquals(1, schoolDayDao.findAll().size());
        schoolDayDao.deleteAll();
        assertEquals(0, schoolDayDao.findAll().size());
    }

    private void checkSchoolday(Optional<SchoolDay> schoolDay) {
        assertTrue(schoolDay.isPresent());
        schoolDay.ifPresent(day -> {
            assertTrue(day.getId() > 0);
            assertNotNull(day.getWeekday());
        });
    }
}
