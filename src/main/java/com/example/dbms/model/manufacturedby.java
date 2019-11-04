package com.example.dbms.model;

public class manufacturedby {

    private String productid;
    private String companyid;

    public manufacturedby(){

    }

    public manufacturedby(String productid,String companyid){
        this.companyid=companyid;
        this.productid=productid;
    }

    /**
     * @return the companyid
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * @return the productid
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @param companyid the companyid to set
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
    
}