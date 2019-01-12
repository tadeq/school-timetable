package com.schooltimetable.dao;

import com.schooltimetable.model.Schoolday;
import com.schooltimetable.model.Weekday;
import com.schooltimetable.service.SessionService;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class SchooldayDao extends Dao<Schoolday, String> {
    public Optional<Schoolday> create(Weekday weekday) {
        save(new Schoolday(weekday));
        return Optional.of(findByWeekday(weekday));
    }

    @Override
    public Optional<Schoolday> findById(String id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Schoolday schoolday = SessionService.getSession()
                    .createQuery("SELECT sd FROM Schoolday sd WHERE sd.id = :id", Schoolday.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(schoolday);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Schoolday> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Schoolday> schooldays = SessionService.getSession()
                .createQuery("SELECT sd FROM Schoolday sd", Schoolday.class)
                .getResultList();
        transaction.commit();
        return schooldays;
    }

    public Schoolday findByWeekday(Weekday weekday) {
        return SessionService.getSession().createQuery("SELECT sd FROM Schoolday sd WHERE sd.weekday = :weekday", Schoolday.class)
                .setParameter("weekday", weekday)
                .getSingleResult();
    }
}
