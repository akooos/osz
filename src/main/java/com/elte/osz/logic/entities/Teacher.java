package com.elte.osz.logic.entities;

//
// This file was generated by the JPA Modeler
//

import com.elte.osz.logic.dbhandler.AbstractEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Teacher extends AbstractEntity implements Serializable {

    
    @Column(nullable = false)
    @Basic
    private String name;

    public Teacher() {
        super();

    }
    
    public Teacher(String name){
        super();
        this.name=name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
