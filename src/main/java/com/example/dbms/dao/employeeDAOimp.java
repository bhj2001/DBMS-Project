package com.example.dbms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;
import javax.validation.Valid;

import com.example.dbms.model.Attendance;
import com.example.dbms.model.employee;
import com.example.dbms.model.manages;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class employeeDAOimp implements employeeDAO {

    private JdbcTemplate jdbcTemplate;

    public employeeDAOimp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public employee getEmployee(String empid) {
        String sql = "select * from employee where empid='" + empid + "'";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(employee.class));
    }

    @Override
    public void editEmployeeProfile(employee e) {
        String sql = "update employee set name='" + e.getName() + "' , email='" + e.getEmail() + "' , phone="
                + e.getPhone().toString() + " , address='" + e.getAddress() + "' where empid = '" + e.getEmpid() + "'";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    @Override
    public List<String> getEmployeesNameList() {
        String sql = "select * from employee";
        List<employee> l = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(employee.class));
        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < l.size(); i++)
            ret.add(l.get(i).getEmpid());
        return ret;
    }

    @Override
    public void finallyMarkAttendance(Attendance attendance) {
        HashMap<String, String> ma = attendance.getMa();
        if (ma == null)
            return;
        Set<String> keys = ma.keySet();

        Iterator<String> keysIterator = keys.iterator();

        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            System.out.println(key);
            String sql = "INSERT IGNORE INTO attendance(empid, date, value) VALUES(?,?,?)";
            if (ma.get(key) != null)
                jdbcTemplate.update(sql, new Object[] { key, attendance.getDate(), ma.get(key) });
        }
    }

    @Override
    public List<List<String>> getClientAttendanceList(String empid) {
        String sql = "select * from attendance where empid='" + empid + "'";
        List<Attendance> l = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Attendance.class));
        List<List<String>> ret = new ArrayList<List<String>>();
        for (int i = 0; i < l.size(); i++) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(l.get(i).getDate().toString());
            tmp.add(l.get(i).getValue());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public List<List<String>> getAllAttendanceList() {
        String sql = "select * from attendance";
        List<Attendance> l = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Attendance.class));
        List<List<String>> ret = new ArrayList<List<String>>();
        for (int i = 0; i < l.size(); i++) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(l.get(i).getEmpid());
            String sql1 = "select * from employee where empid='" + l.get(i).getEmpid() + "'";
            employee e = jdbcTemplate.queryForObject(sql1, BeanPropertyRowMapper.newInstance(employee.class));
            tmp.add(e.getName());
            tmp.add(l.get(i).getDate().toString());
            tmp.add(l.get(i).getValue());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public List<List<String>> getUserAttendanceList(String username) {
        List<List<String>> l = getAllAttendanceList();
        List<List<String>> ret = new ArrayList<List<String>>();
        System.out.println(username);
        System.out.println(l.toString());
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).get(0).equals(username)) {
                ret.add(l.get(i));
            }
        }
        return ret;
    }

    @Override
    public List<List<String>> getEmployeesList() {
        String sql = "select * from employee";
        List<employee> l = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(employee.class));
        List<List<String>> ret = new ArrayList<List<String>>();
        for (int i = 0; i < l.size(); i++) {
            List<String> tmp = new ArrayList<String>();
            tmp.add(l.get(i).getEmpid());
            tmp.add(l.get(i).getName());
            tmp.add(l.get(i).getEmail());
            tmp.add(l.get(i).getPhone().toString());
            tmp.add(l.get(i).getPosition());
            tmp.add(l.get(i).getAddress());
            ret.add(tmp);
        }
        return ret;
    }

    @Override
    public void save(@Valid employee e) {
        String sql = "insert into employee values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, e.getEmpid(), e.getName(), e.getPhone(), e.getEmail(), e.getPosition(), e.getAddress(),
                e.getPassword());
    }

    @Override
    public List<employee> empList() {
        String sql = "select * from employee";
        List<employee> l = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(employee.class));
        return l;
    }

    @Override
    public void addManager(manages m) {
        String sql="insert into manages values(?,?)";
        jdbcTemplate.update(sql, m.getManagerid(),m.getEmpid());
    }

    @Override
    public List<String> getManagers(String empid) {
        String sql="select * from manages";
        List<manages> lm=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(manages.class));
        List<String> ret=new ArrayList<String>();
        for(manages m: lm) if(m.getEmpid().equals(empid)) ret.add(m.getManagerid());
        return ret;
    }

    @Override
    public List<String> getSubordinates(String empid) {
        String sql="select * from manages";
        List<manages> lm=jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(manages.class));
        List<String> ret=new ArrayList<String>();
        for(manages m: lm) if(m.getManagerid().equals(empid)) ret.add(m.getEmpid());
        return ret;
    }

}