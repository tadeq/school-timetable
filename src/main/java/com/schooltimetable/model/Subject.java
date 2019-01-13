package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
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

    public Integer getId() {
        return id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getSubjects().add(this);
    }
}
