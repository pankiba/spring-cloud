package com.pankiba.employeeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankiba.employeeservice.domain.Employee;
import com.pankiba.employeeservice.domain.Grade;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByGrade(Grade grade);
}
