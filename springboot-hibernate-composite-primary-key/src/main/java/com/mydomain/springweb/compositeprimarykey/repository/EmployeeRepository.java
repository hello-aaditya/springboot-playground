package com.mydomain.springweb.compositeprimarykey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mydomain.springweb.compositeprimarykey.entity.Employee;
import com.mydomain.springweb.compositeprimarykey.entity.EmployeeIdentity;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeIdentity> {

}
