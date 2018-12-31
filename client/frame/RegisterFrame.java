package client.frame;

import javax.swing.*;

import client.action.LoginAction;
import client.common.ImageButton;
import client.common.ImagePanel;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@SuppressWarnings({ "serial", "unused" })
public class RegisterFrame extends JFrame{
	public JPanel content_pane;
	public JLabel account_lable, pswd_lable, another_password_lable, name_lable, age_lable, phone_lable, gender_lable, intro_lable;
	public JTextField account, name, age, phone;
	public JPasswordField password, another_password;
	public JButton confirm_btn, cancle_btn;
	public JComboBox<String> gender;
	public JTextArea introduction;
	
	public Font f = new Font("黑体", Font.BOLD, 14);
	
	public RegisterFrame() throws IOException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				
			}
		});
		
		setTitle("注册");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setResizable(false);
        
        content_pane = new ImagePanel("reg.jpg");
        content_pane.setLayout(null);
        content_pane.setOpaque(true);
        
        account_lable = new JLabel("账号：");
        account_lable.setBounds(55, 30, 120, 25);
        account_lable.setFont(new Font("黑体", Font.BOLD, 14));
        content_pane.add(account_lable);
        
        account = new JTextField();
        account.setBounds(120, 30, 150, 25);
        account.setFont(new Font("黑体", Font.BOLD, 14));
        account.setOpaque(false);
        content_pane.add(account);
        
        pswd_lable = new JLabel("密码：");
        pswd_lable.setBounds(55, 90, 120, 25);
        pswd_lable.setFont(new Font("黑体", Font.BOLD, 14));
        content_pane.add(pswd_lable);
        
        password = new JPasswordField();
        password.setBounds(120, 90, 150, 25);
        content_pane.add(password);
        
        another_password_lable = new JLabel("再次输入：");
        another_password_lable.setBounds(45, 150, 100, 25);
        another_password_lable.setFont(new Font("黑体", Font.BOLD, 14));
        content_pane.add(another_password_lable);
        
        another_password = new JPasswordField();
        another_password.setBounds(120, 150, 150, 25);
        content_pane.add(another_password);
        
        name_lable = new JLabel("昵称：");
        name_lable.setFont(f);
        name_lable.setBounds(55, 210, 100, 25);
        content_pane.add(name_lable);
        
        name = new JTextField();
        name.setBounds(120, 210, 150, 25);
        name.setFont(f);
        content_pane.add(name);
        
        age_lable = new JLabel("年龄：");
        age_lable.setFont(f);
        age_lable.setBounds(55, 270, 100, 25);
        content_pane.add(age_lable);
        
        age = new JTextField();
        age.setBounds(120, 270, 150, 25);
        age.setFont(f);
        content_pane.add(age);
        
        phone_lable = new JLabel("手机：");
        phone_lable.setFont(f);
        phone_lable.setBounds(360, 30, 100, 25);
        content_pane.add(phone_lable);
        
        phone = new JTextField();
        phone.setBounds(425, 30, 150, 25);
        phone.setFont(f);
        content_pane.add(phone);
        
        gender_lable = new JLabel("性别：");
        gender_lable.setFont(f);
        gender_lable.setBounds(360, 90, 100, 25);
        content_pane.add(gender_lable);
        
        gender = new JComboBox<>();
        gender.setFont(f);
        gender.setBounds(425, 90, 150, 25);
        gender.setBackground(Color.WHITE);
        gender.addItem("---请选择一项---");
        gender.addItem("男");
        gender.addItem("女");
        content_pane.add(gender);
        
        
        introduction = new JTextArea("个人介绍");
        introduction.setFont(new Font("黑体", Font.PLAIN, 14));
        introduction.setBounds(360, 150, 250, 145);
        introduction.setForeground(Color.GRAY);
        introduction.setLineWrap(true);
        introduction.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(introduction.getText().equals("")) {
					introduction.setText("个人介绍");
					introduction.setFont(new Font("黑体", Font.PLAIN, 14));
					introduction.setForeground(Color.GRAY);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(introduction.getText().equals("个人介绍")) {
					introduction.setText("");
					introduction.setFont(f);
					introduction.setForeground(Color.BLACK);
				}
			}
		});
        
        content_pane.add(introduction);
        
        
        confirm_btn = new JButton();
        confirm_btn.setText("注册");
        confirm_btn.setFont(f);
        confirm_btn.setSize(150, 25);
        confirm_btn.setBounds(120, 355, 150, 25);
        content_pane.add(confirm_btn);
        
        cancle_btn = new JButton("重置");
        cancle_btn.setFont(f);
        cancle_btn.setSize(150, 25);
        cancle_btn.setBounds(getWidth() / 2 + 50, 355, 150, 25);
        content_pane.add(cancle_btn);
        
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
