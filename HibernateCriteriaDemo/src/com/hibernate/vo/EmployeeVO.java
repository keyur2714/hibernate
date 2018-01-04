package com.hibernate.vo;

import java.math.BigDecimal;

public class EmployeeVO {

	private Double avgSalary;
	private Double totalSal;
	private BigDecimal totalBonus;
	public Double getAvgSalary() {
		return avgSalary;
	}
	public void setAvgSalary(Double avgSalary) {
		this.avgSalary = avgSalary;
	}
	public Double getTotalSal() {
		return totalSal;
	}
	public void setTotalSal(Double totalSal) {
		this.totalSal = totalSal;
	}
	public BigDecimal getTotalBonus() {
		return totalBonus;
	}
	public void setTotalBonus(BigDecimal totalBonus) {
		this.totalBonus = totalBonus;
	}
}
