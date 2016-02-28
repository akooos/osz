package com.elte.tosz.logic.entities;

//
// This file was generated by the JPA Modeler
//

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class Room extends IEntity implements Serializable {

    @Column(nullable = false)
    @Basic
    private int floor;

    @Column(nullable = false)
    @Basic
    private int building;

    @Column(nullable = false)
    @Basic
    private int capacity;

    public Room() {

    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getBuilding() {
        return this.building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
