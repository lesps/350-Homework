package com.spencer.upenn.cis350.homework5;

import java.util.ArrayList;

public class DataViewer {
	private ArrayList<WorldSeriesInstance> _list;

	public DataViewer(DataStore ds){
		_list = ds.allInstances();
	}

	protected String showDataForYear(int year) {
		// look through all the instances
		for (WorldSeriesInstance wsi : _list) {
			if (wsi.getYear() == year) {
				// found it!
				return wsi.printWin();
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
				result.append(wsi.printWin() + "\n");
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
		boolean w, l, a;
		w = choice.equalsIgnoreCase("W");
		l = choice.equalsIgnoreCase("L");
		a = choice.equalsIgnoreCase("A");

		int wins, losses;
		losses = wins = 0;
		for (WorldSeriesInstance wsi : _list) {
			// convert to uppercase and use "contains" for partial matching
			if(w){
				if (wsi.getWinner().toUpperCase().contains(team.toUpperCase())) {
					// we found an instance when the team won
					result.append(wsi.printWin() + "\n");
					wins++;
				}
			} else if(l){
				if (wsi.getLoser().toUpperCase().contains(team.toUpperCase())) {
					result.append(wsi.printLoss() + "\n");
					losses++;
				}
			} else if(a){
				if (wsi.getWinner().toUpperCase().contains(team.toUpperCase())) {
					// we found an instance when the team won
					result.append(wsi.printWin() + "\n");
					wins++;
				}
				else if (wsi.getLoser().toUpperCase().contains(team.toUpperCase())) {
					result.append(wsi.printLoss()+"\n");
					losses++;
				}
			} 
		}

		if(w){
			if (wins == 0) {
				result.append("The " + team + " have not won any World Series\n");
			}
			else {
				result.append("The " + team + " have won " + wins + " World Series\n");
			}
		} else if(l){
			if (losses == 0) {
				result.append("The " + team + " have not lost any World Series\n");
			}
			else {
				result.append("The " + team + " have lost " + losses + " World Series\n");
			}
		} else if(a){
			if (wins + losses == 0) {
				result.append("The " + team + " have not played in any World Series\n");
			}
			else {
				result.append("The " + team + " have won " + wins + " World Series and lost "
						+ losses + "\n");
			}
		} else {
			result.append("\"" + choice + "\" is not a valid entry.");
		}

		/**
		if (choice.equalsIgnoreCase("W")) {
			// keep track of the number of wins for the team
			int m = 0;

			// look through all the instances
			for (WorldSeriesInstance wsi : _list) {
				// convert to uppercase and use "contains" for partial matching
				if (wsi.getWinner().toUpperCase().contains(team.toUpperCase())) {
					// we found an instance when the team won
					result.append(wsi.printWin() + "\n");
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
					result.append(wsi.printLoss() + "\n");
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
					result.append(wsi.printWin() + "\n");
					a++;
				}
				else if (wsi.getLoser().toUpperCase().contains(team.toUpperCase())) {
					result.append(wsi.printLoss()+"\n");
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
		 **/	
		return result.toString();


	}

}
