package com.schooltimetable.dao;

import com.schooltimetable.model.Teacher;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import java.util.List;

public class TeacherDao extends Dao<Teacher, Integer> {
    @Override
    public Teacher findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        Teacher teacher = SessionService.getSession()
                .createQuery("SELECT t FROM Teacher t WHERE t.id = :id", Teacher.class).setParameter("id", id).getSingleResult();
        transaction.commit();
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Teacher> teachers = SessionService.getSession()
                .createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
        transaction.commit();
        return teachers;
    }
}
