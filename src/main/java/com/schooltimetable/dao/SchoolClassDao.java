package com.schooltimetable.dao;

import com.schooltimetable.model.SchoolClass;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class SchoolClassDao extends Dao<SchoolClass, String> {
    public Optional<SchoolClass> create(String name) {
        save(new SchoolClass(name));
        return findByName(name);
    }

    @Override
    public Optional<SchoolClass> findById(String id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            SchoolClass schoolClass = SessionService.getSession()
                    .createQuery("SELECT sc FROM SchoolClass sc WHERE sc.id = :id", SchoolClass.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(schoolClass);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<SchoolClass> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<SchoolClass> schoolClasses = SessionService.getSession()
                .createQuery("SELECT sc FROM SchoolClass sc", SchoolClass.class)
                .getResultList();
        transaction.commit();
        return schoolClasses;
    }

    public Optional<SchoolClass> findByName(String name) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            SchoolClass schoolClass = SessionService.getSession()
                    .createQuery("SELECT sc FROM SchoolClass sc WHERE sc.name = :name", SchoolClass.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return Optional.of(schoolClass);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }
}
