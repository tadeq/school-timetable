package com.schooltimetable.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson> lessons;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<Subject> subjects;

    public Teacher() {
    }

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.lessons = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getTeachers().add(this);
    }
}
