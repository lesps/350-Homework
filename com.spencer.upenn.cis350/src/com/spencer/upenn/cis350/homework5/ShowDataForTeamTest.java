package com.spencer.upenn.cis350.homework5;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ShowDataForTeamTest {

	private DataStore ds;
	@Before
	public void setUp() throws Exception {
		ds = new DataStore(UserInterface.DATAFILE);
	}

	@Test
	public void testRedSoxWins() {
		String result = ds.showDataForTeam("Red Sox", "W");

		String expectedResult = "In 1912 the Boston Red Sox defeated the New York Giants by 4-3\n" +
			"In 1915 the Boston Red Sox defeated the Philadelphia Phillies by 4-1\n" +
			"In 1916 the Boston Red Sox defeated the Brooklyn Robins by 4-1\n" +
			"In 1918 the Boston Red Sox defeated the Chicago Cubs by 4-2\n" +
			"In 2004 the Boston Red Sox defeated the St. Louis Cardinals by 4-0\n" +
			"In 2007 the Boston Red Sox defeated the Colorado Rockies by 4-0\n" +
			"The Red Sox have won 6 World Series\n";
		
		assertEquals(expectedResult, result);
		
	}
	
	@Test
	public void testYankeesLosses() {
		String result = ds.showDataForTeam("YANKEES", "l");

		String expectedResult = "In 1921 the New York Yankees lost to the New York Giants by 5-3\n" +
				"In 1922 the New York Yankees lost to the New York Giants by 4-0\n" + 
				"In 1926 the New York Yankees lost to the St. Louis Cardinals by 4-3\n" +
				"In 1942 the New York Yankees lost to the St. Louis Cardinals by 4-1\n" + 
				"In 1955 the New York Yankees lost to the Brooklyn Dodgers by 4-3\n" + 
				"In 1957 the New York Yankees lost to the Milwaukee Braves by 4-3\n" + 
				"In 1960 the New York Yankees lost to the Pittsburgh Pirates by 4-3\n" + 
				"In 1963 the New York Yankees lost to the Los Angeles Dodgers by 4-0\n" + 
				"In 1964 the New York Yankees lost to the St. Louis Cardinals by 4-3\n" + 
				"In 1976 the New York Yankees lost to the Cincinnati Reds by 4-0\n" + 
				"In 1981 the New York Yankees lost to the Los Angeles Dodgers by 4-2\n" + 
				"In 2001 the New York Yankees lost to the Arizona Diamondbacks by 4-3\n" + 
				"In 2003 the New York Yankees lost to the Florida Marlins by 4-2\n" + 
				"The YANKEES have lost 13 World Series\n";
		
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testPhilliesAll() {
		String result = ds.showDataForTeam("Philadelphia Phillies", "A");
	
		String expectedResult = "In 1915 the Philadelphia Phillies lost to the Boston Red Sox by 4-1\n" +
				"In 1950 the Philadelphia Phillies lost to the New York Yankees by 4-0\n" +
				"In 1980 the Philadelphia Phillies defeated the Kansas City Royals by 4-2\n" +
				"In 1983 the Philadelphia Phillies lost to the Baltimore Orioles by 4-1\n" +
				"In 1993 the Philadelphia Phillies lost to the Toronto Blue Jays by 4-2\n" +
				"In 2008 the Philadelphia Phillies defeated the Tampa Bay Rays by 4-1\n" +
				"In 2009 the Philadelphia Phillies lost to the New York Yankees by 4-2\n" +
				"The Philadelphia Phillies have won 2 World Series and lost 5\n";

		assertEquals(expectedResult, result);
				
	}

	@Test
	public void testNoWorldSeries() {
		String result = ds.showDataForTeam("Seattle Mariners", "A");

		String expectedResult = "The Seattle Mariners have not played in any World Series\n";
		
		assertEquals(expectedResult, result);
	}

	@Test
	public void testBadChoice() {
		String result = ds.showDataForTeam("Yankees", "K");

		String expectedResult = "\"K\" is not a valid entry.";
		
		assertEquals(expectedResult, result);
	}
}
