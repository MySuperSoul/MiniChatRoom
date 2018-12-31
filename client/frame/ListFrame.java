package client.frame;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import client.common.ImageButton;

@SuppressWarnings("serial")
public class ListFrame extends JFrame{
	public JPanel content_pane;
	public JScrollPane right_scroll;
	public JTextArea testArea, send_area;
	@SuppressWarnings("rawtypes")
	public JList user_list;
	public JButton send_btn;
	@SuppressWarnings("rawtypes")
	public DefaultListModel list_model;
	public Font f = new Font("黑体", Font.BOLD, 14);
	public TitledBorder tb = new TitledBorder("");
	public JMenuBar menu = new JMenuBar();
	public JMenu tools_menu;
	public JMenuItem change_password = new JMenuItem("修改密码");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ListFrame() throws IOException {
		setTitle("ChatRoom");
		setBounds(100, 200, 700, 530);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		content_pane = new JPanel();
		content_pane.setLayout(null);
		content_pane.setOpaque(false);
		
		testArea = new JTextArea();
		testArea.setBackground(Color.WHITE);
		testArea.setFont(f);
		testArea.setEditable(false);
		testArea.setLineWrap(true);
		
		right_scroll = new JScrollPane();
		right_scroll.setViewportView(testArea);
		tb.setTitle("消息显示区");
		tb.setTitleFont(f);
		tb.setTitleColor(Color.RED);
		right_scroll.setBorder(tb);
		right_scroll.setBounds(150, 5, getWidth() - 170, 300);
		right_scroll.setOpaque(false);
		content_pane.add(right_scroll);
		
		send_area = new JTextArea();
		send_area.setFont(f);
		send_area.setBorder(new TitledBorder("Send"));
		send_area.setLineWrap(true);
		send_area.setBackground(Color.WHITE);
		send_area.setBounds(150, 305, getWidth() - 170, 125);
		content_pane.add(send_area);
		
		send_btn = new ImageButton("send.jpg");
		int width = send_btn.getIcon().getIconWidth();
		int height = send_btn.getIcon().getIconHeight();
		
		send_btn.setBounds(615, 430, width, height);
		content_pane.add(send_btn);
		
		list_model = new DefaultListModel();
		user_list = new JList<>(list_model);
		user_list.setBounds(10, 10, 130, 450);
		user_list.setOpaque(true);
		user_list.setFont(f);
		user_list.setBorder(new TitledBorder("在线用户"));
		content_pane.add(user_list);
		
		tools_menu = new JMenu("工具箱");
		tools_menu.setFont(f);
		menu.add(tools_menu);
		
		change_password.setFont(f);
		tools_menu.add(change_password);
		
		this.setJMenuBar(menu);
		this.add(content_pane);
		
		String lookAndFeel =UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setVisible(true);
	}
}
