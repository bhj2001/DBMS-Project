package com.example.dbms.model;

public class containsprod {

    private String orderid;
    private String productid;
    private Integer quantity;

    public containsprod(){

    }

    public containsprod(String orderid, String productid, Integer quantity){
        this.orderid=orderid;
        this.productid=productid;
        this.quantity=quantity;
    }

    /**
     * @return the orderid
     */
    public String getOrderid() {
        return orderid;
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
     * @param orderid the orderid to set
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
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