package com.example.dbms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import com.example.dbms.model.chemical;
import com.example.dbms.model.company;
import com.example.dbms.model.containschemical;
import com.example.dbms.model.containsprod;
import com.example.dbms.model.inventory;
import com.example.dbms.model.manufacturedby;
import com.example.dbms.model.orders;
import com.example.dbms.model.product;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class productDAOimp implements productDAO {

    private JdbcTemplate jdbcTemplate;

    public productDAOimp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public product getProduct(String productid) {
        String sql = "select * from product where productid = '" + productid + "'";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(product.class));
    }

    @Override
    public List<containsprod> getContainsProd(String orderid) {
        String sql = "select * from containsprod where orderid='" + orderid + "'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(containsprod.class));
    }

    @Override
    public List<List<String>> getAllOrdersList() {
        String sql = "select * from orders";
        List<orders> l = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(orders.class));
        List<List<String>> ret = new ArrayList<List<String>>();
        for (orders oo : l) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(oo.getOrderid());
            tmp.add(oo.getRetailerid());
            String s = new String();
            for (containsprod cp : getContainsProd(oo.getOrderid())) {
                s += getProduct(cp.getProductid()).getName() + " , quantity = " + cp.getQuantity().toString() + "<br>";
            }
            tmp.add(s);
            tmp.add(oo.getStatus());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public void addProduct(product p) {
        String sql = "insert into product values(?,?,?)";
        String sql1 = "insert into inventory values(?,?)";
        jdbcTemplate.update(sql, p.getProductid(), p.getName(), p.getCost());
        jdbcTemplate.update(sql1, p.getProductid(), new Integer(0));
    }

    @Override
    public List<product> getProdList() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(product.class));
    }

    @Override
    public List<List<String>> getProductsAndPrice() {
        List<product> l = getProdList();
        List<List<String>> ret = new ArrayList<List<String>>();
        for (product pp : l) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(pp.getProductid());
            tmp.add(pp.getCost().toString());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public void finalPlaceOrder(orders order) {
        HashMap<String, Integer> ma = order.getOrderquantity();
        Set<String> keys = ma.keySet();

        Iterator<String> keysIterator = keys.iterator();

        String sql1 = "insert into orders values(?,?,?)";

        jdbcTemplate.update(sql1, order.getOrderid(), order.getRetailerid(), order.getStatus());

        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            System.out.println(key);
            String sql = "INSERT INTO containsprod VALUES(?,?,?)";
            if (ma.get(key) == 0)
                continue;
            jdbcTemplate.update(sql, order.getOrderid(), key, ma.get(key));
        }

    }

    @Override
    public List<List<String>> getBillPrice(String id) {
        // String sql = "select username, name, typeofgood, quantity*cost as total from
        // orders, Product, clients where orderid='" + id + "' and
        // typeofgood=producttype and clientusername=username";
        String sql = "select retailer.rid , retailer.name, product.name as medname, containsprod.quantity*product.cost as total from orders, product, retailer , containsprod where orders.orderid='"
                + id
                + "' and containsprod.productid=product.productid and  orders.orderid=containsprod.orderid and retailer.rid=orders.retailerid";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<List<String>>>() {
            @SuppressWarnings("null")
            public List<List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<List<String>> orderdetails = new ArrayList<List<String>>();
                while (rs.next()) {
                    List<String> temp = new ArrayList<>();
                    String username = rs.getString("rid");
                    String name = rs.getString("name");
                    String typeofgood = rs.getString("medname");
                    String cost = Integer.toString(rs.getInt("total"));
                    temp.add(name);
                    temp.add(typeofgood);
                    temp.add(cost);
                    temp.add(username);
                    orderdetails.add(temp);
                }
                // System.out.println("Hello");
                // System.out.println(Arrays.toString(products.toArray()));
                return orderdetails;
            }
        });
    }

    @Override
    public void updateOrderStatus(String orderid, String username, String s) {
        String sql = "update orders set status='" + s + "' where orderid='" + orderid + "' and retailerid='" + username
                + "'";
        jdbcTemplate.update(sql);
    }

    @Override
    public List<List<String>> getOrdersForClient(String cname) {
        List<List<String>> l = getAllOrdersList();
        List<List<String>> ret = new ArrayList<List<String>>();
        for (List<String> ls : l) {
            if (ls.get(1).equals(cname)) {
                ret.add(ls);
            }
        }
        return ret;
    }

    @Override
    public List<List<String>> getSpecificInvoiceList(String username) {
        List<List<String>> l = getAllOrdersList();
        List<List<String>> ret = new ArrayList<List<String>>();
        for (List<String> ls : l) {
            if (ls.get(1).equals(username)) {
                List<String> tmp = new ArrayList<String>();
                tmp.add(ls.get(0));
                tmp.add(ls.get(2));
                ret.add(tmp);
            }
        }
        return ret;
    }

    @Override
    public List<List<String>> getInvoiceList() {
        List<List<String>> l = getAllOrdersList();
        List<List<String>> ret = new ArrayList<List<String>>();
        for (List<String> ls : l) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(ls.get(0));
            tmp.add(ls.get(1));
            tmp.add(ls.get(2));
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public boolean existsProdWithId(String productid) {
        List<product> lp = getProdList();
        for (product pp : lp) {
            if (productid.equals(pp.getProductid())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsCompWithId(String s) {
        String sql = "select * from company";
        List<company> lc = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(company.class));
        for (company cc : lc)
            if (s.equals(cc.getCompanyid()))
                return true;
        return false;
    }

    @Override
    public boolean existsChemWithId(String s) {
        String sql = "select * from chemical";
        List<chemical> lc = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(chemical.class));
        for (chemical cc : lc)
            if (s.equals(cc.getChemid()))
                return true;
        return false;
    }

    @Override
    public void addProdManufByComp(String productid, String s) {
        String sql = "insert into manufacturedby values(?,?)";
        jdbcTemplate.update(sql, productid, s);
    }

    @Override
    public void addProdContainsChem(String productid, String s) {
        String sql = "insert into containschemical values(?,?)";
        jdbcTemplate.update(sql, productid, s);
    }

    @Override
    public List<String> getProducts() {
        List<product> lp = getProdList();
        List<String> ret = new ArrayList<String>();
        for (product pp : lp) {
            ret.add(pp.getProductid());
        }
        return ret;
    }

    @Override
    public void updatePrices(product p) {
        HashMap<String, Integer> costlist = p.getCostlist();
        if (costlist == null)
            return;

        Set<String> keys = costlist.keySet();

        Iterator<String> keysIterator = keys.iterator();

        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            System.out.println(key);
            if (costlist.get(key) != null) {
                String sql = "update product set cost=? where productid = ?;";
                jdbcTemplate.update(sql, new Object[] { costlist.get(key), key });
            }
        }
    }

    @Override
    public List<List<String>> getAllProducts() {
        List<product> lp = getProdList();
        List<List<String>> ret = new ArrayList<List<String>>();
        for (product pp : lp) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(pp.getProductid());
            tmp.add(pp.getName());

            String lisofcomp = new String();
            String sql = "select * from manufacturedby";
            List<manufacturedby> lmfb = jdbcTemplate.query(sql,
                    BeanPropertyRowMapper.newInstance(manufacturedby.class));
            for (manufacturedby mfb : lmfb)
                if (pp.getProductid().equals(mfb.getProductid()))
                    lisofcomp += mfb.getCompanyid() + "<br>";
            tmp.add(lisofcomp);

            String listofchems = new String();
            String sql1 = "select * from containschemical";
            List<containschemical> lcc = jdbcTemplate.query(sql1,
                    BeanPropertyRowMapper.newInstance(containschemical.class));
            for (containschemical cc : lcc)
                if (pp.getProductid().equals(cc.getProductid()))
                    listofchems += cc.getChemid() + "<br>";
            tmp.add(listofchems);

            tmp.add(pp.getCost().toString());

            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public List<List<String>> getCompList() {
        String sql = "select * from company";
        List<company> lc = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(company.class));
        List<List<String>> ret = new ArrayList<List<String>>();
        for (company c : lc) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(c.getCompanyid());
            tmp.add(c.getCompanyname());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public List<List<String>> getChemList() {
        String sql = "select * from chemical";
        List<chemical> lc = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(chemical.class));
        List<List<String>> ret = new ArrayList<List<String>>();
        for (chemical c : lc) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(c.getChemid());
            tmp.add(c.getName());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public List<List<String>> getSpecificOrdersList(String clientusername) {
        String sql = "select * from orders where retailerid='" + clientusername + "'";
        List<orders> l = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(orders.class));
        List<List<String>> ret = new ArrayList<List<String>>();
        for (orders oo : l) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(oo.getOrderid());
            tmp.add(oo.getRetailerid());
            String s = new String();
            for (containsprod cp : getContainsProd(oo.getOrderid())) {
                s += getProduct(cp.getProductid()).getName() + " , quantity = " + cp.getQuantity().toString() + "<br>";
            }
            tmp.add(s);
            tmp.add(oo.getStatus());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public List<String> getProdListInventory() {
        List<product> lp = getProdList();
        List<String> ret = new ArrayList<String>();
        for (product p : lp)
            ret.add(p.getProductid());
        return ret;
    }

    @Override
    public inventory getInventory() {
        inventory ret=new inventory();
        String sql="select * from inventory";
        List<inventory> linv=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(inventory.class));
        HashMap<String,Integer> tmpma=new HashMap<String,Integer>();
        for(inventory inv:linv){
            tmpma.put(inv.getProductid(), inv.getQuantity());
        }
        ret.setMa(tmpma);
        return ret;
    }

    @Override
    public void updateInventory(String productid,Integer quantity) {
        String sql="update inventory set quantity="+quantity.toString()+" where productid='"+productid+"'";
        jdbcTemplate.update(sql);
    }


    @Override
    public void alterInventory(inventory inv) {
        String sql="select * from inventory";
        List<inventory> linv=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(inventory.class));
        
        HashMap<String,Integer> ma=inv.getMa();
        
        for(inventory it:linv){
            updateInventory(it.getProductid(), ma.get(it.getProductid()));
        }
    }

    
    

    

    
}