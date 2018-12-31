package client.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

import client.common.CommonFunctions;
import client.frame.ChangePasswordFrame;
import server.information.Package;
import server.information.PackageType;

@SuppressWarnings("serial")
public class ChangePasswordAction extends ChangePasswordFrame{
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public ListAction frame;
	
	public ChangePasswordAction(ObjectInputStream input, ObjectOutputStream output, ListAction frame) {
		super();
		this.input = input;
		this.output = output;
		this.frame = frame;
		addListeners();
	}
	
	public void addListeners() {
		this.cancle_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		this.confirm_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				process_confirm();
			}
		});
	}
	
	public void process_confirm() {
		String old_password = String.valueOf(old_pass.getPassword());
		String new_password = String.valueOf(new_pass.getPassword());
		String another_new_password = String.valueOf(another_new_pass.getPassword());
		
		if(old_password.equals("") || new_password.equals("") || another_new_password.equals("")) JOptionPane.showMessageDialog(this, "填写不能为空", "Error", JOptionPane.ERROR_MESSAGE);
		else if(!new_password.equals(another_new_password)) JOptionPane.showMessageDialog(this, "两次密码不一致，重新输入", "Error", JOptionPane.ERROR_MESSAGE);
		else {
			Package packet = new Package(frame.this_user, "server", CommonFunctions.get_cur_time(), CommonFunctions.md5(new_password), PackageType.update_password);
			try {
				output.writeObject(packet);
				output.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
