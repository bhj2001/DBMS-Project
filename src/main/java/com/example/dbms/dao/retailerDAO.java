package com.example.dbms.dao;

import java.util.List;

import javax.validation.Valid;

import com.example.dbms.model.feedback;
import com.example.dbms.model.retailer;

public interface retailerDAO {

    public List<retailer> list();

	public retailer getRetailer(String rid);

	public void editRetailer(retailer r);

	public List<retailer> retailerList();

	public void save(@Valid retailer r);

	public List<List<String>> getRetailerList();

	public void saveFeedback(@Valid feedback f);

	public List<List<String>> getFeedbacks();

	public boolean doesUsernameExists(String s);

	public String getrole(String uname);
}