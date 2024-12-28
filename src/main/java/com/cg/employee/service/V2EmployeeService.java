package com.cg.employee.service;

import java.util.List;

import com.cg.employee.entity.Employee;

public interface V2EmployeeService {

	public Employee saveEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	public Employee getEmployeeById(Integer id);
	public Employee updateEmployee(Employee employee);
	public String deleteEmployeeById(Integer id);
}
