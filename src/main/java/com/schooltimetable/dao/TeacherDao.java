package com.schooltimetable.dao;

import com.schooltimetable.model.Subject;
import com.schooltimetable.model.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.schooltimetable.service.SessionService;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class TeacherDao extends Dao<Teacher> {
    public Optional<Teacher> create(String name, String surname) {
        Session session = SessionService.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            save(new Teacher(name, surname));
            transaction.commit();
            List<Teacher> teachers = findByNameSurname(name, surname);
            return teachers.isEmpty() ? Optional.empty() : Optional.of(teachers.get(teachers.size() - 1));
        } catch (PersistenceException e) {
            transaction.rollback();
            return Optional.empty();
        }
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

    public boolean addSubject(Teacher teacher, Subject subject) {
        Transaction transaction = SessionService.getSession().beginTransaction();
        try {
            if (teacher.getSubjects().contains(subject)) {
                transaction.commit();
                return false;
            }
            teacher.addSubject(subject);
            update(teacher);
            transaction.commit();
            return true;
        } catch (PersistenceException e) {
            transaction.rollback();
            return false;
        }
    }
}
