package com.schooltimetable.dao;

import com.schooltimetable.model.Subject;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import java.util.List;

public class SubjectDao extends Dao<Subject, String> {
    @Override
    public Subject findById(String id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        Subject subject = SessionService.getSession()
                .createQuery("SELECT t FROM Subject t WHERE t.id = :id", Subject.class).setParameter("id", id).getSingleResult();
        transaction.commit();
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Subject> subjects = SessionService.getSession()
                .createQuery("SELECT t FROM Subject t", Subject.class).getResultList();
        transaction.commit();
        return subjects;
    }
}
