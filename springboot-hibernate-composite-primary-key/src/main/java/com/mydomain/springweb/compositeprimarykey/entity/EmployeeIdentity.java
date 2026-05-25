package com.mydomain.springweb.compositeprimarykey.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeIdentity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String employeeId;
	private String departmentId;
	
	public EmployeeIdentity() {
		super();
		
	}

	public EmployeeIdentity(String employeeId, String departmentId) {
		super();
		this.employeeId = employeeId;
		this.departmentId = departmentId;
	}



	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getdepartmentId() {
		return departmentId;
	}

	public void setdepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentId, employeeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeIdentity other = (EmployeeIdentity) obj;
		return Objects.equals(departmentId, other.departmentId) && Objects.equals(employeeId, other.employeeId);
	}
	
	
}
