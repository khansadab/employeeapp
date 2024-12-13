package com.cg.employee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.employee.entity.Employee;
import com.cg.employee.repository.EmployeeRepository;



@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeRepository employeeRepository;
	
	Logger logger =LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	ResponseEntity<?> responseEntity;
	
    @Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public ResponseEntity<?> saveEmployee(Employee employee) {
		try {
			if(employee != null) {
				responseEntity = new ResponseEntity<>(employeeRepository.save(employee),HttpStatus.OK);
				logger.info("Employee data saved into database");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data not save into database");
			responseEntity = new ResponseEntity<>("Employee data not save into database ! please try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<?> getAllEmployee() {
		try {
				responseEntity = new ResponseEntity<>(employeeRepository.findAll(),HttpStatus.OK);
				logger.info("Employee data fatched from database");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data unable fatched from database");
			responseEntity = new ResponseEntity<>("Employee data unable fatched from database! please try after sometime", HttpStatus.NOT_FOUND);
		
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<?> getEmployeeById(Integer id) {
		try {
			if(id != null) {
				responseEntity = new ResponseEntity<>(employeeRepository.findById(id),HttpStatus.OK);
				logger.info("Employee data fatched from database");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data not save into database");
			responseEntity = new ResponseEntity<>("Employee not found based on id "+id, HttpStatus.NOT_FOUND);
		
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<?> updateEmployee(Employee employee) {
		try {
			if(employee.getId() != null) {
				Employee empDbObj = employeeRepository.findById(employee.getId()).get();
				if(empDbObj != null) {
					empDbObj.setFirstName(employee.getFirstName());
					empDbObj.setLastName(employee.getLastName());
					empDbObj.setGender(employee.getGender());
					empDbObj.setEmail(employee.getEmail());
					empDbObj.setPhone(employee.getPhone());
					empDbObj.setSalary(employee.getSalary());
					empDbObj.setDepartment(employee.getDepartment());
					empDbObj.setRole(employee.getRole());
					
				}
				responseEntity = new ResponseEntity<>(employeeRepository.save(empDbObj),HttpStatus.OK);
				logger.info("Employee data updated and saved into database");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data not updated");
			responseEntity = new ResponseEntity<>("Employee data not updated ! please try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<?> deleteEmployeeById(Integer id) {
		try {
			if(id != null) {
				employeeRepository.deleteById(id);
				responseEntity = new ResponseEntity<>("Employee deleted",HttpStatus.OK);
				logger.info("Employee data deleted from database");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Employee data not found to delete based on id "+id);
			responseEntity = new ResponseEntity<>("Employee data not found to delete based on id "+id, HttpStatus.NOT_FOUND);
		
		}
		return responseEntity;
	}

}
