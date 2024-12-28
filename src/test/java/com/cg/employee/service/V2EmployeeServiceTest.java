package com.cg.employee.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.employee.entity.Employee;
import com.cg.employee.exceptions.EmployeeNotFoundException;
import com.cg.employee.repository.V2EmployeeRepository;
// Nice tutorial once go throug for unit testing of every layers
// https://salithachathuranga94.medium.com/unit-and-integration-testing-in-spring-boot-micro-service-901fc53b0dff
@SpringBootTest
public class V2EmployeeServiceTest {
	
	@Autowired
	V2EmployeeService employeeService;
	
	@MockBean
	V2EmployeeRepository employeeRepository;
	
	@Test
	public void saveEmployeeTest() {
		Employee empObj = new Employee(101, "firstName", "lastName", "test@gmail.com", "Male", "89896769090", "670000", "HR",  "Senior HR");
		Mockito.when(employeeRepository.save(empObj)).thenReturn(empObj);
		assertThat(employeeService.saveEmployee(empObj)).isEqualTo(empObj);
		//Mockito.when(employeeRepository.save(null)).thenThrow(NullPointerException.class);
		//assertEquals(employeeService.saveEmployee(empObj), empObj);
	    //assertEquals(employeeService.saveEmployee(null), NullPointerException.class);
	}
	
	@Test
	public void getAllEmployeeTest() {
		List<Employee> empList = Arrays.asList(
				new Employee(101, "firstName", "lastName", "test@gmail.com", "Male", "89896769090", "670000", "HR",  "Senior HR"),
				new Employee(101, "XXX", "ZZZ", "xzyt@gmail.com", "Female", "1000000", "670000", "HR",  "Senior HR"),
				new Employee(101, "abc", "dcx", "dcx@gmail.com", "Male", "89896769090", "670000", "Developer",  "Senior Developer")
		);
		Mockito.when(employeeRepository.findAll()).thenReturn(empList);
		assertThat(employeeService.getAllEmployee()).isEqualTo(empList);
		
	}
	
	@Test
	public void getEmployeeByIdTest() {
		Employee empObj = new Employee(1, "firstName", "lastName", "test@gmail.com", "Male", "89896769090", "670000", "HR",  "Senior HR");
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(empObj));
		Mockito.when(employeeRepository.findById(101)).thenThrow(EmployeeNotFoundException.class);
		assertThat(employeeService.getEmployeeById(1)).isEqualTo(empObj);
	}
	
	@Test
	public void updateEmployeeTest() {
		Employee empObj = new Employee(1, "firstName", "lastName", "test@gmail.com", "Male", "89896769090", "670000", "HR",  "Senior HR");
		Mockito.when(employeeRepository.findById(1)).thenReturn(Optional.of(empObj));
		empObj.setFirstName("test1");
		empObj.setLastName("test2");
		empObj.setGender("female");
		empObj.setEmail("test12@gmail.com");
		empObj.setPhone("90909090");
		empObj.setSalary("900000");
		empObj.setDepartment("IT");
		empObj.setRole("developer");
		Mockito.when(employeeRepository.save(empObj)).thenReturn(empObj);
		assertThat(employeeService.updateEmployee(empObj)).isEqualTo(empObj);
	}
	
	@Test
	public void deleteEmployeeByIdTest() {
		Employee empObj = new Employee(1, "firstName", "lastName", "test@gmail.com", "Male", "89896769090", "670000", "HR",  "Senior HR");
		when(employeeRepository.findById(1)).thenReturn(Optional.of(empObj));
		employeeService.deleteEmployeeById(empObj.getId());
		verify(employeeRepository, times(1)).deleteById(empObj.getId());
		
	}
	
	

}
