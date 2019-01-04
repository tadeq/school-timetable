package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Schooldays")
public class Schoolday {
    @Id
    private String weekday;

    @OneToMany(mappedBy = "schoolday")
    private List<Lesson> lessons;

    public Schoolday() {
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}