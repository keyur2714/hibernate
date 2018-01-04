package com.hibernate.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {

	@Id
	@Column(name="dept_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="dept_name")
	private String name;
	
	@Column(name="dept_location")
	private String location;
	
	@Column(name="dept_address")
	private String address;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="department_employee_table",joinColumns=@JoinColumn(name="dept_id"),inverseJoinColumns=@JoinColumn(name="employee_id"))
	private Set<Employee> employeesSet = new HashSet<Employee>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Employee> getEmployeesSet() {
		return employeesSet;
	}

	public void setEmployeesSet(Set<Employee> employeesSet) {
		this.employeesSet = employeesSet;
	}
	
}
