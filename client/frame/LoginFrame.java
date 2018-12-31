package client.frame;

import javax.swing.*;

import client.common.ImagePanel;

import java.awt.*;
import java.io.IOException;

@SuppressWarnings({ "serial" })
public class LoginFrame extends JFrame
{
	public JPanel content_pane;
	public JPasswordField password;
	public JTextField account;
	public JLabel pswd_lable, account_lable;
	public JButton login_btn, reg_btn;
	
    public LoginFrame() throws IOException
    {
        setTitle("µÇÂ¼");
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/client/icon/login.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 250);
        setResizable(false);
        content_pane = new ImagePanel("login.png");
        content_pane.setLayout(null);
        content_pane.setOpaque(true);
        
        account_lable = new JLabel("ÕËºÅ£º");
        account_lable.setBounds(55, 30, 120, 25);
        account_lable.setFont(new Font("ºÚÌå", Font.BOLD, 14));
        content_pane.add(account_lable);
        
        account = new JTextField(10);
        account.setBounds(120, 30, 120, 25);
        account.setOpaque(false);
        content_pane.add(account);
        
        pswd_lable = new JLabel("ÃÜÂë£º");
        pswd_lable.setBounds(55, 80, 120, 25);
        pswd_lable.setFont(new Font("ºÚÌå", Font.BOLD, 14));
        content_pane.add(pswd_lable);
        
        password = new JPasswordField(10);
        password.setBounds(120, 80, 120, 25);
        password.setOpaque(false);
        content_pane.add(password);
        
        login_btn = new JButton("µÇÂ¼");
        login_btn.setFont(new Font("ºÚÌå", Font.BOLD, 14));
        login_btn.setBounds(55, 130, 80, 20);
        content_pane.add(login_btn);
        
        reg_btn = new JButton("×¢²á");
        reg_btn.setFont(new Font("ºÚÌå", Font.BOLD, 14));
        reg_btn.setBounds(155, 130, 80, 20);
        content_pane.add(reg_btn);
        
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
