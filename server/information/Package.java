package server.information;

import java.io.Serializable;

/*Package to transform on the LAN network*/
@SuppressWarnings("serial")
public class Package implements Serializable{
	public String from;
	public String to;
	public Object data;
	public String send_time;
	public PackageType type;
	
	public Package(String f, String t, String time, Object da, PackageType ty) {
		from = f;
		to = t;
		send_time = time;
		data = da;
		type = ty;
	}
}
