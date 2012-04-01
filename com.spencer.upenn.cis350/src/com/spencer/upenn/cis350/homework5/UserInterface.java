package com.spencer.upenn.cis350.homework5;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {

	public static final String DATAFILE = "WorldSeries.csv";
	
	public static void printIntroduction(){
		System.out.println("Welcome to the World Series database!");
		System.out.println("");
		System.out.println("Please make your selection:");
		System.out.println("1: Search for World Series info by year");
		System.out.println("2: Search for World Series info by team");
		System.out.println("3: Show all World Series for a range of years");
		System.out.println("4: Show all teams' World Series wins");
		System.out.println("Q: Quit");
		System.out.print("> ");
	}
	
	public static void searchByYear(Scanner in){
		System.out.print("Please enter the year: ");
		try {
			int year = in.nextInt();
			String yearData = showDataForYear(year);
			System.out.println(yearData);
		}
		catch (Exception e) { 
			System.out.println("That is not a valid year.");
		}
	}
	
	public static void searchByTeam(Scanner in){
		System.out.print("Please enter the team name: ");
		String team = in.nextLine();
		System.out.print("Do you want to see World Series the team has (W)on, (L)ost, or (A)ll? ");
		String which = in.next();
		String teamData = showDataForTeam(team, which);	
		System.out.println(teamData);
	}
	
	public static void searchByYearRange(Scanner in){
		int startYear, endYear;
		System.out.print("Please enter the starting year: ");
		try {
			startYear = in.nextInt();
		}
		catch (Exception e) { 
			System.out.println("That is not a valid year.");
			return;
		}
		System.out.print("Please enter the ending year: ");
		try {
			endYear = in.nextInt();
		}
		catch (Exception e) { 
			System.out.println("That is not a valid year.");
			return;
		}
		String yearData = showDataForRange(startYear, endYear);
		System.out.println(yearData);
	}
	
	public static void printSortedTeams(){
		String result = Sorter.sortByWinners();
		System.out.println(Sorter.sortByWinners());
	}
	
	public static void main(String[] args) {
		
		printIntroduction();
		String choice = null; // the thing that the user chooses to do
		
		do {
			
			Scanner in = new Scanner(System.in);
			
			choice = in.nextLine();
			
			try {
				int choiceNum = Integer.parseInt(choice);
				
				switch(choiceNum) {
				case 1: 
					searchByYear(in);
					break;
				case 2:
					searchByTeam(in);
					break;
				case 3:
					searchByYearRange(in);
					break;
				case 4:
					printSortedTeams();
					break;
				default:
					// they entered a number but not a valid one
					System.out.println("That is not a valid selection.");
				}
			}
			catch (NumberFormatException e) {
				// they didn't enter a number
				if (choice.equals("q") == false && choice.equals("Q") == false) {
					System.out.println("That is not a valid selection.");
				}
			}
			
		}
		while (!(choice.equalsIgnoreCase("q")));
		System.out.println("Good bye");
	}

	
	protected static String showDataForYear(int year) {
		DataStore ds = new DataStore(DATAFILE);
		
		// look through all the instances
		ArrayList<WorldSeriesInstance> list = ds.allInstances();
		for (WorldSeriesInstance wsi : list) {
			if (wsi.getYear() == year) {
				// found it!
				return "In " + year + " the " + wsi.getWinner() + " defeated the " + wsi.getLoser() + " by " + wsi.getScore();
			}
		}
		
		// if we made it here, we didn't find it
		return "No World Series was held in " + year;
		
	}

	protected static String showDataForRange(int start, int end) {
		// make sure we have valid data
		if (end < start) {
			return "Invalid year range";
		}
		
		DataStore ds = new DataStore(DATAFILE);
		
		StringBuffer result = new StringBuffer();
		
		// this is a counter of how many we've added to the buffer
		int x = 0;
		
		// look through all the instances
		ArrayList<WorldSeriesInstance> list = ds.allInstances();
		for (WorldSeriesInstance wsi : list) {
			if (wsi.getYear() >= start && wsi.getYear() <= end) {
				// found it!
				result.append("In " + wsi.getYear() + " the " + wsi.getWinner() + " defeated the " + wsi.getLoser() + " by " + wsi.getScore());
				result.append("\n");
				x++;
			}
		}
		
		// if we didn't see any results, return that
		if (x == 0) {
			return "No World Series held between " + start + " and " + end;
		}
		else {
			return result.toString();
		}
		
	}

	protected static String showDataForTeam(String team, String choice) {
		// load the data
		DataStore ds = new DataStore(DATAFILE);
		
		// to hold the result
		StringBuffer result = new StringBuffer();
		
		if (choice.equalsIgnoreCase("W")) {
			// keep track of the number of wins for the team
			int m = 0;
			
			// look through all the instances
			ArrayList<WorldSeriesInstance> list = ds.allInstances();
			for (WorldSeriesInstance wsi : list) {
				// convert to uppercase and use "contains" for partial matching
				if (wsi.getWinner().toUpperCase().contains(team.toUpperCase())) {
					// we found an instance when the team won
					result.append("In " + wsi.getYear() + " the " + wsi.getWinner() + " defeated the " + wsi.getLoser() + " by " + wsi.getScore());
					result.append("\n");
					m++;
				}
			}
			// if none found, print a message
			if (m == 0) {
				result.append("The " + team + " have not won any World Series");
				result.append("\n");
			}
			else {
				result.append("The " + team + " have won " + m + " World Series");
				result.append("\n");
			}
			
		}
		else if (choice.equalsIgnoreCase("L")) {
			// keep track of the number of losses for the team
			int m = 0;
			
			// look through all the instances
			ArrayList<WorldSeriesInstance> list = ds.allInstances();
			for (WorldSeriesInstance wsi : list) {
				// convert to uppercase and use "contains" for partial matching
				if (wsi.getLoser().toUpperCase().contains(team.toUpperCase())) {
					result.append("In " + wsi.getYear() + " the " + wsi.getLoser() + " lost to the " + wsi.getWinner() + " by " + wsi.getScore());
					result.append("\n");
					m++;
				}
			}
			// if none found, print a message
			if (m == 0) {
				result.append("The " + team + " have not lost any World Series");
				result.append("\n");
			}
			else {
				result.append("The " + team + " have lost " + m + " World Series");
				result.append("\n");
			}
			
		}
		else if (choice.equalsIgnoreCase("A")) {
			
			// keep track of the number of wins and losses for the team
			int a = 0, b = 0;
			
			// look through all the instances
			ArrayList<WorldSeriesInstance> list = ds.allInstances();
			for (WorldSeriesInstance wsi : list) {
				// convert to uppercase and use "contains" for partial matching
				if (wsi.getWinner().toUpperCase().contains(team.toUpperCase())) {
					// we found an instance when the team won
					result.append("In " + wsi.getYear() + " the " + wsi.getWinner() + " defeated the " + wsi.getLoser() + " by " + wsi.getScore());
					result.append("\n");
					a++;
				}
				else if (wsi.getLoser().toUpperCase().contains(team.toUpperCase())) {
					result.append("In " + wsi.getYear() + " the " + wsi.getLoser() + " lost to the " + wsi.getWinner() + " by " + wsi.getScore());
					result.append("\n");
					b++;
				}
			}
			// if none found, print a message
			if (a + b == 0) {
				result.append("The " + team + " have not played in any World Series");
				result.append("\n");
			}
			else {
				result.append("The " + team + " have won " + a + " World Series and lost " + b);
				result.append("\n");
			}
			
			
		}
		else {
			result.append("\"" + choice + "\" is not a valid entry.");
		}
		
		return result.toString();
		
	}
	
}
