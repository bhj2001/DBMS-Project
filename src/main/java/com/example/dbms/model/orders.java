package com.example.dbms.model;

import java.util.HashMap;

public class orders {

    private String orderid;
    private String retailerid;
    private String status;
    private HashMap<String,Integer> orderquantity;

    public orders(){

    }

    public orders(String orderid,String retailerid,String status,HashMap<String,Integer> orderquantity){
        this.orderid=orderid;
        this.retailerid=retailerid;
        this.status=status;
        this.orderquantity=orderquantity;
    }

    /**
     * @return the orderid
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * @return the orderquantity
     */
    public HashMap<String, Integer> getOrderquantity() {
        return orderquantity;
    }

    /**
     * @return the retailerid
     */
    public String getRetailerid() {
        return retailerid;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param orderid the orderid to set
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * @param retailerid the retailerid to set
     */
    public void setRetailerid(String retailerid) {
        this.retailerid = retailerid;
    }

    /**
     * @param orderquantity the orderquantity to set
     */
    public void setOrderquantity(HashMap<String, Integer> orderquantity) {
        this.orderquantity = orderquantity;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}