package com.schooltimetable.dao;

import com.schooltimetable.model.Classroom;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import java.util.List;

public class ClassroomDao extends Dao<Classroom, Integer> {
    @Override
    public Classroom findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        Classroom classroom = SessionService.getSession()
                .createQuery("SELECT t FROM Classroom t WHERE t.id = :id", Classroom.class).setParameter("id", id).getSingleResult();
        transaction.commit();
        return classroom;
    }

    @Override
    public List<Classroom> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Classroom> classrooms = SessionService.getSession()
                .createQuery("SELECT t FROM Classroom t", Classroom.class).getResultList();
        transaction.commit();
        return classrooms;
    }
}
