package com.example.dbms.model;

import java.sql.Date;
import java.util.HashMap;

public class Attendance {

    private String empid;
    private Date date;
    private String value;
    private HashMap<String,String> ma;

    public Attendance(){

    }

    public Attendance(String empid,Date date, String value){
        this.empid=empid;
        this.date=date;
        this.value=value;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the empid
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the ma
     */
    public HashMap<String, String> getMa() {
        return ma;
    }

    /**
     * @param ma the ma to set
     */
    public void setMa(HashMap<String, String> ma) {
        this.ma = ma;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @param empid the empid to set
     */
    public void setEmpid(String empid) {
        this.empid = empid;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}