package com.pankiba.employeeservice.service;

import java.util.List;

import com.pankiba.employeeservice.domain.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public List<Employee> getEmployeesWithRewards();

}
