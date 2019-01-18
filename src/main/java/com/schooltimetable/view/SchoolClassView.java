package com.schooltimetable.view;

import com.schooltimetable.dao.SchoolClassDao;
import com.schooltimetable.model.SchoolClass;

import java.util.Optional;
import java.util.Scanner;

public class SchoolClassView {
    private Scanner scanner;

    private SchoolClassDao schoolClassDao;

    private LessonView lessonView;

    public SchoolClassView() {
        this.scanner = new Scanner(System.in);
        this.schoolClassDao = new SchoolClassDao();
        this.lessonView = new LessonView();
        this.lessonView.setSchoolClassView(this);
    }

    public void showClasses() {
        schoolClassDao.findAll().forEach(schoolClass -> System.out.print(schoolClass.getName() + ", "));
        System.out.println();
    }

    public void addClass() {
        System.out.println("Add class: ");
        String name = scanner.nextLine();
        if (!name.equals(""))
            schoolClassDao.create(name);
    }

    public void showClassTimetable() {
        Optional<SchoolClass> classFound;
        System.out.print("Class: ");
        classFound = schoolClassDao.findByName(scanner.nextLine());
        classFound.ifPresent(schoolClass -> {
            System.out.println("Class " + schoolClass.getName());
            lessonView.showLessons(schoolClass);
            System.out.println("a - add lesson");
            System.out.println("other - back");
            String line = scanner.nextLine();
            if (line.length() != 0) {
                char c = line.charAt(0);
                if (c == 'a') {
                    lessonView.addLesson(schoolClass);
                }
            }
        });
    }
}
