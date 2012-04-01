package com.spencer.upenn.cis350.homework5;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SorterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String result = new Sorter().winners();
		
		String expectedResult = "Anaheim Angels: 2002\n" +
				"Arizona Diamondbacks: 2001\n" +
				"Atlanta Braves: 1995\n" +
				"Baltimore Orioles: 1966, 1970, 1983\n" +
				"Boston Americans: 1903\n" +
				"Boston Braves: 1914\n" +
				"Boston Red Sox: 1912, 1915, 1916, 1918, 2004, 2007\n" +
				"Brooklyn Dodgers: 1955\n" +
				"Chicago Cubs: 1907, 1908\n" +
				"Chicago White Sox: 1906, 1917, 2005\n" +
				"Cincinnati Reds: 1919, 1940, 1975, 1976, 1990\n" +
				"Cleveland Indians: 1920, 1948\n" +
				"Detroit Tigers: 1935, 1945, 1968, 1984\n" +
				"Florida Marlins: 1997, 2003\n" +
				"Kansas City Royals: 1985\n" +
				"Los Angeles Dodgers: 1959, 1963, 1965, 1981, 1988\n" +
				"Milwaukee Braves: 1957\n" +
				"Minnesota Twins: 1987, 1991\n" +
				"New York Giants: 1905, 1921, 1922, 1933, 1954\n" +
				"New York Mets: 1969, 1986\n" +
				"New York Yankees: 1923, 1927, 1928, 1932, 1936, 1937, 1938, 1939, 1941, 1943, 1947, 1949, 1950, 1951, 1952, 1953, 1956, 1958, 1961, 1962, 1977, 1978, 1996, 1998, 1999, 2000, 2009\n" +
				"Oakland Athletics: 1972, 1973, 1974, 1989\n" +
				"Philadelphia Athletics: 1910, 1911, 1913, 1929, 1930\n" +
				"Philadelphia Phillies: 1980, 2008\n" +
				"Pittsburgh Pirates: 1909, 1925, 1960, 1971, 1979\n" +
				"San Francisco Giants: 2010\n" +
				"St. Louis Cardinals: 1926, 1931, 1934, 1942, 1944, 1946, 1964, 1967, 1982, 2006, 2011\n" +
				"Toronto Blue Jays: 1992, 1993\n" +
				"Washington Senators: 1924\n";
		
		assertEquals(expectedResult, result);
	}

}
