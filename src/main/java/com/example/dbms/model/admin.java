package com.example.dbms.model;

public class admin{
    private String adminid;
    private String password;
    private String name;
    private Integer phone;
    private String email;

    public admin(){

    }

    public admin(String adminid,String password,String name,Integer phone,String email){
        this.adminid=adminid;
        this.password=password;
        this.name=name;
        this.phone=phone;
        this.email=email;
    }

    /**
     * @return the adminid
     */
    public String getAdminid() {
        return adminid;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * @param adminid the adminid to set
     */
    public void setAdminid(String adminid) {
        this.adminid = adminid;
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
     * @param phone the phone to set
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }
    
}