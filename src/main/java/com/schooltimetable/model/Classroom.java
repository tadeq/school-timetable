package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Classrooms")
public class Classroom {

    @Id
    private String number;

    @OneToMany
    private List<Lesson> lessons;

    public Classroom() {
    }

    public Classroom(String number) {
        this.number = number;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}
