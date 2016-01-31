package database;

import java.util.HashMap;
/**
 * 
 * @author Mark Sauber
 * Person class with attributes of id and a hashmap of dates to boolean values.
 * If the value is 1, then the person was there on that date. 
 */
public class Person {
	private int id;
	private HashMap<String, Integer> attendance; 
	public Person(int id){
		this.id = id;

	}
	public int getId(){
		return id;
	}
	public HashMap<String, Integer> getattendance(){
		return attendance;
	}
	public void setattendance(HashMap<String, Integer> attendance){
		this.attendance = attendance;
	}
	@Override
	public String toString()
	{
		return id + " "+attendance.toString();
		
	}
}
