package com.schooltimetable.dao;

import org.hibernate.Session;
import com.schooltimetable.service.SessionService;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public abstract class Dao<T> {
    protected void save(T t) throws PersistenceException {
        Session session = SessionService.getSession();
        session.save(t);
        session.merge(t);
    }

    protected void update(T t) throws PersistenceException {
        Session session = SessionService.getSession();
        session.update(t);
        session.merge(t);
    }

    protected void delete(T t) throws PersistenceException {
        Session session = SessionService.getSession();
        session.delete(t);
    }

    public void deleteOne(T t) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            delete(t);
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void deleteAll() {
        Session session = SessionService.getSession();
        List<T> elements = findAll();
        Transaction transaction = session.beginTransaction();
        try {
            for (T t : elements) {
                delete(t);
            }
            transaction.commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public abstract Optional<T> findById(Integer id);

    public abstract List<T> findAll();
}
