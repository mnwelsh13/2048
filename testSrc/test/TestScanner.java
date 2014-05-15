package test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class TestScanner {

	@Test
	public void test() {
		System.out.println("INPUT :");
		Scanner s = new Scanner(System.in);
		
		assertEquals("U", s.next().toUpperCase());
	}

}
