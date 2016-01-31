package database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Test class for database 
 * @author marke_000
 *
 */
public class DBTest {

	public static void main(String[] args) {
		System.out.println(DatabaseSupport.getName(1));
		Map<Integer,Integer> attendance = new HashMap<Integer,Integer>();
		
		attendance.put(1, 0);
		attendance.put(2, 1);
		attendance.put(3,0);
		attendance.put(4, 1);
		//DatabaseSupport.takeAttendance(attendance);
		
		ArrayList<Person> people = DatabaseSupport.getAttendance();
		for(Person p: people){
			System.out.println(p.toString());
		}
	}

}
