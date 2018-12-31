package server.function;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame{
	public JPanel content_pane;
	public JButton shut_down_btn, show_user_btn, show_log_btn;
	public JTextArea log_area;
	public JScrollPane scrollPane;
	
	public ServerFrame() {
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ServerFrame.class.getResource("/client/icon/login.png")));
		this.setFont(new Font("等线", Font.BOLD, 14));
		this.setResizable(false);
		this.setTitle("ChatRoom\u670D\u52A1\u5668");
		this.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(164, 10, 433, 312);
		this.getContentPane().add(scrollPane);
		
		log_area = new JTextArea();
		log_area.setEditable(false);
		log_area.setLineWrap(true);
		log_area.setForeground(SystemColor.textHighlight);
		log_area.setFont(new Font("等线", Font.BOLD, 13));
		scrollPane.setViewportView(log_area);
		
		shut_down_btn = new JButton("\u5173\u95ED\u670D\u52A1\u5668");
		
		shut_down_btn.setFont(new Font("等线", Font.BOLD, 14));
		shut_down_btn.setBounds(46, 10, 108, 62);
		this.getContentPane().add(shut_down_btn);
		
		show_user_btn = new JButton("\u7528\u6237");
		
		show_user_btn.setFont(new Font("等线", Font.BOLD, 14));
		show_user_btn.setBounds(46, 133, 108, 62);
		this.getContentPane().add(show_user_btn);
		
		show_log_btn = new JButton("\u65E5\u5FD7\u4FE1\u606F");
		
		show_log_btn.setFont(new Font("等线", Font.BOLD, 14));
		show_log_btn.setBounds(46, 260, 108, 62);
		
		this.getContentPane().add(show_log_btn);
		this.setBounds(100, 100, 613, 371);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
