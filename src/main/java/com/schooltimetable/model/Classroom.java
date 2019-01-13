package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String number;

    @OneToMany
    private List<Lesson> lessons;

    public Classroom() {
    }

    public Classroom(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}
