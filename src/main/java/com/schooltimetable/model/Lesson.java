package com.schooltimetable.model;

import javax.persistence.*;

@Entity
@Table(name = "Lessons", uniqueConstraints = @UniqueConstraint(columnNames = {"schooldayId", "number", "schoolClassId"}))
public class Lesson implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "schooldayId", nullable = false)
    private SchoolDay schoolDay;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "schoolClassId", nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "classroomId")
    private Classroom classroom;

    public Lesson() {
    }

    public Lesson(SchoolDay schoolDay, int number, SchoolClass schoolClass) {
        this.schoolDay = schoolDay;
        schoolDay.getLessons().add(this);
        this.number = number;
        this.schoolClass = schoolClass;
        schoolClass.getLessons().add(this);
    }

    public Lesson(SchoolDay schoolDay, int number, Subject subject, Teacher teacher, SchoolClass schoolClass, Classroom classroom) {
        this.schoolDay = schoolDay;
        this.number = number;
        this.subject = subject;
        this.teacher = teacher;
        this.schoolClass = schoolClass;
        this.classroom = classroom;
    }

    public Integer getId() {
        return this.id;
    }

    public SchoolDay getSchoolDay() {
        return schoolDay;
    }

    public Integer getNumber() {
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

    public void setSchoolDay(SchoolDay schoolDay) {
        if (this.schoolDay != null)
            this.schoolDay.getLessons().remove(this);
        this.schoolDay = schoolDay;
        this.schoolDay.getLessons().add(this);
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setSubject(Subject subject) {
        if (this.subject != null)
            this.subject.getLessons().remove(this);
        this.subject = subject;
        this.subject.getLessons().add(this);
    }

    public void setTeacher(Teacher teacher) {
        if (teacher.getLessons().stream().anyMatch(lesson -> lesson.compareTo(this) == 0)) {
            System.out.println("This teacher has another lesson at the same time");
        } else {
            if (this.teacher != null)
                this.teacher.getLessons().remove(this);
            this.teacher = teacher;
            this.teacher.getLessons().add(this);
        }
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        if (schoolClass.getLessons().stream().anyMatch(lesson -> lesson.compareTo(this) == 0)) {
            System.out.println("This class has another lesson at the same time");
        } else {
            if (this.schoolClass != null)
                this.schoolClass.getLessons().remove(this);
            this.schoolClass = schoolClass;
            this.schoolClass.getLessons().add(this);
        }
    }

    public void setClassroom(Classroom classroom) {
        if (classroom.getLessons().stream().anyMatch(lesson -> lesson.compareTo(this) == 0)) {
            System.out.println("Classroom reserved at this time by other lesson");
        } else {
            if (this.classroom != null)
                this.classroom.getLessons().remove(this);
            this.classroom = classroom;
            this.classroom.getLessons().add(this);
        }
    }

    @Override
    public int compareTo(Object o) {
        Lesson other = (Lesson) o;
        int res;
        res = this.getSchoolDay().getWeekday().compareTo(other.getSchoolDay().getWeekday());
        if (res != 0)
            return res;
        res = this.getNumber().compareTo(other.getNumber());
        return res;
    }

    @PreRemove
    public void onRemove() {
        schoolDay.getLessons().remove(this);
        schoolClass.getLessons().remove(this);
        if (subject != null)
            subject.getLessons().remove(this);
        if (teacher != null)
            teacher.getLessons().remove(this);
        if (classroom != null)
            classroom.getLessons().remove(this);
    }

    public String toString() {
        return this.schoolDay.getWeekday() + "  Lesson " + this.number + ": " + this.subject.getName() + " "
                + this.teacher.getName() + " " + this.teacher.getSurname() + this.classroom.getNumber();
    }
}
