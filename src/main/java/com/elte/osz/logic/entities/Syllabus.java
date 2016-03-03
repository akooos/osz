package com.elte.osz.logic.entities;

//
// This file was generated by the JPA Modeler
//

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Syllabus")
@Table(name = "Syllabus")

public class Syllabus implements Serializable {

    @Column(unique = true, nullable = false)
    @Basic
    private String name;

    @Column(updatable = false, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = SyllabusItem.class)
    private List<SyllabusItem> items;

    public Syllabus() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SyllabusItem> getItems() {
        return this.items;
    }

    public void setItems(List<SyllabusItem> items) {
        this.items = items;
    }
}