package com.example.hr.domain;

import com.example.hr.domain.annotation.ValueObject;

@ValueObject
public class BirthYear {

	private final int value;

	private BirthYear(int value) {
		super();
		this.value = value;
	}
	
	public static BirthYear valueOf(int value) {
		if(value < 1950)
			throw new IllegalArgumentException("Year must be larger than 1950.");
		return new BirthYear(value);
	}

	@Override
	public String toString() {
		return "BirthYear [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
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
		BirthYear other = (BirthYear) obj;
		if (value != other.value)
			return false;
		return true;
	}

	public int getValue() {
		return value;
	}
	}
