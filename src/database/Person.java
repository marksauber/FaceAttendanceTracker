package database;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author Mark Sauber
 * Person class with attributes of id and a hashmap of dates to boolean values.
 * If the value is 1, then the person was there on that date. 
 */
public class Person {
	private int id;
	private ArrayList<String> dates; 
	public Person(int id){
		this.id = id;

	}
	public int getId(){
		return id;
	}
	public ArrayList<String> getDates(){
		return dates;
	}
	public void setDates(ArrayList<String> dates){
		this.dates =dates;
	}
	@Override
	public String toString()
	{
		return id + " "+dates.toString();
		
	}
}
