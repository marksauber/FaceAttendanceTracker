package database;
import java.awt.Image;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Database Support class
 * @author Mark Sauber
 * 
 *  The methods in this class use JDBC (Java Database Connection) to manipulate a MySQL database. To make the program
* work in Eclipse you will need to add the MySQL connector (Connector/J) for JDBC (https://www.mysql.com/products/connector/)
* as an external jar to your Eclipse project
* 
* Steps to Get Your Project working with Database
* 1. You will need to download UwAmp http://www.uwamp.com/en/?page=download  and mysql workbench https://dev.mysql.com/downloads/workbench/.
*  2. Run the UwAmp server, by pressing the arrow next to start and clicking the sql server. 
*  3. Open mysql workbench and click the plus button to open a new connection. Keep the default settings 
*  and you should be on port 3306. Name the connection what ever you want. Username and password should be set to 
*  root.
*  4.Dowload the folder I shared with you. Click server and data import on mysql workbench. Choose import from dump project and find
*  the dump project I shared with you. You are now ready to rock!
* 
* 
 */
public class DatabaseSupport {
	
	private static Connection connection;
	/**
	 * Gets the name associated with this id
	 * @param id
	 * @return
	 */
	public static String getName(int id)
	{
		String name=null;

		try {
			connection = getConnection();
			if(connection==null)
				name=null;
			else{
				Statement stmt = connection.createStatement();
				ResultSet rs=stmt.executeQuery("select name from people where id='"+id+"'");
				if(rs.next())
				{
					name = rs.getString("name");
				}
				rs.close();
				stmt.close();
				connection.close();
			}
		} catch (SQLException sqle){
			name=null;
			sqle.printStackTrace();
			while (sqle != null) {
				String logMessage = "\n SQL Error: "+
						sqle.getMessage() + "\n\t\t"+
						"Error code: "+sqle.getErrorCode() +
						"\n\t\t"+
						"SQLState: "+sqle.getSQLState()+"\n";
				System.out.println(logMessage);
				sqle = sqle.getNextException();
			}
		}
		return name;

	}
	public static void saveImage(Image img)
	{

	}
	public static Image getImage()
	{
		return null;
		
	}
	/**
	 * The person object has a hashmap whose keys are dates and whose values are ints that are 1
	 * if someone attended on a certain date and 0 if the didn't attend. 
	 * @return
	 */
	public static ArrayList<Person> getAttendance()
	{
		//Get the dates
		//Use dates to find info
		ArrayList<Person> people = new ArrayList<Person>();
		try {
			connection = getConnection();
			if(connection==null){}
			else{
				//get ids
				Statement stmt = connection.createStatement();
				ResultSet rs=stmt.executeQuery("select id from people");
				ArrayList<Integer> ids = new ArrayList<Integer>();
				while(rs.next())
				{
					ids.add(rs.getInt("id"));
				}
				rs.close();
				//find info by id
				for(int id:ids)
				{
					
					rs=stmt.executeQuery("select * from attendance where id='"+id+"'");
					Person p = new Person(id);
					HashMap<String, Integer> personalAttendance = new HashMap<String,Integer>();
					while(rs.next())
					{
						personalAttendance.put(rs.getString("date"),rs.getInt("present"));
					}
					p.setattendance(personalAttendance);
					people.add(p);
				}
				rs.close();
				stmt.close();
				connection.close();
			}
		} catch (SQLException sqle){
			sqle.printStackTrace();
			while (sqle != null) {
				String logMessage = "\n SQL Error: "+
						sqle.getMessage() + "\n\t\t"+
						"Error code: "+sqle.getErrorCode() +
						"\n\t\t"+
						"SQLState: "+sqle.getSQLState()+"\n";
				System.out.println(logMessage);
				sqle = sqle.getNextException();
			}
		}

		return people;
		
	}
	/**
	 * Adds a date and should be called after take attendance is pressed. The hashmap's keys are ids and values
	 * are integers that are 1 someone was present
	 * @param attendance
	 */
	public static void takeAttendance(Map<Integer, Integer> attendance)
	{
		//Operations
		//Get current Date
		//add to Date table
		//create table with date as the table name and insert the information
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd/hh:mm:ss");
		String currentDate = ft.format(dNow);
		try {
			connection = getConnection();
			if(connection==null){}
			else{
				Statement stmt = connection.createStatement();
				String sql = "INSERT INTO dates " +
		                   "VALUES ('"+currentDate+"')";
		      stmt.executeUpdate(sql);
		      for (Entry<Integer, Integer> entry : attendance.entrySet()) {
		    	    System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
		    	    sql = "INSERT INTO attendance(date,present,id) " +
			                   "VALUES ('"+currentDate+"', '"+entry.getValue()+"', '"+entry.getKey()+"')";
		    	    stmt.executeUpdate(sql);
		    	}
		      
				stmt.close();
				connection.close();
			}
		} catch (SQLException sqle){
			sqle.printStackTrace();
			while (sqle != null) {
				String logMessage = "\n SQL Error: "+
						sqle.getMessage() + "\n\t\t"+
						"Error code: "+sqle.getErrorCode() +
						"\n\t\t"+
						"SQLState: "+sqle.getSQLState()+"\n";
				System.out.println(logMessage);
				sqle = sqle.getNextException();
			}
		}

		
	}
	/**
	 * Gets the connection to the DB
	 * @return
	 */
	private static Connection getConnection(){
		try{
			/**
			 * Load jdbc driver for MySQL
			 */
			Class.forName("com.mysql.jdbc.Driver");


			String url = "jdbc:mysql://localhost/mydb";

			connection = DriverManager.getConnection (url, "root", "root");// you will need to change the password to
			// match the one you set up when you downloaded
			// and installed MySQL workbench.
		}
		catch(ClassNotFoundException nfe){
			connection=null;
		}
		catch(SQLException sql){
			connection=null;
		}
		return connection;
	}
}
