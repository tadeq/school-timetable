package com.schooltimetable.model;

import javax.persistence.*;

@Entity
@Table(name = "Lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "schooldayId")
    @Column(nullable = false)
    private Schoolday schoolday;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "schoolClassId")
    @Column(nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne
    @JoinColumn(name = "classroomId")
    private Classroom classroom;

    public Lesson() {
    }

    public Lesson(Schoolday schoolday, int number, SchoolClass schoolClass) {
        this.schoolday = schoolday;
        this.number = number;
        this.schoolClass = schoolClass;
    }

    public Lesson(Schoolday schoolday, int number, Subject subject, Teacher teacher, SchoolClass schoolClass, Classroom classroom) {
        this.schoolday = schoolday;
        this.number = number;
        this.subject = subject;
        this.teacher = teacher;
        this.schoolClass = schoolClass;
        this.classroom = classroom;
    }

    public int getId() {
        return this.id;
    }

    public Schoolday getSchoolday() {
        return schoolday;
    }

    public int getNumber() {
        return number;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.getLessons().add(this);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        teacher.getLessons().add(this);
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        schoolClass.getLessons().add(this);
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
        classroom.getLessons().add(this);
    }
}
