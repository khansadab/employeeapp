package com.cg.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.employee.entity.Employee;
import com.cg.employee.service.V2EmployeeService;

@RestController
@RequestMapping("restapi/v2/")
public class V2EmployeeController {
	

	V2EmployeeService employeeService;


	public V2EmployeeController(V2EmployeeService v2EmployeeService) {
		this.employeeService = v2EmployeeService;
	}

	@PostMapping("employee/save")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@GetMapping("employee/getall")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer id) {
		return employeeService.getEmployeeById(id);

	}

	@PutMapping("employee/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);

	}

	@DeleteMapping("employee/delete/{id}")
	public String deleteEmployeeById(@PathVariable("id") Integer id) {

		return employeeService.deleteEmployeeById(id);
	}


}
