package com.schooltimetable;

import com.schooltimetable.dao.TeacherDao;
import com.schooltimetable.model.Teacher;
import com.schooltimetable.service.SessionService;

public class Application {
    public static void main(String[] args) {
        SessionService.openSession();
        TeacherDao teacherDao = new TeacherDao();
        Teacher teacher = teacherDao.findById(1);
        System.out.println(teacher.toString());
        SessionService.closeSession();
        SessionService.closeSessionFactory();
    }
}
