package com.example.hr.domain;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import com.example.hr.domain.annotation.ValueObject;

// Value Object -> no identity, immutable
@ValueObject
public class TcKimlikNo {
	
	private final String value;
	private static final Map<String, TcKimlikNo> identityCache = new WeakHashMap<>();
	
	public TcKimlikNo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static TcKimlikNo valueOf(String value) {
		// validation
		if(isValid(value)) {
			throw new IllegalArgumentException("This is not a valid identity no!");
		}
		var tcKimlikNo = identityCache.get(value);
		if(Objects.nonNull(tcKimlikNo)) {
			return tcKimlikNo;
		}
		tcKimlikNo = new TcKimlikNo(value);
		identityCache.put(value, tcKimlikNo);
		// object pooling -> caching -> immutable sýnýflar için yapýlabilir
		return new TcKimlikNo(value);
	}

	private static boolean isValid(String value) {
		if(value == null) {
			return false;
		}
		if(value.matches("^\\d{11}$")) {
			return false;
		}
		
		int[] digits = new int[11];
		for(int i = 0; i < digits.length; i++) {
			digits[i] = value.charAt(i)-'0';
		}
		
		int x = digits[0];
		int y = digits[1];
		
		for(int i = 1; i < 5; i++) {
			x += digits[2 * i];
		}
		
		for(int i = 2; i <= 4; i++) {
			y += digits[2 * i - 1];
		}
		
		int c1 = 7 * x - y;
		
		if(c1 % 10 != digits[9]) {
			return false;
		}
		
		int c2 = 0;
		
		for(int i = 0; i < 10; i++) {
			c2 += digits[i];
		}
		
		if(c2 % 10 != digits[10]) {
			return false;
		}
		
		return true;
	}
}
