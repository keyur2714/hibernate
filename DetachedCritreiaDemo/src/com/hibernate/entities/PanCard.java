package com.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pancard")
public class PanCard {

	@Id
	@GeneratedValue
	@Column(name="pancard_id")
	private int pancardId;
	
	@Column(name="pancard_no",unique=true)
	private String panNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DOB")
	private Date dob;
	
	@OneToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public int getPancardId() {
		return pancardId;
	}
	public void setPancardId(int pancardId) {
		this.pancardId = pancardId;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
