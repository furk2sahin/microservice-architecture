package com.example.hr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobType;


@Document(collection = "employees")
public class EmployeeDocument {
	@Id
	private String kimlikNo;
	private String fullname;
	private double salary;
	private FiatCurrency currency;
	private String iban;
	private int birthYear;
	private Department department;
	private JobType jobType;
	private String photo;
	
	
	
	public EmployeeDocument() {
	}
	
	public EmployeeDocument(String kimlikNo, String fullname, double salary, FiatCurrency currency, String iban,
			int birthYear, Department department, JobType jobType, String photo) {
		super();
		this.kimlikNo = kimlikNo;
		this.fullname = fullname;
		this.salary = salary;
		this.currency = currency;
		this.iban = iban;
		this.birthYear = birthYear;
		this.department = department;
		this.jobType = jobType;
		this.photo = photo;
	}
	public String getKimlikNo() {
		return kimlikNo;
	}
	public void setKimlikNo(String kimlikNo) {
		this.kimlikNo = kimlikNo;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public FiatCurrency getCurrency() {
		return currency;
	}
	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kimlikNo == null) ? 0 : kimlikNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDocument other = (EmployeeDocument) obj;
		if (kimlikNo == null) {
			if (other.kimlikNo != null)
				return false;
		} else if (!kimlikNo.equals(other.kimlikNo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeDocument [kimlikNo=" + kimlikNo + ", fullname=" + fullname + ", salary=" + salary
				+ ", currency=" + currency + ", iban=" + iban + ", birthYear=" + birthYear + ", jobType=" + jobType
				+ "]";
	}
}
