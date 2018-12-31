package client.action;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.frame.LoginFrame;
import client.frame.RegisterFrame;
import server.information.Package;
import server.information.PackageType;
import client.common.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "serial", "unused" })
public class LoginAction extends LoginFrame{
	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	
	public LoginAction() throws IOException {
		super();
		add_listeners();
		socket = CommonFunctions.get_socket();
		this.input = new ObjectInputStream(socket.getInputStream());
		this.output = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void log_in(){
		String account = this.account.getText();
		String password = String.valueOf(this.password.getPassword());
		
		if(account.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(this, "用户名密码不能为空，请重新输入", "失败", 0);
		}else {
			password = CommonFunctions.md5(password);
			try {
				String mess = password;
				Package packet = new Package(account, "server", CommonFunctions.get_cur_time(), mess, PackageType.login_apply);
				
				output.writeObject(packet);
				output.flush();
				
				packet = (Package) input.readObject();
				String me = (String) packet.data;
				if(packet.type == PackageType.login_fail) {
					JOptionPane.showMessageDialog(this, me, "Fail", JOptionPane.ERROR_MESSAGE);
				}else {
					new ListAction(socket, input, output, account);
					dispose();
				}
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(this, "server down", "down", 0);
				dispose();
			}
		}
	}
	
	public void register() {
		try {
			new RegisterAction(input, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add_listeners() {
		this.login_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				log_in();
			}
		});
		
		this.reg_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				register();
			}
		});
	}
	
	public static void main(String[] args) throws IOException {
		new LoginAction();
	}
}
