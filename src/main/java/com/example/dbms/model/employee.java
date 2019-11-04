package com.example.dbms.model;

import java.util.HashMap;

public class employee {

    private String empid;
    private String name;
    private Integer phone;
    private String email;
    private String position;
    private String address;
    private String password;
    private String mPassword;
    private HashMap ma;

    public employee(){

    }

    public employee(String empid,String name,Integer phone,String email,String position,String address,HashMap ma){
        this.empid=empid;
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.position=position;
        this.address=address;
        this.ma=ma;
    }

    /**
     * @return the empid
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the mPassword
     */
    public String getmPassword() {
        return mPassword;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param empid the empid to set
     */
    public void setEmpid(String empid) {
        this.empid = empid;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param mPassword the mPassword to set
     */
    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }
}