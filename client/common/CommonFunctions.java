package client.common;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JTextArea;

public class CommonFunctions {
	public static String ServerIP = "127.0.0.1";
	public static int ServerPort = 6673;
	
	/** Function: used to produce md5 encrypt code*/
	public static String md5(String plaintext) {
		byte[] b = null;
		try {
			b = MessageDigest.getInstance("md5").digest(plaintext.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String md5code = new BigInteger(1, b).toString(16);
		for(int i = 0; i < 32 - md5code.length(); i++) {
			md5code += "0";
		}
		
		return md5code;
	}
	
	/** new a socket to the server
	 * @throws IOException 
	 * @throws UnknownHostException */
	public static Socket get_socket() throws UnknownHostException, IOException {
		Socket socket = new Socket(ServerIP, ServerPort);
		return socket;
	}
	
	/** Get current time*/
	public static String get_cur_time(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/** from account to name*/
	public static String from_account_to_name(String account, HashMap<String, String> hmap) {
		String from_name = null;
		for(String s : hmap.keySet()) {
			if(hmap.get(s).equals(account)) {
				from_name = s;
				break;
			}
		}
		return from_name;
	}
	
	/** set the cursor to the lowest of the JTextArea*/
	public static void set_cursor_lowest(JTextArea jt) {
		jt.setCaretPosition(jt.getDocument().getLength());
	}
}
