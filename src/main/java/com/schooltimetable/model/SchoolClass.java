package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "schoolClass")
    private List<Lesson> lessons;

    public SchoolClass() {
    }

    public SchoolClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}
