package com.schooltimetable.dao;

import com.schooltimetable.model.Teacher;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class TeacherDao extends Dao<Teacher, Integer> {
    public Optional<Teacher> create(String name, String surname) {
        save(new Teacher(name, surname));
        List<Teacher> teachers = findByNameSurname(name, surname);
        return teachers.isEmpty() ? Optional.empty() : Optional.of(teachers.get(teachers.size() - 1));
    }

    @Override
    public Optional<Teacher> findById(Integer id) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            Teacher teacher = SessionService.getSession()
                    .createQuery("SELECT t FROM Teacher t WHERE t.id = :id", Teacher.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(teacher);
        } catch (PersistenceException e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Teacher> findAll() {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Teacher> teachers = SessionService.getSession()
                .createQuery("SELECT t FROM Teacher t", Teacher.class).getResultList();
        transaction.commit();
        return teachers;
    }

    public List<Teacher> findByNameSurname(String name, String surname) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        List<Teacher> teachers = SessionService.getSession()
                .createQuery("SELECT t FROM Teacher t WHERE t.name = :name AND t.surname = :surname", Teacher.class)
                .setParameter("name", name)
                .setParameter("surname", surname)
                .getResultList();
        transaction.commit();
        return teachers;
    }
}
