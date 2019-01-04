package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Subjects")
public class Subject {

    @Id
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Lesson> lessons;

    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }
}
