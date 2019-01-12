package com.schooltimetable.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import java.util.List;
import java.util.Optional;

public abstract class Dao<T, Id> {
    public void save(T t) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
    }

    public void update(T t) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(t);
        transaction.commit();
    }

    public void delete(T t) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(t);
        transaction.commit();
    }

    public abstract Optional<T> findById(Id id);

    public abstract List<T> findAll();
}
