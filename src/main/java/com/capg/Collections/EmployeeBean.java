package com.capg.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmployeeBean {
	
	private int empNo;
	private String empName;
	private double empSal;
	private List<String> empListProj;
	private Set<String> empSetProj;
	private Map<String, String> empMapProj;
	
	public EmployeeBean() {
		super();
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getEmpSal() {
		return empSal;
	}

	public void setEmpSal(double empSal) {
		this.empSal = empSal;
	}

	public List<String> getEmpListProj() {
		return empListProj;
	}

	public void setEmpListProj(List<String> empListProj) {
		this.empListProj = empListProj;
	}

	public Set<String> getEmpSetProj() {
		return empSetProj;
	}

	public void setEmpSetProj(Set<String> empSetProj) {
		this.empSetProj = empSetProj;
	}

	public Map<String, String> getEmpMapProj() {
		return empMapProj;
	}

	public void setEmpMapProj(Map<String, String> empMapProj) {
		this.empMapProj = empMapProj;
	}

	@Override
	public String toString() {
		return "EmployeeBean [empNo=" + empNo + ", empName=" + empName + ", empSal=" + empSal + ", empListProj="
				+ empListProj + ", empSetProj=" + empSetProj + ", empMapProj=" + empMapProj + "]";
	}

}
