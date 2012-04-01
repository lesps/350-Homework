package com.spencer.upenn.cis350.homework5;
import java.util.ArrayList;


public class Sorter {
	
	/*
	 * Show all of the World Series results, sorted by the winning team
	 */
	public static String sortByWinners() {
		DataStore ds = new DataStore(UserInterface.DATAFILE);
		
		// to hold the return value
		StringBuffer result = new StringBuffer();
		
		ArrayList<WorldSeriesInstance> list = ds.allInstances();
		
		/*
		 All right, this is mega-complicated so pay close attention.
		 In order to group the WS results by winning team, I loop through
		 the whole list and I have two ArrayLists: 
		 -- teams: holds the distinct team names in the order in which I see them
		 -- wins: each entry is ITSELF an ArrayList holding the years in which that team won
		 The indices of teams and wins MUST match, ie the k-th entry in wins corresponds
		 to the k-th entry in teams.
		 */
		
		ArrayList<String> teams = new ArrayList<String>();
		ArrayList<ArrayList<Integer>> wins = new ArrayList<ArrayList<Integer>>();
		
		for (WorldSeriesInstance wsi : list) {

			// see if the winner is already in the list of teams
			int teamsIndex = teams.indexOf(wsi.getWinner());
			
			// if it's -1, then we haven't seen this team before
			if (teamsIndex == -1) {
				// add it to the list of teams
				teams.add(wsi.getWinner());
				// create an entry in the wins list
				ArrayList<Integer> newEntry = new ArrayList<Integer>();
				newEntry.add(wsi.getYear());
				wins.add(newEntry);
			}
			// if it's not -1, then we've already seen this team
			else {
				// so just update the corresponding entry in wins
				wins.get(teamsIndex).add(wsi.getYear());
			}
		}
		
		// now we need to sort the teams list! but we also need to keep the 
		// wins list in the corresponding order! omfg this is such a hack
		// selection sort to the rescue
		for (int i = 0; i < teams.size(); i++) {
			// find the "smallest" remaining value
			String min = teams.get(i);
			int minIndex = i;
			for (int j = i; j < teams.size(); j++) {
				if (teams.get(j).compareTo(min) < 0) {
					min = teams.get(j);
					minIndex = j;
				}
			}
			// swap it with the first one
			String temp = teams.get(i);
			teams.set(i, teams.get(minIndex));
			teams.set(minIndex, temp);
			
			// now do the same swap for the wins array
			ArrayList<Integer> t = wins.get(i);
			wins.set(i, wins.get(minIndex));
			wins.set(minIndex, t);
			
		}
		
		// now put together the result
		for (int i = 0; i < teams.size(); i++) {
			result.append(teams.get(i) + ": ");
			ArrayList<Integer> teamWins = wins.get(i);
			for (int j = 0; j < teamWins.size(); j++) {
				result.append(teamWins.get(j));
				if (j < teamWins.size()-1) result.append(", ");
			}
			result.append("\n");
		}
		
		return result.toString();
		
	}


}
