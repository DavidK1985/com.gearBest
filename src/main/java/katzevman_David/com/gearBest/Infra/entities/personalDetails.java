package katzevman_David.com.gearBest.Infra.entities;

public class personalDetails {

	public String email;
	public String password;
	
	public personalDetails(String email,String password) {
		this.email = email;
		this.password = password;
	}
	
	public String toString() {
		return "Email: " + email + "; Password: " + password;
	}
} 
