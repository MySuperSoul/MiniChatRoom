package client.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.action.ListAction;
import server.common.SQLConnect;
import server.information.Package;
import server.information.PackageType;

public class ClientReaderThread extends Thread{
	public ObjectInputStream input;
	public ListAction frame;
	public ObjectOutputStream output;
	
	public ClientReaderThread(ObjectInputStream input, ObjectOutputStream output, ListAction frame) throws IOException {
		this.input = input;
		this.output = output;
		this.frame = frame;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			try {
				Package packet = (Package) input.readObject();
				if(packet == null) continue;
				else {
					if(packet.type == PackageType.refresh) {
						frame.name_account.clear();
						frame.list_model.clear();
						ArrayList<String> user = (ArrayList<String>) packet.data;
						for(int i = 0; i < user.size() - 1; i += 2) {
							String ac = user.get(i);
							String na = user.get(i + 1);
							frame.list_model.addElement(na);
							frame.name_account.put(na, ac);
						}
					}else if(packet.type == PackageType.user_down) {
						String mess = (String) packet.data;
						JOptionPane.showMessageDialog(frame, mess, "UserDown", JOptionPane.PLAIN_MESSAGE);
						
						//Then refresh
						if(packet.to.equals(frame.to_user)) {
							frame.refresh();
							frame.testArea.setText("");
							frame.to_user = "";
							frame.setTitle(SQLConnect.from_account_to_name(frame.this_user));
						}
					}else if(packet.type == PackageType.update_password) {
						String infor = (String) packet.data;
						JOptionPane.showMessageDialog(null, infor, "Success", JOptionPane.PLAIN_MESSAGE);
					}else if(packet.type == PackageType.login_fail) {
						
					}else if(packet.type == PackageType.chat) {
						String from_name = CommonFunctions.from_account_to_name(packet.from, frame.name_account);
						
						if(packet.from.equals(frame.name_account.get(frame.to_user))) {
							frame.testArea.append(from_name + "对你说： "+(String) packet.data);
							frame.testArea.setCaretPosition(frame.testArea.getDocument().getLength());
						}else {
							JOptionPane.showMessageDialog(frame, "来自 " + from_name + " 的新消息~~~~", "new message", JOptionPane.PLAIN_MESSAGE);
						}
						String log_mess = from_name + "对你说： "+(String) packet.data;
						Package p = new Package(packet.to, packet.from, packet.send_time, log_mess, PackageType.log);
						
						output.writeObject(p);
						output.flush();
					}else if(packet.type == PackageType.new_user_up) {
						frame.refresh();
						String up_name = packet.to;
						JOptionPane.showMessageDialog(frame, "用户： " + up_name + " 上线！", "新用户上线", JOptionPane.PLAIN_MESSAGE);
					}else if(packet.type == PackageType.get_chat_information) {
						frame.testArea.setText("");
						ArrayList<String> s = (ArrayList<String>) packet.data;
						for(String in : s) {
							frame.testArea.append(in);
						}
						frame.testArea.setCaretPosition(frame.testArea.getDocument().getLength());
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				interrupt();
			}
		}
	}
}
