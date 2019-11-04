package com.example.dbms.dao;

import java.util.List;

import com.example.dbms.model.containsprod;
import com.example.dbms.model.inventory;
import com.example.dbms.model.orders;
import com.example.dbms.model.product;

public interface productDAO {

	product getProduct(String productid);

	List<containsprod> getContainsProd(String orderid);
	
	List<List<String>> getAllOrdersList();

	void addProduct(product p);

	List<product> getProdList();

	List<List<String>> getProductsAndPrice();

	void finalPlaceOrder(orders order);

	List<List<String>> getBillPrice(String id);

	void updateOrderStatus(String orderid, String username, String s);

	List<List<String>> getOrdersForClient(String cname);

	List<List<String>> getSpecificInvoiceList(String username);

	List<List<String>> getInvoiceList();

	boolean existsProdWithId(String productid);

	boolean existsCompWithId(String s);

	public boolean existsChemWithId(String s);

	void addProdManufByComp(String productid, String s);

	void addProdContainsChem(String productid, String s);

	List<String> getProducts();

	void updatePrices(product p);

	List<List<String>> getAllProducts();

	List<List<String>> getCompList();

	List<List<String>> getChemList();

	List<List<String>> getSpecificOrdersList(String clientusername);

	List<String> getProdListInventory();

	inventory getInventory();

	void updateInventory(String productid,Integer quantity);

	void alterInventory(inventory inv);

}