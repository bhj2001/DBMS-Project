package com.example.dbms.dao;

import java.util.List;

import javax.validation.Valid;

import com.example.dbms.model.Attendance;
import com.example.dbms.model.employee;
import com.example.dbms.model.manages;

public interface employeeDAO {

    public employee getEmployee(String empid);

	public void editEmployeeProfile(employee e);

	public List<String> getEmployeesNameList();

	public void finallyMarkAttendance(Attendance attendance);

	public List<List<String>> getClientAttendanceList(String empid);

	public List<List<String>> getAllAttendanceList();

	public List<List<String>> getUserAttendanceList(String username);

	public List<List<String>> getEmployeesList();

	public void save(@Valid employee e);

	public List<employee> empList();

	public void addManager(manages m);

	public List<String> getManagers(String empid);

	public List<String> getSubordinates(String empid);
    

}