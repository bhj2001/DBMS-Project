package com.example.dbms.dao;

import java.util.List;

import javax.sql.DataSource;

import com.example.dbms.model.admin;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class adminDAOimp implements adminDAO {

    private JdbcTemplate jdbcTemplate;

    public adminDAOimp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<admin> adminList() {
        String sql="select * from admin";
        return jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(admin.class));
    }

    
}