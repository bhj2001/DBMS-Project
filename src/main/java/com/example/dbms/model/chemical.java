package com.example.dbms.model;

public class chemical {

    private String chemid;
    private String name;

    public chemical(){

    }

    public chemical(String chemid,String name){
        this.chemid=chemid;
        this.name=name;
    }

    /**
     * @return the chemid
     */
    public String getChemid() {
        return chemid;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param chemid the chemid to set
     */
    public void setChemid(String chemid) {
        this.chemid = chemid;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}