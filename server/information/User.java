package server.information;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	public String account;
	public String password;
	public String name;
	public int age;
	public String phone;
	public String gender;
	public String introduction;
	
	public User(String ac, String pa, String na, int ag, String ph, String ge, String in) {
		account = ac;
		password = pa;
		name = na;
		age = ag;
		phone = ph;
		gender = ge;
		introduction = in;
	}
}
