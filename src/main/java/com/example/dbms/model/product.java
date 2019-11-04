package com.example.dbms.model;

import java.util.HashMap;

public class product {

    private String productid;
    private String name;
    private Integer cost;
    private String companies ;
    private String chemicals ;
    private HashMap<String, Integer> costlist;

    public product(){

    }

    public product(String productid,String name,Integer cost, String companies , String chemicals , HashMap<String,Integer> costlist){
        this.productid=productid;
        this.name=name;
        this.cost=cost;
        this.chemicals=chemicals;
        this.companies=companies;
        this.costlist=costlist;
    }


    /**
     * @return the costlist
     */
    public HashMap<String, Integer> getCostlist() {
        return costlist;
    }

    /**
     * @param costlist the costlist to set
     */
    public void setCostlist(HashMap<String, Integer> costlist) {
        this.costlist = costlist;
    }
    
    /**
     * @return the cost
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * @return the chemicals
     */
    public String getChemicals() {
        return chemicals;
    }
    /**
     * @return the companies
     */
    public String getCompanies() {
        return companies;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the productid
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param chemicals the chemicals to set
     */
    public void setChemicals(String chemicals) {
        this.chemicals = chemicals;
    }

    /**
     * @param companies the companies to set
     */
    public void setCompanies(String companies) {
        this.companies = companies;
    }
    

    /**
     * @param productid the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }
}