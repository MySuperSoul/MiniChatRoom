package server.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class SQLConnect {
	public static Connection getcon() {
		String driver = "com.mysql.cj.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/chatroom ? characterEncoding=utf8 & useSSL=true & serverTimezone=GMT";
	    String username = "root";
	    String password = "123456";
	    Connection conn = null;
	    
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public static void insert_into_log(String time, String message) {
		Connection conn = getcon();
		PreparedStatement pstmt;
		String sql = "insert into log values ('"+time+"', '"+message+"')";
		
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** override up function*/
	public static String from_account_to_name(String account) {
		String from_name = null;
		Connection conn = getcon();
		PreparedStatement pstmt;
		String sql = "select * from user where account = '" + account + "'";
		
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) from_name = rs.getString(3);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return from_name;
	}
}
