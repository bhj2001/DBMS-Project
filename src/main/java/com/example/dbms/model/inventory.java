package com.example.dbms.model;

import java.util.HashMap;

public class inventory {

    private String productid;
    private Integer quantity;
    HashMap<String,Integer> ma;

    public inventory(){

    }

    public inventory(String productid,Integer quantity,HashMap<String,Integer> ma){
        this.productid=productid;
        this.quantity=quantity;
        this.ma=ma;
    }

    /**
     * @return the ma
     */
    public HashMap<String, Integer> getMa() {
        return ma;
    }

    /**
     * @param ma the ma to set
     */
    public void setMa(HashMap<String, Integer> ma) {
        this.ma = ma;
    }
    

    /**
     * @return the productid
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param productid the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
}