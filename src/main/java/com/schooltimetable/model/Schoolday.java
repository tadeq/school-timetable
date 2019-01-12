package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Schooldays")
public class Schoolday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    @OneToMany(mappedBy = "schoolday")
    private List<Lesson> lessons;

    public Schoolday() {
    }

    public Schoolday(Weekday weekday) {
        this.weekday = weekday;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}