package com.example.hr.domain;

import com.example.hr.domain.annotation.EntityClass;

// Entity Class -> Identity, mutable
@EntityClass(identity= {"kimlikNo"})
public class Employee {

	private TcKimlikNo kimlikNo;
	private FullName fullname;
	private Money salary;
	private Iban iban;
	private BirthYear birthYear;
	private Department department;
	private JobType jobType;
	private Photo photo;
	
	public Employee(TcKimlikNo kimlikNo, FullName fullname, Money salary, BirthYear birthYear) {
		this.kimlikNo = kimlikNo;
		this.fullname = fullname;
		this.salary = salary;
		this.birthYear = birthYear;
	}

	public Employee(TcKimlikNo kimlikNo, FullName fullname, Money salary, Iban iban, BirthYear birthYear,
			Department department, JobType jobType, Photo photo) {
		this.kimlikNo = kimlikNo;
		this.fullname = fullname;
		this.salary = salary;
		this.iban = iban;
		this.birthYear = birthYear;
		this.department = department;
		this.jobType = jobType;
		this.photo = photo;
	}
	
	public Employee(Builder builder) {
		this.kimlikNo = builder.kimlikNo;
		this.fullname = builder.fullname;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.birthYear = builder.birthYear;
		this.department = builder.department;
		this.jobType = builder.jobType;
		this.photo = builder.photo;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
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

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	public TcKimlikNo getKimlikNo() {
		return kimlikNo;
	}

	@Override
	public String toString() {
		return "Employee [kimlikNo=" + kimlikNo + ", fullname=" + fullname + ", salary=" + salary + ", iban=" + iban
				+ ", birthYear=" + birthYear + ", department=" + department + ", jobType=" + jobType + "]";
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
		Employee other = (Employee) obj;
		if (kimlikNo == null) {
			if (other.kimlikNo != null)
				return false;
		} else if (!kimlikNo.equals(other.kimlikNo))
			return false;
		return true;
	}



	// Flow API 
	/*new Employee.Builder("12345678901")
		.fullname("Jack", "Bauer")
		.salary(10_000, FiatCurrency.TRY)
		.iban("TR1")
		.birthYear(1967)
		.department("SALES")
		.jobType("FULL_TIME")
		.build();*/
	public static class Builder {
		private TcKimlikNo kimlikNo;
		private FullName fullname;
		private Money salary;
		private Iban iban;
		private BirthYear birthYear;
		private Department department;
		private JobType jobType;
		private Photo photo;
		
		public Builder(TcKimlikNo kimlikNo) {
			this.kimlikNo = kimlikNo;
		}
		
		public Builder fullname(String first, String last) {
			this.fullname = FullName.of(first, last);
			return this;
		}
		
		public Builder fullname(String fullname) {
			var tokens = fullname.split(" ");
			if(tokens.length != 2) throw new IllegalArgumentException("Does not match: firstname lastname");
			this.fullname = FullName.of(tokens[0], tokens[1]);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.of(value, currency);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.valueOf(value);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.valueOf(value);
			return this;
		}

		public Builder photo(byte[] data) {
			this.photo = Photo.of(data);
			return this;
		}

		public Builder jobType(String value) {
			this.jobType = JobType.valueOf(value);
			return this;
		}

		public Builder department(String value) {
			this.department = Department.valueOf(value);
			return this;
		}
		
		public Employee build() {
			// validation
			// business rule
			return new Employee(this);
		}
		 
	}
	
}
