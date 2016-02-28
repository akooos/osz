package com.elte.tosz.logic.entities;

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

@Entity
@Table

public class ProposedTime implements Serializable {

    @Id
    @OneToOne(optional = true, targetEntity = Syllabus.class)
    private Syllabus syllabus;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private Timestamp dtCreated;

    @Column(unique = false, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = ProposedTimeItem.class)
    private List<ProposedTimeItem> proposedtime_item;

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

    public List<ProposedTimeItem> getProposedtime_item() {
        return this.proposedtime_item;
    }

    public void setProposedtime_item(List<ProposedTimeItem> proposedtime_item) {
        this.proposedtime_item = proposedtime_item;
    }
}
