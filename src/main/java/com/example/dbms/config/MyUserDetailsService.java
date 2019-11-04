package com.example.dbms.config;

import com.example.dbms.dao.adminDAO;
import com.example.dbms.dao.employeeDAO;
import com.example.dbms.dao.retailerDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    retailerDAO retailerdao;

    @Autowired
    employeeDAO employeedao;

    @Autowired
    adminDAO admindao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(username,retailerdao,employeedao,admindao);
    }

    
}