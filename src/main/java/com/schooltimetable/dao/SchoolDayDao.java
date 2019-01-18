package com.schooltimetable.dao;

import com.schooltimetable.model.SchoolDay;
import com.schooltimetable.model.Weekday;
import com.schooltimetable.service.SessionService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class SchoolDayDao extends Dao<SchoolDay> {
    public Optional<SchoolDay> create(Weekday weekday) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            save(new SchoolDay(weekday));
            transaction.commit();
            return findByWeekday(weekday);
        } catch (PersistenceException e) {
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public Optional<SchoolDay> findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            SchoolDay schoolDay = SessionService.getSession()
                    .createQuery("SELECT sd FROM SchoolDay sd WHERE sd.id = :id", SchoolDay.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(schoolDay);
        } catch (PersistenceException e) {
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<SchoolDay> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<SchoolDay> schoolDays = SessionService.getSession()
                .createQuery("SELECT sd FROM SchoolDay sd", SchoolDay.class)
                .getResultList();
        transaction.commit();
        return schoolDays;
    }

    public Optional<SchoolDay> findByWeekday(Weekday weekday) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            SchoolDay schoolDay = SessionService.getSession().createQuery("SELECT sd FROM SchoolDay sd WHERE sd.weekday = :weekday", SchoolDay.class)
                    .setParameter("weekday", weekday)
                    .getSingleResult();
            return Optional.of(schoolDay);
        } catch (PersistenceException e) {
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }
}
