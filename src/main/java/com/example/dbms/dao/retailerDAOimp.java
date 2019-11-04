package com.example.dbms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import com.example.dbms.model.admin;
import com.example.dbms.model.employee;
import com.example.dbms.model.feedback;
import com.example.dbms.model.retailer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class retailerDAOimp implements retailerDAO {

    @Autowired
    employeeDAO employeedao;

    @Autowired
    adminDAO admindao;

    private JdbcTemplate jdbcTemplate;

    public retailerDAOimp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<retailer> list() {
        String sql = "select * from retailer";
        System.out.println(sql);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(retailer.class));
    }

    @Override
    public retailer getRetailer(String rid) {
        String sql = "select * from retailer where rid='" + rid + "'";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(retailer.class));
    }

    @Override
    public void editRetailer(retailer r) {
        String sql = "update retailer set name='" + r.getName() + "' , phone=" + r.getPhone().toString() + " , email='"
                + r.getEmail() + "' , shopaddress='" + r.getShopaddress() + "'  , ownersname = '" + r.getOwnersname()
                + "'  where rid='" + r.getRid() + "'";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    @Override
    public List<retailer> retailerList() {
        String sql = "select * from retailer";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(retailer.class));
    }

    @Override
    public void save(@Valid retailer r) {
        String sql = "insert into retailer values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, r.getRid(), r.getName(), r.getPhone(), r.getEmail(), r.getShopaddress(),
                r.getOwnersname(), r.getPassword());
    }

    @Override
    public List<List<String>> getRetailerList() {
        List<retailer> l = retailerList();
        List<List<String>> ret = new ArrayList<List<String>>();
        for (retailer rr : l) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(rr.getRid());
            tmp.add(rr.getName());
            tmp.add(rr.getEmail());
            tmp.add(rr.getPhone().toString());
            tmp.add(rr.getShopaddress());
            tmp.add(rr.getOwnersname());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public void saveFeedback(@Valid feedback f) {
        String sql="insert into feedback values(?,?,?)";
        jdbcTemplate.update(sql, f.getRid(),f.getDate(),f.getFeedbacktext());   
    }

    @Override
    public List<List<String>> getFeedbacks() {
        String sql="select * from feedback";
        List<feedback> lf=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(feedback.class));
        List<List<String>> ret=new ArrayList<List<String>>();
        for(feedback ff:lf){
            List<String> tmp=new ArrayList<String>();
            tmp.add(ff.getRid());
            tmp.add(getRetailer(ff.getRid()).getName());
            tmp.add(ff.getDate().toString());
            tmp.add(ff.getFeedbacktext());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public boolean doesUsernameExists(String s) {
        List<retailer> lr=retailerList();
        for(retailer rr:lr) if(s.equals(rr.getRid())) return true;
        List<employee> le=employeedao.empList();
        for(employee ee:le) if(s.equals(ee.getEmpid())) return true;
        List<admin> la=admindao.adminList();
        for(admin aa:la) if(s.equals(aa.getAdminid())) return true;
 
        return false;
    }

    @Override
    public String getrole(String s) {
        if(!doesUsernameExists(s)) return "node";

        List<retailer> lr=retailerList();
        for(retailer rr:lr) if(s.equals(rr.getRid())) return "CLIENT";
        List<employee> le=employeedao.empList();
        for(employee ee:le) if(s.equals(ee.getEmpid())) return "EMPLOYEE";
        List<admin> la=admindao.adminList();
        for(admin aa:la) if(s.equals(aa.getAdminid())) return "ADMIN";

        return "none";

    }
    
}