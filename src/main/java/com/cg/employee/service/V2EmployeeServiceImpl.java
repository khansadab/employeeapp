package com.cg.employee.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.employee.entity.Employee;
import com.cg.employee.exceptions.EmployeeNotFoundException;
import com.cg.employee.repository.V2EmployeeRepository;

@Service
public class V2EmployeeServiceImpl implements V2EmployeeService {
	
	Logger logger =LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	V2EmployeeRepository employeeRepository;

	public V2EmployeeServiceImpl(V2EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		Employee employeeDbObj=null;
		try {
			if(employee != null) {
				employeeDbObj	 = employeeRepository.save(employee);
				logger.info("Employee data saved into database");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data not save into database");
		
		}
		return employeeDbObj;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList=null;
		try {
			empList = employeeRepository.findAll();
			logger.info("Employee data fatched from database");		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data unable fatched from database");
		
		}
		return empList;
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		Employee empDbObj=null;
		try {
			if(id != null) {
				empDbObj = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee not found based on id "+id));
				logger.info("Employee data fatched from database");		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data not fatched from database");		
		}
		return empDbObj;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee empDbObj=null;
		try {
			if(employee.getId() != null) {
				empDbObj = employeeRepository.findById(employee.getId()).orElseThrow(()-> new EmployeeNotFoundException("Employee not found based on id "+employee.getId()));
				if(empDbObj != null) {
					empDbObj.setFirstName(employee.getFirstName());
					empDbObj.setLastName(employee.getLastName());
					empDbObj.setGender(employee.getGender());
					empDbObj.setEmail(employee.getEmail());
					empDbObj.setPhone(employee.getPhone());
					empDbObj.setSalary(employee.getSalary());
					empDbObj.setDepartment(employee.getDepartment());
					empDbObj.setRole(employee.getRole());
					empDbObj = employeeRepository.save(empDbObj);
					logger.info("Employee data updated and saved into database");		
				}
	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data not updated");
		
		}
		return empDbObj;
	}

	@Override
	public String deleteEmployeeById(Integer id) {
		String result=null;
		try {
			if(id != null) {
				Employee empObj = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found based on id "+id));
				if(empObj.getId() != null) {
					employeeRepository.deleteById(id);
					logger.info("Employee data deleted from database");
					result="Employee deleted with id "+id;
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee not found to delete based on id "+id);
		
		}
		return result;
	}


}
