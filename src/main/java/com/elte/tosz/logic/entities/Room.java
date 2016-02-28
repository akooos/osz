package com.elte.tosz.logic.entities;

//
// This file was generated by the JPA Modeler
//

import com.elte.tosz.logic.dbhandler.IEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Room")
@Table(name = "Room")

public class Room extends IEntity<Room> implements Serializable {

    @Column(updatable = false, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[Id="+ id + ",Building="+building +",Capacity="+ capacity +",Floor="+ floor +"]" );
        
        return sb.toString();
    }
}
