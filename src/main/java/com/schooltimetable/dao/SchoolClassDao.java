package com.schooltimetable.dao;

import com.schooltimetable.model.SchoolClass;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import java.util.List;

public class SchoolClassDao extends Dao<SchoolClass, String> {
    @Override
    public SchoolClass findById(String id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        SchoolClass schoolClass = SessionService.getSession()
                .createQuery("SELECT t FROM SchoolClass t WHERE t.id = :id", SchoolClass.class).setParameter("id", id).getSingleResult();
        transaction.commit();
        return schoolClass;
    }

    @Override
    public List<SchoolClass> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<SchoolClass> schoolClasses = SessionService.getSession()
                .createQuery("SELECT t FROM SchoolClass t", SchoolClass.class).getResultList();
        transaction.commit();
        return schoolClasses;
    }
}
