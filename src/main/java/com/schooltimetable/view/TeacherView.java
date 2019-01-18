package com.schooltimetable.view;

import com.schooltimetable.dao.TeacherDao;
import com.schooltimetable.model.Teacher;

import java.util.Optional;
import java.util.Scanner;

public class TeacherView {
    private Scanner scanner;

    private TeacherDao teacherDao;

    private LessonView lessonView;

    public TeacherView() {
        this.scanner = new Scanner(System.in);
        this.teacherDao = new TeacherDao();
        this.lessonView = new LessonView();
        this.lessonView.setTeacherView(this);
    }

    public void showTeachers() {
        teacherDao.findAll().forEach(teacher -> System.out.println(teacher.getName() + " " + teacher.getSurname() + ", ID:" + teacher.getId() + "\n"));
    }

    public void showTeacherDetails() {
        Optional<Teacher> teacherFound = null;
        System.out.print("Teacher ID: ");
        try {
            teacherFound = teacherDao.findById(Integer.parseInt(scanner.nextLine()));
            teacherFound.ifPresent(teacher -> {
                System.out.println("Teacher");
                System.out.println("ID: " + teacher.getId());
                System.out.println("Name: " + teacher.getName());
                System.out.println("Surname" + teacher.getSurname());
                System.out.println("Subjects learnt: " + teacher.getSubjects().stream().map(subject -> subject.getName() + ", "));
                lessonView.showLessons(teacher);
            });
        } catch (NumberFormatException e) {
            System.out.println("Wrong value");
        }
    }
}
