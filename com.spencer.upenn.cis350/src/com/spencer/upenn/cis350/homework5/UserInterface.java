package com.spencer.upenn.cis350.homework5;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {

	public static final String DATAFILE = "WorldSeries.csv";
	public static DataStore ds;
	
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
			String yearData = ds.showDataForYear(year);
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
		String teamData = ds.showDataForTeam(team, which);	
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
		String yearData = ds.showDataForRange(startYear, endYear);
		System.out.println(yearData);
	}
	
	public static void printSortedTeams(){
		String result = ds.sortByWinners();
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		
		printIntroduction();
		String choice = null; // the thing that the user chooses to do
		ds = new DataStore(DATAFILE);
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

}
