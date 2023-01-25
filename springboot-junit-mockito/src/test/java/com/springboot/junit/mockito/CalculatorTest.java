package com.springboot.junit.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
	
	Calculator calculator;
	
	
	@BeforeEach
	void setUp() {
		calculator = new Calculator();
	}
	
	@Test
	void testMultiply() {
		assertEquals(20, calculator.multiply(4, 5));
		assertEquals(25, calculator.multiply(5, 5));
	}
	
	@Test
	void testDivide() {
		assertEquals(2, calculator.divide(4, 2));
		assertEquals(0, calculator.divide(5, 10));
	}

}
