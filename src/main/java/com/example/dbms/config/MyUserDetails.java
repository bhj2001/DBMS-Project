package com.example.dbms.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.example.dbms.dao.adminDAO;
import com.example.dbms.dao.employeeDAO;
import com.example.dbms.dao.retailerDAO;
import com.example.dbms.model.admin;
import com.example.dbms.model.employee;
import com.example.dbms.model.retailer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails {

    private String userName;

    private retailerDAO retailerdao;

    private employeeDAO employeedao;

    private adminDAO admindao;

    public MyUserDetails(String userName,retailerDAO retailerdao,employeeDAO employeedao,adminDAO admindao) {
        this.userName=userName;
        this.retailerdao=retailerdao;
        this.employeedao=employeedao;
        this.admindao=admindao;
    }

    public MyUserDetails(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(!retailerdao.doesUsernameExists(userName)) return Arrays.asList();
        List<retailer> lr=retailerdao.list();
        List<employee> le=employeedao.empList();
        List<admin> la=admindao.adminList();
        for(retailer rr:lr) if(userName.equals(rr.getRid())) return Arrays.asList(new SimpleGrantedAuthority("CLIENT"));
        for(employee ee:le) if(userName.equals(ee.getEmpid())) return Arrays.asList(new SimpleGrantedAuthority("EMPLOYEE"));
        for(admin aa:la) if(userName.equals(aa.getAdminid())) return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
        return Arrays.asList();
    }

    @Override
    public String getPassword() {
        List<retailer> lr=retailerdao.list();
        List<employee> le=employeedao.empList();
        List<admin> la=admindao.adminList();
        for(retailer rr:lr) if(userName.equals(rr.getRid())) return rr.getPassword();
        for(employee ee:le) if(userName.equals(ee.getEmpid())) return ee.getPassword();
        for(admin aa:la) if(userName.equals(aa.getAdminid())) return aa.getPassword();
        return "non matching password 123";
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}