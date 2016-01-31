package database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

/**
 * Test class for database 
 * @author marke_000
 *
 */
public class DBTest {

	public static void main(String[] args) {
		System.out.println(DatabaseSupport.getName(1));
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		ids.add(1);
		ids.add(2);
		ids.add(3);
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		DatabaseSupport.takeAttendance(ids,date);
		
		ArrayList<Person> people = DatabaseSupport.getAttendance();
		for(Person p: people){
			System.out.println(p.toString());
		}
	}

}
