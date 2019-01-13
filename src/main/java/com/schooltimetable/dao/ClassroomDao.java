package com.schooltimetable.dao;

import com.schooltimetable.model.Classroom;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class ClassroomDao extends Dao<Classroom> {
    public Optional<Classroom> create(String number) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            save(new Classroom(number));
            transaction.commit();
            return findByNumber(number);
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Classroom> findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Classroom classroom = SessionService.getSession()
                    .createQuery("SELECT c FROM Classroom c WHERE c.id = :id", Classroom.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(classroom);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Classroom> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Classroom> classrooms = SessionService.getSession()
                .createQuery("SELECT c FROM Classroom c", Classroom.class)
                .getResultList();
        transaction.commit();
        return classrooms;
    }

    public Optional<Classroom> findByNumber(String number) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Classroom classroom = SessionService.getSession().createQuery("SELECT c FROM Classroom c WHERE c.number = :number", Classroom.class)
                    .setParameter("number", number)
                    .getSingleResult();
            return Optional.of(classroom);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }
}
