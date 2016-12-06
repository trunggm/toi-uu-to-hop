package com.toiuutohop;

public class SumNumber {
	public Integer sum = 0;
	
	public SumNumber () {
		
	}
	
	public SumNumber (Integer a) {
		this.sum = a;
	}
	
	public SumNumber (Integer a, Integer b) {
		this.sum = a + b;
	}
	
	public Integer getSum() {
		return sum;
	}
}
