package client.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

import client.common.ClientReaderThread;
import client.common.CommonFunctions;
import client.frame.RegisterFrame;
import server.information.Package;
import server.information.PackageType;
import server.information.User;

@SuppressWarnings("serial")
public class RegisterAction extends RegisterFrame{
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public ClientReaderThread thread;
	public JOptionPane pane = new JOptionPane();
	public Font f = new Font("黑体", Font.BOLD, 14);
	
	public RegisterAction(ObjectInputStream input, ObjectOutputStream output) throws IOException {
		super();
		addlisteners();
		this.input = input;
		this.output = output;
	}
	
	@SuppressWarnings("static-access")
	public void confirm_review() throws IOException {
		String Account = account.getText();
		String Name = name.getText();
		String Password = String.valueOf(password.getPassword());
		String Another = String.valueOf(another_password.getPassword());
		String Phone = phone.getText();
		String Gender = String.valueOf(gender.getSelectedItem());
		int Age = Integer.valueOf(age.getText());
		String Intro = introduction.getText();
		System.out.println(Password + "/" + Another);
		if(!Password.equals(Another)) pane.showMessageDialog(null, "密码不一致，请重新输入", "失败", JOptionPane.ERROR_MESSAGE);
		else {
			User user = new User(Account, CommonFunctions.md5(Password), Name, Age, Phone, Gender, Intro);
			Package packet = new Package(Account, "server", CommonFunctions.get_cur_time(), user, PackageType.register);
			output.writeObject(packet); //send the package to server
			output.flush();
			
			try {
				Package p = (Package) input.readObject();
				String message = (String) p.data;
				JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.PLAIN_MESSAGE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addlisteners() {
		this.cancle_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				account.setText("");
				name.setText("");
				age.setText("");
				phone.setText("");
				password.setText("");
				another_password.setText("");
				introduction.setText("个人介绍");
				introduction.setFont(new Font("黑体", Font.PLAIN, 14));
				introduction.setForeground(Color.GRAY);
				gender.setSelectedIndex(0);
			}
		});
		
		this.confirm_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					confirm_review();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
