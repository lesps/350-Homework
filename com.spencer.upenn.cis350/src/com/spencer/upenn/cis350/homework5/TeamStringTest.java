package com.spencer.upenn.cis350.homework5;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TeamStringTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testTeamToStringNoYear(){
		Team test = new Team("Jerseys");
		assertEquals("Jerseys: \n", test.toString());
	}
	
	@Test
	public void testTeamToString(){
		Team test = new Team("Jerseys");
		test.addWin(1988);
		assertEquals("Jerseys: 1988\n", test.toString());
	}
	
	@Test
	public void testTeamToStringMultipleYears(){
		Team test = new Team("Jerseys");
		test.addWin(1988);
		test.addWin(1999);
		test.addWin(2001);
		test.addWin(1978);
		assertEquals("Jerseys: 1978, 1988, 1999, 2001\n", test.toString());
	}
	
	@Test
	public void testTwoEqualTeamsEqual(){
		Team test1 = new Team("Jer");
		Team test2 = new Team("Jer");
		assertEquals(test1, test2);
	}

}
