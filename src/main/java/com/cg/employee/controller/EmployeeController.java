package com.cg.employee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cg.employee.entity.Employee;
import com.cg.employee.service.EmployeeService;

@RestController
public class EmployeeController {

	EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("employee/save")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@GetMapping("employee/getall")
	public ResponseEntity<?> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("employee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) {
		return employeeService.getEmployeeById(id);

	}

	@PutMapping("employee/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);

	}

	@DeleteMapping("employee/delete")
	public ResponseEntity<?> deleteEmployeeById(Integer id) {

		return employeeService.deleteEmployeeById(id);
	}

}
