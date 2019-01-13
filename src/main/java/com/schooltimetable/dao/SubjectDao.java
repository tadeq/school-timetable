package com.schooltimetable.dao;

import com.schooltimetable.model.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class SubjectDao extends Dao<Subject> {
    public Optional<Subject> create(String name) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            save(new Subject(name));
            transaction.commit();
            return findByName(name);
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Subject> findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Subject subject = SessionService.getSession()
                    .createQuery("SELECT s FROM Subject s WHERE s.id = :id", Subject.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(subject);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Subject> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Subject> subjects = SessionService.getSession()
                .createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
        transaction.commit();
        return subjects;
    }

    public Optional<Subject> findByName(String name) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Subject subject = SessionService.getSession()
                    .createQuery("SELECT s FROM Subject s WHERE s.name = :name", Subject.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(subject);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }
}
