package com.schooltimetable.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Classes")
public class SchoolClass {

    @Id
    private String name;

    @OneToMany(mappedBy = "schoolClass")
    private List<Lesson> lessons;

    public SchoolClass() {
    }

    public SchoolClass(String name){
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}
