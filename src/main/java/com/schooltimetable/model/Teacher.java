package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    private List<Lesson> lessons;

    @ManyToMany
    private Set<Subject> subjects;

    public Teacher() {
    }

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public int getId() {
        return id;
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
