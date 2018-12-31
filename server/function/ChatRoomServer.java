package server.function;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import client.common.CommonFunctions;
import server.common.SQLConnect;
import server.information.Package;
import server.information.PackageType;
import server.information.User;

@SuppressWarnings("serial")
public class ChatRoomServer extends ServerFrame{
	public ServerSocket server;
	public HashMap<String, ServerThread> usermap = new HashMap<String, ServerThread>(); //account->thread
	public void addListener() {
		shut_down_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					SQLConnect.insert_into_log(CommonFunctions.get_cur_time(), "Mini聊天室服务器已关闭！");
					server.close();
					dispose();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		show_user_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				log_area.setText("");
				Connection conn = SQLConnect.getcon();
				PreparedStatement pstmt;
				String sql = "select * from user";
			    try {
					pstmt = (PreparedStatement)conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						String state = (rs.getString(8).equals("0")) ? "离线" : "在线";
						String show_mess = String.format("%-20s", rs.getString(1)) + String.format("%-20s", rs.getString(3)) + String.format("%-20s", state) + "\n\n";
						log_area.append(show_mess);
					}
					CommonFunctions.set_cursor_lowest(log_area);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		show_log_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				log_area.setText("");
				Connection conn = SQLConnect.getcon();
				PreparedStatement pstmt;
				String sql = "select * from log";
			    try {
					pstmt = (PreparedStatement)conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						String time = rs.getString(1);
						String mess = rs.getString(2);
						String show_mess = time + " : " + mess + "\n\n";
						log_area.append(show_mess);
					}
					CommonFunctions.set_cursor_lowest(log_area);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public ChatRoomServer(){
		super();
		try {
			server = new ServerSocket(CommonFunctions.ServerPort);
			addListener();
			SQLConnect.insert_into_log(CommonFunctions.get_cur_time(), "Mini聊天室服务器已开启！");
			while(true) {
				Socket socket = server.accept();
				ServerThread new_thread = new ServerThread(socket, server, this);
				new_thread.start();
			}
		} catch (IOException e) {
			//e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new ChatRoomServer();
	}
}

class ServerThread extends Thread{
	public Socket socket;
	public ServerSocket server;
	public ObjectOutputStream output; 
	public ObjectInputStream input;
	public ChatRoomServer cs;
	
	public ServerThread(Socket s, ServerSocket server, ChatRoomServer cs) throws IOException {
		this.socket = s;
		this.server = server;
		this.cs = cs;
		output = new ObjectOutputStream(s.getOutputStream());
		input = new ObjectInputStream(s.getInputStream());
	}
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			try {
				Package packet = (Package) input.readObject();
				if(packet == null) continue;
				else {
					if(packet.type == PackageType.login_apply) process_login(packet);
					else if(packet.type == PackageType.register) process_register(packet);
					else if(packet.type == PackageType.refresh) {
						Connection conn = SQLConnect.getcon();
						PreparedStatement pstmt;
						String sql = "select * from user where isonline = 1 and account != '"+packet.from+"'";
					    try {
					        pstmt = (PreparedStatement)conn.prepareStatement(sql);
					        ResultSet rs = pstmt.executeQuery();
					        ArrayList<String> infor = new ArrayList<String>();
					        
					        while (rs.next()) {
					        	infor.add(rs.getString(1));
					            infor.add(rs.getString(3));
					        }
					        System.out.println(infor.size());
					        Package p = new Package("server", packet.from, CommonFunctions.get_cur_time(), infor, PackageType.refresh);
					        output.writeObject(p);
					        output.flush();
					        conn.close();    
					    } catch (SQLException e) {
					        e.printStackTrace();
					    }
					}else if(packet.type == PackageType.chat) {
						ServerThread st = cs.usermap.get(packet.to);
						st.output.writeObject(packet);
						
						SQLConnect.insert_into_log(packet.send_time, SQLConnect.from_account_to_name(packet.from) + " talk to " + SQLConnect.from_account_to_name(packet.to));
					}else if(packet.type == PackageType.log) {
						String mess = (String) packet.data;
						String from = packet.from;
						String to = packet.to;
						String time = packet.send_time;
						String sql = "";
						
						Connection conn = SQLConnect.getcon();
						PreparedStatement pstmt;
						
						sql = "insert into message values ('"+from+"', '"+to+"', '"+time+"', '"+mess+"')";
						System.out.println(sql);
						try {
							pstmt = (PreparedStatement)conn.prepareStatement(sql);
							pstmt.executeUpdate();
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}else if(packet.type == PackageType.get_chat_information) {
						String from = packet.from;
						String to = packet.to;
						
						Connection conn = SQLConnect.getcon();
						PreparedStatement pstmt;
						String sql = "select * from message where send_side = '"+from+"' and recv_side = '"+to+"'";
						try {
					        pstmt = (PreparedStatement)conn.prepareStatement(sql);
					        ResultSet rs = pstmt.executeQuery();
					        ArrayList<String> infor = new ArrayList<String>();
					        
					        while (rs.next()) {
					        	infor.add(rs.getString(4));
					        }
					        System.out.println(infor.size());
					        Package p = new Package("server", packet.from, CommonFunctions.get_cur_time(), infor, PackageType.get_chat_information);
					        output.writeObject(p);
					        output.flush();
					        conn.close();    
					    } catch (SQLException e) {
					        e.printStackTrace();
					    }
					}else if(packet.type == PackageType.user_down) {
						process_user_down(packet);
					}else if(packet.type == PackageType.update_password) {
						process_update_pass(packet);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				interrupt();
				try {
					input.close();
					output.close();
					socket.close();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void process_update_pass(Package packet) {
		String account = packet.from;
		String new_pass = (String) packet.data;
		Connection conn = SQLConnect.getcon();
		String sql = "update user set password = '" + new_pass + "' where account = '" + account + "'";
		PreparedStatement pstmt;
		
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.execute();
			
			//insert into log database
			SQLConnect.insert_into_log(packet.send_time, SQLConnect.from_account_to_name(account) + " 修改密码！");
			conn.close();
			
			//write information back to client
			Package p = new Package("server", account, CommonFunctions.get_cur_time(), "修改密码成功！", PackageType.update_password);
			output.writeObject(p);
			output.flush();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void process_user_down(Package packet) {
		String from_account = packet.from;
		
		//first remove from the usermap
		cs.usermap.remove(from_account);
		
		//then update database isonline attribute
		Connection conn = SQLConnect.getcon();
		String sql = "update user set isonline = 0 where account = '" + from_account + "'";
		PreparedStatement pstmt;
		
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.execute();
			//insert into the system log
			SQLConnect.insert_into_log(packet.send_time, SQLConnect.from_account_to_name(from_account) + " 下线了！");
			conn.close();
			
			//finally broadcast the information
			String from_name = SQLConnect.from_account_to_name(from_account);
			Package p = new Package("server", from_name, CommonFunctions.get_cur_time(), from_name + " 下线了!", PackageType.user_down);
			
			for(String key : cs.usermap.keySet()){
				ServerThread st = cs.usermap.get(key);
				st.output.writeObject(p);
				st.output.flush();
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void process_login(Package packet) {
		String account = packet.from;
		String md5code = (String)packet.data;
		
		Connection conn = SQLConnect.getcon();
		String sql = "select * from user where account = '"+account+"' and password = '"+md5code+"'";
		PreparedStatement pstmt;
		
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.getMetaData().getColumnCount() == 0) {
				Package p = new Package("server", account, CommonFunctions.get_cur_time(), "用户名或密码错误", PackageType.login_fail);
				output.writeObject(p);
				output.flush();
			}else {
				Package p = new Package("server", account, CommonFunctions.get_cur_time(), "登陆验证成功", PackageType.login_success);
				output.writeObject(p);
				output.flush();
				cs.usermap.put(account, this);
				
				String up_name = null;
				while(rs.next()) up_name = rs.getString(3);
				//set isonline to 1
				sql = "update user set isonline = 1 where account = '" + account + "'";
				pstmt = (PreparedStatement)conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
				//broadcast
				p = new Package("server", up_name, CommonFunctions.get_cur_time(), "", PackageType.new_user_up);
				for(String key : cs.usermap.keySet()){
					if(key != account) {
						ServerThread st = cs.usermap.get(key);
						st.output.writeObject(p);
						st.output.flush();
					}
				}
				
				//insert into system log database
				SQLConnect.insert_into_log(p.send_time, SQLConnect.from_account_to_name(account) + " 上线了！");
			}
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void process_register(Package packet) {
		User user = (User) packet.data;
		Connection conn = SQLConnect.getcon();
		String sql = "insert into user values ('" + user.account+"', '"+user.password+"', '"+user.name+"', '"+user.phone+"', '"+user.introduction+"', '"+user.gender+"', "+user.age+", 0)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			Package p = new Package("server", user.account, CommonFunctions.get_cur_time(), "注册成功", PackageType.register_success);
			output.writeObject(p);
			pstmt.close();
			conn.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void process_server_stop() {
		this.interrupt();
		System.exit(0);
	}
}
