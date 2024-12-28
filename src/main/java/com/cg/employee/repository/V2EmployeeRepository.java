package com.cg.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.employee.entity.Employee;

@Repository
public interface V2EmployeeRepository extends JpaRepository<Employee, Integer> {

}
