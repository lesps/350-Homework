package com.spencer.upenn.cis350.homework5;

import java.util.ArrayList;
import java.util.TreeSet;


public class DataSorter {

	private ArrayList<WorldSeriesInstance> _list;
	
	public DataSorter(DataStore ds){
		_list = ds.allInstances();
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
}
