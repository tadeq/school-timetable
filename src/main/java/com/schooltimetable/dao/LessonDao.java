package com.schooltimetable.dao;

import com.schooltimetable.model.Lesson;
import com.schooltimetable.model.SchoolClass;
import com.schooltimetable.model.SchoolDay;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class LessonDao extends Dao<Lesson> {
    public Optional<Lesson> create(SchoolDay schoolday, int number, SchoolClass schoolClass) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            save(new Lesson(schoolday, number, schoolClass));
            transaction.commit();
            return findByDayNumberClass(schoolday, number, schoolClass);
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Lesson lesson = SessionService.getSession()
                    .createQuery("SELECT l FROM Lesson l WHERE l.id = :id", Lesson.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(lesson);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Lesson> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Lesson> lessons = SessionService.getSession()
                .createQuery("SELECT l FROM Lesson l", Lesson.class)
                .getResultList();
        transaction.commit();
        return lessons;
    }

    public Optional<Lesson> findByDayNumberClass(SchoolDay schoolDay, int number, SchoolClass schoolClass) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Lesson lesson = SessionService.getSession()
                    .createQuery("SELECT l FROM Lesson l WHERE l.schoolDay = :schoolday AND l.number = :number AND l.schoolClass = :schoolClass", Lesson.class)
                    .setParameter("schoolDay", schoolDay)
                    .setParameter("number", number)
                    .setParameter("schoolClass", schoolClass)
                    .getSingleResult();
            return Optional.of(lesson);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }
}
