package com.spencer.upenn.cis350.homework5;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
	
}
