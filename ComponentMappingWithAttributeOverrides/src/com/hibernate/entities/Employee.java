package com.hibernate.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int employeeId;
	
	@Column(name="employee_name",length=60)
	private String employeeName;
	
	@Column(name="salary",columnDefinition="DECIMAL(7,2)")
	private Double salary;
	
	@Column(name="date_of_joining")
	private Date doj;
	
	@Column(name="dept_name",length=30,nullable=false)
	private String deptName;
	
	@Column(name="bonus",precision=6,scale=3)
	private BigDecimal bonus;
	
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(column = @Column(name="home_city"), name = "city"),
		@AttributeOverride(column = @Column(name="home_street"), name = "street"),
		@AttributeOverride(column = @Column(name="home_zipCode"), name = "zipCode"),
		@AttributeOverride(column = @Column(name="home_addressLine1"), name = "addressLine1")
		,@AttributeOverride(column = @Column(name="home_addressLine2"), name = "addressLine2")
	})
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(column = @Column(name="office_city"), name = "city"),
		@AttributeOverride(column = @Column(name="office_street"), name = "street"),
		@AttributeOverride(column = @Column(name="office_zipCode"), name = "zipCode"),
		@AttributeOverride(column = @Column(name="office_addressLine1"), name = "addressLine1")
		,@AttributeOverride(column = @Column(name="office_addressLine2"), name = "addressLine2")
	})
	private Address officeAddress;
	
	
	@Column(name="email",length=30,unique=true)
	private String email;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	public BigDecimal getBonus() {
		return bonus;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
}
