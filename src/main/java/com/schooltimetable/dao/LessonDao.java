package com.schooltimetable.dao;

import com.schooltimetable.model.Lesson;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import java.util.List;

public class LessonDao extends Dao<Lesson, Integer> {
    @Override
    public Lesson findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        Lesson lesson = SessionService.getSession()
                .createQuery("SELECT t FROM Lesson t WHERE t.id = :id", Lesson.class).setParameter("id", id).getSingleResult();
        transaction.commit();
        return lesson;
    }

    @Override
    public List<Lesson> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Lesson> lessons = SessionService.getSession()
                .createQuery("SELECT t FROM Lesson t", Lesson.class).getResultList();
        transaction.commit();
        return lessons;
    }
}
