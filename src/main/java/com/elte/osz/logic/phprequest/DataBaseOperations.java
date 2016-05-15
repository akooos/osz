/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elte.osz.logic.phprequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dobos Árpád
 */
public class DataBaseOperations {
    private Connection connection;
    private Properties properties;
    private PhpRequest phprequest;
    
    private final String SQL_URL = "jdbc:derby://localhost:1527/osz";
    
    private final String GET_ALL_SUBJECTS_CODE = "SELECT CODE FROM SUBJECT";
    private final String GET_SUBJECT_ID = "SELECT ID FROM SUBJECT WHERE CODE = ";
    private final String GET_TEACHER_ID = "SELECT ID FROM TEACHER WHERE NAME = ";
    private final String GET_ROOM_ID = "SELECT ID FROM ROOM WHERE NAME LIKE ";
    
    private final String ADD_ELEMENT_TO_SEMESTERITEM = "INSERT INTO OSZ.SEMESTERITEM (DAY, ENDTIME, STARTTIME, ROOM_ID, SUBJECT_ID,"
            + "TEACHER_ID) VALUES (?,?,?,?,?,?)";

    public String subjectID;
    public String teacherID;
    public String roomID;
    public String startTime;
    public String endTime;
    public String day;
    
    public DataBaseOperations() {
            properties = new Properties();
            properties.put("user", "osz");
            properties.put("password", "osz");
            phprequest = new PhpRequest();
    }
    
    public void searchSubjectSchedule(){
        try {
            connection = DriverManager.getConnection(SQL_URL, properties);
            ArrayList<String> subjectsID = new ArrayList<String>();
            Statement stat = connection.createStatement();
            ResultSet res = stat.executeQuery(GET_ALL_SUBJECTS_CODE);
            while(res.next()) subjectsID.add(res.getString("CODE"));
           // ResultSet getSubjectIDRes = stat.executeQuery(GET_SUBJECT_ID+"'"+subjectsID.get(5)+"'");
           //if(getSubjectIDRes.next()) System.out.println(getSubjectIDRes.getString("ID"));
            for(int i = 0; i < subjectsID.size(); ++i){
                if(subjectsID.get(i).equals("<NULL>") || subjectsID.get(i).length() < 4  ||
                        subjectsID.get(i).equals("OE") || subjectsID.get(i).equals("OE_KARITAN") /*|| subjectsID.get(i).equals("?ff2n9b47?") */
                        || subjectsID.get(i).equals("IKP-9239") || subjectsID.get(i).equals("IPM-13FESZLAB3")) continue;
                uploadSubjectTime(subjectsID.get(i));
                
            }
            res.close();
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void uploadSubjectTime(String subjectCode){
        try {
            connection = DriverManager.getConnection(SQL_URL, properties);
            phprequest.downloadSubjectData(subjectCode);
            ArrayList<String> subjectData = new ArrayList<String>();
            subjectData = phprequest.getSubjectInfo();
            if(subjectData.size() > 0){
            ResultSet resSubjectID = connection.createStatement().executeQuery(GET_SUBJECT_ID+"'"+subjectCode+"'");
            subjectID = resSubjectID.next() ? resSubjectID.getString("ID") : null;
            ResultSet resTeacherID = connection.createStatement().executeQuery(GET_TEACHER_ID + "'"+ subjectData.get(4) + "'");
            teacherID = resTeacherID.next() ? resTeacherID.getString("ID") : null;
            String[] room = subjectData.get(2).split(" ");
            if(room.length < 2){
                roomID = null;
            } else{
                ResultSet resRoomID = connection.createStatement().executeQuery(GET_ROOM_ID + "'%" + room[2] + "%'");
                roomID = resRoomID.next() ? resRoomID.getString("ID") : null;
                resRoomID.close();
            }
            String[] time = subjectData.get(1).split(" |-");
            if(time.length < 2){
                startTime = "";
                endTime = "";
                day = "nincs megadva";
            } else{
                startTime = time[1];
                day = time[0];
                endTime = time[2];
            }
            PreparedStatement prep = connection.prepareStatement(ADD_ELEMENT_TO_SEMESTERITEM);
            prep.setString(1, day);
            prep.setString(2, endTime);
            prep.setString(3, startTime);
            prep.setString(4, roomID);
            prep.setString(5, subjectID);
            prep.setString(6, teacherID);
            prep.executeUpdate();
            prep.close();
            resSubjectID.close();
            resTeacherID.close();
            }
            connection.close(); 
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteTestTable(){
        try {
            connection = DriverManager.getConnection(SQL_URL, properties);
            Statement stat = connection.createStatement();
            stat.executeUpdate("DELETE FROM SEMESTERITEM");
            stat.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
