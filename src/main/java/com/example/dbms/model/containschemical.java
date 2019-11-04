package com.example.dbms.model;

public class containschemical {

    private String productid;
    private String chemid;

    public containschemical(){

    }

    public containschemical(String productid,String chemid){
        this.chemid=chemid;
        this.productid=productid;
    }

    /**
     * @return the chemid
     */
    public String getChemid() {
        return chemid;
    }

    /**
     * @return the productid
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @param chemid the chemid to set
     */
    public void setChemid(String chemid) {
        this.chemid = chemid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    
}