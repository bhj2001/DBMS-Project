package com.example.dbms.model;

public class manages {

    private String managerid;
    private String empid;

    public manages(){

    }

    public manages(String managerid,String empid){
        this.managerid=managerid;
        this.empid=empid;
    }

    /**
     * @return the empid
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * @return the managerid
     */
    public String getManagerid() {
        return managerid;
    }

    /**
     * @param empid the empid to set
     */
    public void setEmpid(String empid) {
        this.empid = empid;
    }

    /**
     * @param managerid the managerid to set
     */
    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }
    
}