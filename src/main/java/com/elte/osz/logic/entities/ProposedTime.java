package com.elte.osz.logic.entities;

//
// This file was generated by the JPA Modeler
//

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "ProposedTime")
@Table(name = "ProposedTime")

public class ProposedTime implements Serializable {

    
    @OneToOne(optional = false, targetEntity = Syllabus.class)
    private Syllabus syllabus;

    @Column(nullable = false)
    @Basic
    private Timestamp dtCreated;

    @Column(updatable = false, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = ProposedTimeItem.class)
    private List<ProposedTimeItem> items;

    public ProposedTime() {

    }

    public Syllabus getSyllabus() {
        return this.syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    public Timestamp getDtCreated() {
        return this.dtCreated;
    }

    public void setDtCreated(Timestamp dtCreated) {
        this.dtCreated = dtCreated;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProposedTimeItem> getItems() {
        return this.items;
    }

    public void setItems(List<ProposedTimeItem> items) {
        this.items = items;
    }
}
