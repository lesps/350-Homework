package com.spencer.upenn.cis350.homework2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

public class CalculateDistanceTraveledTest {

	GPXtrk std = null;
	ArrayList<GPXtrkseg> segs = null;
	ArrayList<GPXtrkpt> ptsTest = null;
	
	@Before
	public void setUp() throws Exception {
		//Point array of 20 dist
		ArrayList<GPXtrkpt> pts1 = new ArrayList<GPXtrkpt>();
		pts1.add(new GPXtrkpt(90, 90, new Date()));
		pts1.add(new GPXtrkpt(90, 100, new Date()));
		pts1.add(new GPXtrkpt(80, 100, new Date()));
		
		//Point array of 40 dist
		ArrayList<GPXtrkpt> pts2 = new ArrayList<GPXtrkpt>();
		pts2.add(new GPXtrkpt(-80, -100, new Date()));
		pts2.add(new GPXtrkpt(-80, -80, new Date()));
		pts2.add(new GPXtrkpt(-60, -80, new Date()));
		
		//Point array of 5 dist (test the square root functionality)
		ArrayList<GPXtrkpt> pts3 = new ArrayList<GPXtrkpt>();
		pts3.add(new GPXtrkpt(0, 0, new Date()));
		pts3.add(new GPXtrkpt(3, 4, new Date()));
		
		segs = new ArrayList<GPXtrkseg>();
		segs.add(new GPXtrkseg(pts1));
		segs.add(new GPXtrkseg(pts2));
		segs.add(new GPXtrkseg(pts3));
		
		std = new GPXtrk("Track", segs);
		
		ArrayList<GPXtrkpt> ptsTest = new ArrayList<GPXtrkpt>();
		ptsTest.add(new GPXtrkpt(90, 100, new Date()));
		ptsTest.add(new GPXtrkpt(100, 100, new Date()));
		ptsTest.add(new GPXtrkpt(130, 140, new Date()));
	}

	@After
	public void tearDown() throws Exception {
		std=null;
		segs = null;
		ptsTest = null;
	}

	@Test
	//Be sure negative one is returned when GPXtrk is null 
	public void testNullGPXtrkReturnsNegativeOne() {
		GPXtrk nulltrk = null;
		assertTrue("calculateDistanceTraveled should return -1 for a null trk", 
				GPXcalculator.calculateDistanceTraveled(nulltrk) == -1);
	}
	
	@Test
	//Test functionality against hard-coded points
	public void testFunctionalityStandard(){
		assertTrue("Distance should be 65 for standard trk",
				GPXcalculator.calculateDistanceTraveled(std)==65.0);
	}
	
	@Test
	//Test functionality works in a very basic case
	public void testFunctionalityBasic(){
		ArrayList<GPXtrkpt> pts = new ArrayList<GPXtrkpt>();
		pts.add(new GPXtrkpt(90, 90, new Date()));
		pts.add(new GPXtrkpt(90, 100, new Date()));
		pts.add(new GPXtrkpt(80, 100, new Date()));
		
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(new GPXtrkseg(pts));
		
		GPXtrk basic = new GPXtrk("Basic Track", segs);
		assertTrue("Distance for basic test should yield 20",
				GPXcalculator.calculateDistanceTraveled(basic)==20);
	}
	
	
	@Test
	//Be sure an empty GPXtrk returns -1
	public void testEmptyReturnsNegativeOne(){
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		GPXtrk emptytrk = new GPXtrk("Empty track", segs);
		assertTrue("calculateDistanceTraveled should return -1 for an empty trk",
				GPXcalculator.calculateDistanceTraveled(emptytrk) == -1);
	}
	
	@Test
	//Be sure a null segment does not affect distance - base case
	public void testNullSegmentDoesNotAffectDistanceBasic(){
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(null);
		GPXtrk track = new GPXtrk("Track", segs);
		assertTrue("Track seg with only a null seg should return 0", 
				GPXcalculator.calculateDistanceTraveled(track) == 0);
	}
	
	@Test
	//Be sure null segment does not affect distance - against hard coded points
	public void testNullSegmentDoesNotAffectDistanceStandard(){
		segs.add(null);
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of an empty seg should still have dist 65",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//Be sure null segment does not affect distance - against randomly generated values
	//All 'advanced' tests yield true if distance is returned as one stagnant value (e.g. 0 all the time)
	//These should only be considered after basic and standard tests pass
	public void testNullSegmentDoesNotAffectDistanceAdvanced(){
		ArrayList<GPXtrkseg> segs = createGPXtrkseg(5);
		GPXtrk track = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(track);
		segs.add(null);
		track=new GPXtrk("Track", segs);
		assertTrue("Addition of a null element should not affect total distance traveled",
				distance==GPXcalculator.calculateDistanceTraveled(track));
	}
	
	@Test
	//Empty segment should not affect distance - basic case
	public void testEmptyGPXtrksegReturnsZeroBasic(){
		GPXtrkseg seg = new GPXtrkseg(new ArrayList<GPXtrkpt>());
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(seg);
		GPXtrk track = new GPXtrk("Track", segs);
		assertTrue("Track seg with only an empty seg should return 0", 
				GPXcalculator.calculateDistanceTraveled(track) == 0);
	}
	
	@Test
	//Empty segment should not affect distance - against hard coded values
	public void testEmptyGPXtrksegReturnsZeroStandard(){
		segs.add(new GPXtrkseg(new ArrayList<GPXtrkpt>()));
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of an empty seg should still have dist 65",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//Empty segment should not affect distance - against randomly generated values
	public void testEmptyGPXtrksegReturnsZeroAdvanced(){
		ArrayList<GPXtrkseg> segs = createGPXtrkseg(5);
		GPXtrk track = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(track);
		segs.add(new GPXtrkseg(new ArrayList<GPXtrkpt>()));
		track=new GPXtrk("Track", segs);
		assertTrue("Addition of an empty seg should not affect total distance traveled",
				distance==GPXcalculator.calculateDistanceTraveled(track));
	}
	
	@Test
	//Segment with single segment should return zero - basic case
	public void testGPXtrksegWithSinglePointReturnsZeroDistanceBasic(){
		GPXtrkseg seg = new GPXtrkseg(createGPXtrkpt(1));
		ArrayList<GPXtrkseg> segs = new ArrayList<GPXtrkseg>();
		segs.add(seg);
		GPXtrk track = new GPXtrk("Track", segs);
		assertTrue("Track seg with only a single point should return 0", 
				GPXcalculator.calculateDistanceTraveled(track) == 0);
	}
	
	@Test
	//Segment with single segment should return zero - against hard coded values
	public void testGPXtrksegWithSinglePointReturnsZeroDistanceStandard(){
		ArrayList<GPXtrkpt> pts = new ArrayList<GPXtrkpt>();
		pts.add(new GPXtrkpt(50, 50, new Date()));
		segs.add(new GPXtrkseg(pts));
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of a seg with one point should still have dist 65",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//Segment with single segment should return zero - against random values
	public void testGPXtrksegWithSinglePointReturnsZeroDistanceAdvanced(){
		ArrayList<GPXtrkseg> segs = createGPXtrkseg(5);
		GPXtrk track = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(track);
		GPXtrkseg seg = new GPXtrkseg(createGPXtrkpt(1));
		segs.add(seg);
		track=new GPXtrk("Track", segs);
		assertTrue("Addition of an empty seg should not affect total distance traveled",
				distance==GPXcalculator.calculateDistanceTraveled(track));
	}
	
	@Test
	//Null track point should cause segment to return zero distance - basic case
	public void testNullGPXtrkptReturnsZeroBasic(){
		ptsTest = new ArrayList<GPXtrkpt>();
		ptsTest.add(null);
		ArrayList<GPXtrkseg> nullSegs = new ArrayList<GPXtrkseg>();
		nullSegs.add(new GPXtrkseg(ptsTest));
		GPXtrk trk = new GPXtrk("TestTrack",nullSegs);
		assertTrue("A track seg with a null point should return zero",
				GPXcalculator.calculateDistanceTraveled(trk)==0);
	}
	
	@Test
	//Null track point should cause segment to return zero distance - against hard coded values
	public void testNullGPXtrkptReturnsZeroStandard(){
		ptsTest = new ArrayList<GPXtrkpt>();
		ptsTest.add(null);
		segs.add(new GPXtrkseg(ptsTest));
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of a GPXtrkseg with null point should return zero",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//Null track point should cause segment to return zero distance - against random values
	public void testNullGPXtrkptReturnsZeroAdvanced(){
		ptsTest = new ArrayList<GPXtrkpt>();
		ptsTest.add(null);
		segs = createGPXtrkseg(5);
		GPXtrk trk = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(trk);
		segs.add(new GPXtrkseg(ptsTest));
		trk = new GPXtrk("Track", segs);
		assertTrue("Distance should remain unchanged after addition of GPX trk" +
				" seg containing a null point", 
				distance==GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	@Test
	//A point with a lattitude > 90 should cause segment to return zero - basic case
	public void testInvalidGPXtrkptLattitudePosReturnsZeroBasic(){
		ptsTest.add(new GPXtrkpt(91, 150, new Date()));
		ArrayList<GPXtrkseg> single = new ArrayList<GPXtrkseg>();
		single.add(new GPXtrkseg(ptsTest));
		GPXtrk trk = new GPXtrk("Track", single);
		assertTrue("Segment with invalid positive lattitude should return zero", 
				GPXcalculator.calculateDistanceTraveled(trk)==0);
	}
	
	@Test
	//A point with a lattitude > 90 should cause segment to return zero - against hard coded values
	public void testInvalidGPXtrkptLattitudePosReturnZeroStandard(){
		ptsTest.add(new GPXtrkpt(91,150,new Date()));
		segs.add(new GPXtrkseg(ptsTest));
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of a GPXtrkseg with an invalid positive lattitude should not affect distance",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//A point with a lattitude > 90 should cause segment to return zero - against random values
	public void testInvalidGPXtrkptLattitudePosReturnZeroAdvanced(){
		ptsTest.add(new GPXtrkpt(91,150, new Date()));
		segs = createGPXtrkseg(5);
		GPXtrk trk = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(trk);
		segs.add(new GPXtrkseg(ptsTest));
		trk = new GPXtrk("Track", segs);
		assertTrue("The addition of a seg with an invalid positive lattitude should not change the distance", 
				distance==GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	@Test
	//A point with lattitude < -90 should cause segment to return zero - basic case
	public void testInvalidGPXtrkptLattitudeNegReturnZeroBasic(){
		ptsTest.add(new GPXtrkpt(-91, 150, new Date()));
		ArrayList<GPXtrkseg> single = new ArrayList<GPXtrkseg>();
		single.add(new GPXtrkseg(ptsTest));
		GPXtrk trk = new GPXtrk("Track", single);
		assertTrue("Segment with invalid negative lattitude should return zero", 
				GPXcalculator.calculateDistanceTraveled(trk)==0);
	}
	
	@Test
	//A point with lattitude < -90 should cause segment to return zero - against hard coded values
	public void testInvalidGPXtrkptLattitudeNegReturnZeroStandard(){
		ptsTest.add(new GPXtrkpt(-91,150,new Date()));
		segs.add(new GPXtrkseg(ptsTest));
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of a GPXtrkseg with invalid negative lattitude should not affect distance",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//A point with lattitude < -90 should cause segment to return zero - against random values
	public void testInvalidGPXtrkptLattitudeNegReturnZeroAdvanced(){
		ptsTest.add(new GPXtrkpt(-91,150, new Date()));
		segs = createGPXtrkseg(5);
		GPXtrk trk = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(trk);
		segs.add(new GPXtrkseg(ptsTest));
		trk = new GPXtrk("Track", segs);
		assertTrue("The addition of a seg with an invalid point should not change the distance", 
				distance==GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	@Test
	//A point with longitude > 180 should cause segment to return zero - basic case
	public void testInvalidGPXtrkptLongitudePosReturnZeroBasic(){
		ptsTest.add(new GPXtrkpt(80, 181, new Date()));
		ArrayList<GPXtrkseg> single = new ArrayList<GPXtrkseg>();
		single.add(new GPXtrkseg(ptsTest));
		GPXtrk trk = new GPXtrk("Track", single);
		assertTrue("Segment with invalid positive longitude should return zero", 
				GPXcalculator.calculateDistanceTraveled(trk)==0);
	}
	
	@Test
	//A point with longitude > 180 should cause segment to return zero - against hard coded values
	public void testInvalidGPXtrkptLongitudePosReturnZeroStandard(){
		ptsTest.add(new GPXtrkpt(80,181,new Date()));
		segs.add(new GPXtrkseg(ptsTest));
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of a GPXtrkseg with invalid positive longitude should not affect distance",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//A point with longitude > 180 should cause segment to return zero - against random values
	public void testInvalidGPXtrkptLongitudePosReturnZeroAdvanced(){
		ptsTest.add(new GPXtrkpt(80,181, new Date()));
		segs = createGPXtrkseg(5);
		GPXtrk trk = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(trk);
		segs.add(new GPXtrkseg(ptsTest));
		trk = new GPXtrk("Track", segs);
		assertTrue("The addition of a seg with an invalid point should not change the distance", 
				distance==GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	@Test
	//A point with longitude < -180 should cause segment to return zero - basic case
	public void testInvalidGPXtrkptLongitudeNegReturnZeroBasic(){
		ptsTest.add(new GPXtrkpt(80, -181, new Date()));
		ArrayList<GPXtrkseg> single = new ArrayList<GPXtrkseg>();
		single.add(new GPXtrkseg(ptsTest));
		GPXtrk trk = new GPXtrk("Track", single);
		assertTrue("Segment with invalid negative longitude should return zero", 
				GPXcalculator.calculateDistanceTraveled(trk)==0);
	}
	
	@Test
	//A point with longitude < -180 should cause segment to return zero - against hard coded values
	public void testInvalidGPXtrkptLongitudeNegReturnZeroStandard(){
		ptsTest.add(new GPXtrkpt(80,-181,new Date()));
		segs.add(new GPXtrkseg(ptsTest));
		std = new GPXtrk("Track", segs);
		assertTrue("The addition of a GPXtrkseg with invalid negative longitude should not affect distance",
				GPXcalculator.calculateDistanceTraveled(std)==65);
	}
	
	@Test
	//A point with longitude < -180 should cause segment to return zero - against random values
	public void testInvalidGPXtrkptLongitudeNegReturnZeroAdvanced(){
		ptsTest.add(new GPXtrkpt(80,-181, new Date()));
		segs = createGPXtrkseg(5);
		GPXtrk trk = new GPXtrk("Track", segs);
		double distance = GPXcalculator.calculateDistanceTraveled(trk);
		segs.add(new GPXtrkseg(ptsTest));
		trk = new GPXtrk("Track", segs);
		assertTrue("The addition of a seg with an invalid point should not change the distance", 
				distance==GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	/**
	 * Return an array list of track points of size n
	 * @param n The desired size of the arraylist
	 * @return The array list generated
	 */
	public ArrayList<GPXtrkseg> createGPXtrkseg(int n){
		ArrayList<GPXtrkseg> ret = new ArrayList<GPXtrkseg>();
		
		for(int i = 0; i < n; ++i)
			ret.add(new GPXtrkseg(createGPXtrkpt(n)));
		
		return ret;
	}
	
	
	/**
	 * Return an array list of track points of size n with max lat at abs(lat)<=90
	 * and max longitude at abs(longitude) <= 180
	 * @param n The desired size of the arraylist
	 * @return The array list generated
	 */
	public ArrayList<GPXtrkpt> createGPXtrkpt(int n){
		
		ArrayList<GPXtrkpt> pts = new ArrayList<GPXtrkpt>();
		for(int i = 0; i < n; ++i){
			double lattitude = genRandDoub(90);
			double longitude = genRandDoub(180);
			pts.add(new GPXtrkpt(lattitude, longitude, new Date()));
		}
		return pts;
	}
	
	/**
	 * Generate a random double from -n to n
	 * @param n Integer bounding double
	 * @return The double generated
	 */
	public double genRandDoub(int n){
		Random rand = new Random();
		double lat = rand.nextDouble()*n;
		return rand.nextBoolean() ? lat : lat*-1;		
	}

}
