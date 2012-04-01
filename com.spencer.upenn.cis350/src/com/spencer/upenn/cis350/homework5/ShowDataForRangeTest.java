package com.spencer.upenn.cis350.homework5;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ShowDataForRangeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test2004to2007() {
		String result = UserInterface.showDataForRange(2004, 2007);
		
		String expectedResult = "In 2004 the Boston Red Sox defeated the St. Louis Cardinals by 4-0\n" +
				"In 2005 the Chicago White Sox defeated the Houston Astros by 4-0\n" +
				"In 2006 the St. Louis Cardinals defeated the Detroit Tigers by 4-1\n" +
				"In 2007 the Boston Red Sox defeated the Colorado Rockies by 4-0\n";
		
		assertEquals(expectedResult, result);
	}

	@Test
	public void test1990to1999() {
		String result = UserInterface.showDataForRange(1990, 1999);
		
		String expectedResult = "In 1990 the Cincinnati Reds defeated the Oakland Athletics by 4-0\n" +
				"In 1991 the Minnesota Twins defeated the Atlanta Braves by 4-3\n" +
				"In 1992 the Toronto Blue Jays defeated the Atlanta Braves by 4-2\n" +
				"In 1993 the Toronto Blue Jays defeated the Philadelphia Phillies by 4-2\n" +
				"In 1995 the Atlanta Braves defeated the Cleveland Indians by 4-2\n" +
				"In 1996 the New York Yankees defeated the Atlanta Braves by 4-2\n" +
				"In 1997 the Florida Marlins defeated the Cleveland Indians by 4-3\n" +
				"In 1998 the New York Yankees defeated the San Diego Padres by 4-0\n" +
				"In 1999 the New York Yankees defeated the Atlanta Braves by 4-0\n";
		
		assertEquals(expectedResult, result);
	}

	@Test
	public void testBadRange() {
		String result = UserInterface.showDataForRange(2000, 1999);
		
		String expectedResult = "Invalid year range";
		
		assertEquals(expectedResult, result);
	}

	@Test
	public void testEmptyRange() {
		String result = UserInterface.showDataForRange(1700, 1800);
		
		String expectedResult = "No World Series held between 1700 and 1800";
		
		assertEquals(expectedResult, result);
	}

}
