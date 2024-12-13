package com.cg.employee.service;

import org.springframework.http.ResponseEntity;

import com.cg.employee.entity.Employee;

public interface EmployeeService {
	
	public ResponseEntity<?> saveEmployee(Employee employee);
	public ResponseEntity<?> getAllEmployee();
	public ResponseEntity<?> getEmployeeById(Integer id);
	public ResponseEntity<?> updateEmployee(Employee employee);
	public ResponseEntity<?> deleteEmployeeById(Integer id);

}
