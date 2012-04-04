package com.spencer.upenn.cis350.homework5;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class DataStore {
	
	private final String _fileName;
	private ArrayList<WorldSeriesInstance> _list;
		
	public DataStore(String fileName) {
		_fileName = fileName;
		_list = new ArrayList<WorldSeriesInstance>();
		readDataFile();
	}
	
	public ArrayList<WorldSeriesInstance> allInstances() { return _list; }
	
	protected void readDataFile() {
		
		try {
			Scanner in = new Scanner(new File(_fileName));
			// since it's a comma-separated file
			in.useDelimiter(",");
			
			// read each line of the file one at a time
			while (in.hasNext()) {
				int y = in.nextInt();
				String w = in.next();
				String s = in.next();
				String l = in.nextLine();
				// the loser still has the leading comma attached, so get rid of it
				l = l.substring(1, l.length());
				//System.out.println(year + ": " + winner + " beat " + loser + " by " + score);
				
				// create a WorldSeriesInstance
				WorldSeriesInstance wsi = new WorldSeriesInstance(y, w, l, s);
				
				// add it to the ArrayList
				_list.add(wsi);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	public String sortByWinners() {
		
		ArrayList<WorldSeriesInstance> instances = _list;
		
		//Keep an ordered set of teams, sorted by natural ordering.
		TreeSet<Team> sortedTeams = new TreeSet<Team>();
		
		for(int i = 0; i < instances.size(); ++i){
			WorldSeriesInstance currentInstance = instances.get(i);
			Team currentTeam = new Team(currentInstance.getWinner());
			int year = currentInstance.getYear();
			if(sortedTeams.contains(currentTeam))
				sortedTeams.ceiling(currentTeam).addWin(year);
			else
				sortedTeams.add(new Team(currentInstance.getWinner(), year));
		}
		
		StringBuffer ret = new StringBuffer("");
		while(!sortedTeams.isEmpty())
			ret.append(sortedTeams.pollFirst().toString());
		
		return ret.toString();
	}
	
	protected String showDataForYear(int year) {
		// look through all the instances
		for (WorldSeriesInstance wsi : _list) {
			if (wsi.getYear() == year) {
				// found it!
				return wsi.toString();
			}
		}
		
		// if we made it here, we didn't find it
		return "No World Series was held in " + year;
		
	}
	
	protected String showDataForRange(int start, int end) {
		// make sure we have valid data
		if (end < start) {
			return "Invalid year range";
		}
		
		StringBuffer result = new StringBuffer();
		
		// this is a counter of how many we've added to the buffer
		int x = 0;
		
		// look through all the instances
		for (WorldSeriesInstance wsi : _list) {
			if (wsi.getYear() >= start && wsi.getYear() <= end) {
				// found it!
				result.append(wsi);
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
	
	protected String showDataForTeam(String team, String choice) {
		
		// to hold the result
		StringBuffer result = new StringBuffer();
		
		if (choice.equalsIgnoreCase("W")) {
			// keep track of the number of wins for the team
			int m = 0;
			
			// look through all the instances
			for (WorldSeriesInstance wsi : _list) {
				// convert to uppercase and use "contains" for partial matching
				if (wsi.getWinner().toUpperCase().contains(team.toUpperCase())) {
					// we found an instance when the team won
					result.append(wsi);
					result.append("\n");
					m++;
				}
			}
			// if none found, print a message
			if (m == 0) {
				result.append("The " + team + " have not won any World Series\n");
			}
			else {
				result.append("The " + team + " have won " + m + " World Series\n");
			}
			
		}
		else if (choice.equalsIgnoreCase("L")) {
			// keep track of the number of losses for the team
			int m = 0;
			
			// look through all the instances
			for (WorldSeriesInstance wsi : _list) {
				// convert to uppercase and use "contains" for partial matching
				if (wsi.getLoser().toUpperCase().contains(team.toUpperCase())) {
					result.append("In " + wsi.getYear() + " the " + wsi.getLoser() 
							+ " lost to the " + wsi.getWinner() + " by " + 
							wsi.getScore()+"\n");
					m++;
				}
			}
			// if none found, print a message
			if (m == 0) {
				result.append("The " + team + " have not lost any World Series\n");
			}
			else {
				result.append("The " + team + " have lost " + m + " World Series\n");
			}
			
		}
		else if (choice.equalsIgnoreCase("A")) {
			
			// keep track of the number of wins and losses for the team
			int a = 0, b = 0;
			
			// look through all the instances
			for (WorldSeriesInstance wsi : _list) {
				// convert to uppercase and use "contains" for partial matching
				if (wsi.getWinner().toUpperCase().contains(team.toUpperCase())) {
					// we found an instance when the team won
					result.append(wsi);
					result.append("\n");
					a++;
				}
				else if (wsi.getLoser().toUpperCase().contains(team.toUpperCase())) {
					result.append("In " + wsi.getYear() + " the " + wsi.getLoser() +
							" lost to the " + wsi.getWinner() + " by " + wsi.getScore()+"\n");
					b++;
				}
			}
			
			// if none found, print a message
			if (a + b == 0) {
				result.append("The " + team + " have not played in any World Series\n");
			}
			else {
				result.append("The " + team + " have won " + a + " World Series and lost "
						+ b + "\n");
			}
			
			
		}
		else {
			result.append("\"" + choice + "\" is not a valid entry.");
		}
		
		return result.toString();
		
	}
	
}
