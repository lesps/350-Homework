package com.spencer.upenn.cis350.homework5;

import java.util.TreeSet;

public class Team implements Comparable<Team>{

	private String _name;
	private TreeSet<Integer> _wins;
	
	public Team(String name){
		_name = name;
		_wins = new TreeSet<Integer>();
	}
	
	public Team(String name, int year){
		_name = name;
		_wins = new TreeSet<Integer>();
		_wins.add(year);
	}
	
	public Team(String name, TreeSet<Integer> wins){
		_name = name;
		_wins = wins;
	}
	
	public void addWin(int year){
		_wins.add(year);
	}
	
	public TreeSet<Integer> getWins(){
		return _wins;
	}
	
	@Override
	public int compareTo(Team other){
		if(this._name.compareToIgnoreCase(other._name) < 0 )
			return -1;
		else if(this._name.compareToIgnoreCase(other._name) > 0)
			return 1;
		else
			return 0;
	}
	
	@Override
	public boolean equals(Object other){
		if(other==null)
			return false;
		if(!(other instanceof Team))
			return false;
		return this.compareTo((Team)other)==0;
	}
	
	@Override
	public int hashCode(){
		return _name.hashCode();
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer(_name);
		buf.append(": ");
		if(!_wins.isEmpty()){
			String set = _wins.toString();
			buf.append(set.substring(1, set.length()-1));
		}
		buf.append("\n");
		return buf.toString();
		
	}
}
