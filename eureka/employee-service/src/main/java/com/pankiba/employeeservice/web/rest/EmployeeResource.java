package com.pankiba.employeeservice.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankiba.employeeservice.domain.Employee;
import com.pankiba.employeeservice.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;

	/*
	 * Retrieve all employees
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {

		log.info("Get employees");
		List<Employee> employeeList = employeeService.getEmployees();

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

	/*
	 * Retrieve all employees with rewards
	 */
	@GetMapping("/employees-with-rewards")
	public ResponseEntity<List<Employee>> getEmployeesWithRewards() {

		log.info("Get employees with rewards using discovery client - rest template");
		List<Employee> employeeList = employeeService.getEmployeesWithRewards();

		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}

}
