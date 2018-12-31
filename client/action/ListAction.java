package client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.common.ClientReaderThread;
import client.common.CommonFunctions;
import client.frame.ListFrame;
import server.common.SQLConnect;
import server.information.Package;
import server.information.PackageType;

@SuppressWarnings("serial")
public class ListAction extends ListFrame{
	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	public ClientReaderThread read_thread;
	public String to_user = "";
	public String this_user;
	public HashMap<String, String> name_account = new HashMap<String, String>();
	
	public ListAction(Socket s, ObjectInputStream input, ObjectOutputStream output, String account) throws IOException {
		super();
		this.socket = s;
		this.input = input;
		this.output = output;
		this.this_user = account;
		setTitle(SQLConnect.from_account_to_name(this_user) + "£∫ª∂”≠ƒ„~");
		addListeners();
		read_thread = new ClientReaderThread(input, output, this);
		read_thread.start();
		refresh();
	}
	
	public void refresh() {
		Package p = new Package(this_user, "server", CommonFunctions.get_cur_time(), "", PackageType.refresh);
		try {
			output.writeObject(p);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addListeners() {
		this.send_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!send_area.getText().equals("") && !to_user.equals("")) {
					String message = CommonFunctions.get_cur_time() + "\n" + send_area.getText() + "\n\n";
					Package p = new Package(this_user, name_account.get(to_user), CommonFunctions.get_cur_time(), message, PackageType.chat);
					try {
						output.writeObject(p);
						output.flush();
						
						String log_mess = "Œ“£∫ " + message;
						Package log_p = new Package(this_user, name_account.get(to_user), CommonFunctions.get_cur_time(), log_mess, PackageType.log);
						output.writeObject(log_p);
						output.flush();
						
						send_area.setText("");
						testArea.append("Œ“£∫ " + message);
						testArea.setCaretPosition(testArea.getDocument().getLength());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		this.user_list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(user_list.getValueIsAdjusting()) {
					to_user = (String) user_list.getSelectedValue();
					setTitle("Chat with : " + to_user + " ~ ~");
					
					Package p = new Package(this_user, name_account.get(to_user), CommonFunctions.get_cur_time(), "", PackageType.get_chat_information);
					try {
						output.writeObject(p);
						output.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		this.change_password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordAction(input, output, ListAction.this);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e) {  
				 super.windowClosing(e);  
				 Package p = new Package(this_user, "server", CommonFunctions.get_cur_time(), "", PackageType.user_down);
				 try {
					output.writeObject(p);
					output.flush();
					read_thread.interrupt();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}  
		});
	}
}
