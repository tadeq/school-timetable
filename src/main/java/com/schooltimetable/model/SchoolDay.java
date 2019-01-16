package com.schooltimetable.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Schooldays")
public class SchoolDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    @OneToMany(mappedBy = "schoolDay")
    private List<Lesson> lessons;

    public SchoolDay() {
    }

    public SchoolDay(Weekday weekday) {
        this.weekday = weekday;
        this.lessons = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lesson.setSchoolDay(this);
    }
}