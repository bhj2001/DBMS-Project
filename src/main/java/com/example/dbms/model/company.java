package com.example.dbms.model;

public class company{
    
    private String companyid;
    private String companyname;

    public company(){

    }

    public company(String companyid,String companyname){
        this.companyid=companyid;
        this.companyname=companyname;
    }

    /**
     * @return the companyid
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * @return the companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * @param companyid the companyid to set
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    /**
     * @param companyname the companyname to set
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    
}