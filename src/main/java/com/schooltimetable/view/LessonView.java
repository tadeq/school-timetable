package com.schooltimetable.view;

import com.schooltimetable.dao.LessonDao;
import com.schooltimetable.model.Classroom;
import com.schooltimetable.model.Lesson;
import com.schooltimetable.model.SchoolClass;
import com.schooltimetable.model.Teacher;

import java.util.Collections;
import java.util.List;

public class LessonView {
    private TeacherView teacherView;

    private SchoolClassView schoolClassView;

    private LessonDao lessonDao;

    public LessonView() {
        this.lessonDao = new LessonDao();
    }

    public void setTeacherView(TeacherView teacherView) {
        this.teacherView = teacherView;
    }

    public void setSchoolClassView(SchoolClassView schoolClassView) {
        this.schoolClassView = schoolClassView;
    }

    public void addLesson(SchoolClass schoolClass) {
        System.out.println("\nAdd lesson");
        System.out.print("Teachers");
        teacherView.showTeachers();
        System.out.println("Classes");
        schoolClassView.showClasses();
        //TODO
    }

    public void showLessons(List<Lesson> lessons) {
        System.out.println("Timetable");
        Collections.sort(lessons);
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }

    public void showLessons(SchoolClass schoolClass) {
        List<Lesson> lessons = schoolClass.getLessons();
        showLessons(lessons);
    }

    public void showLessons(Teacher teacher) {
        List<Lesson> lessons = teacher.getLessons();
        showLessons(lessons);
    }

    public void showLessons(Classroom classroom) {
        List<Lesson> lessons = classroom.getLessons();
        showLessons(lessons);
    }
}
