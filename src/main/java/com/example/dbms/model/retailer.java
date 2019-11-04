package com.example.dbms.model;

public class retailer {

    private String rid;
    private String name;
    private Integer phone;
    private String email;
    private String shopaddress;
    private String ownersname;
    private String password;
    private String mPassword;

    public retailer(){

    }

    public retailer(String rid,String name,Integer phone,String email,String shopaddress,String ownersname,String password){
        this.rid=rid;
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.shopaddress=shopaddress;
        this.ownersname=ownersname;
        this.password=password;
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
     * @return the ownersname
     */
    public String getOwnersname() {
        return ownersname;
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
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @return the shopaddress
     */
    public String getShopaddress() {
        return shopaddress;
    }

    /**
     * @return the mPassword
     */
    public String getmPassword() {
        return mPassword;
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
     * @param ownersname the ownersname to set
     */
    public void setOwnersname(String ownersname) {
        this.ownersname = ownersname;
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

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * @param mPassword the mPassword to set
     */
    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    /**
     * @param shopaddress the shopaddress to set
     */
    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }
    
}