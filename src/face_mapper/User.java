package face_mapper;
/**
 * Model of a user for the FaceMapper application.
 * @author Davis Batten
 *
 */
public class User {
	
	private String name;
	private String email;
	private String id;
	
	//constructor
	public User(String name, String email, String id){
		this.name = name;
		this.email = email;
		this.id = id;
	}
	
	
	//get methods
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public static User findById(int id){
		//TODO fetch and return user by id
		return null;
	}

}
