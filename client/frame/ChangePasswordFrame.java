package client.frame;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class ChangePasswordFrame extends JFrame{
	public JPasswordField old_pass;
	public JPasswordField new_pass;
	public JPasswordField another_new_pass;
	public JButton confirm_btn, cancle_btn;
	
	public ChangePasswordFrame() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\\u9EC4\u4EA6\u975E\\\u8F6F\u4EF6\\For Java\\swingtest\\src\\client\\icon\\Back.jpg"));
		this.setFont(new Font("等线", Font.BOLD, 14));
		this.setResizable(false);
		this.setTitle("\u4FEE\u6539\u5BC6\u7801");
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u539F\u5BC6\u7801");
		lblNewLabel.setFont(new Font("等线", Font.BOLD, 14));
		lblNewLabel.setBounds(73, 42, 54, 15);
		this.getContentPane().add(lblNewLabel);
		
		old_pass = new JPasswordField();
		old_pass.setBounds(151, 38, 187, 21);
		this.getContentPane().add(old_pass);
		
		JLabel label = new JLabel("\u65B0\u5BC6\u7801");
		label.setFont(new Font("等线", Font.BOLD, 14));
		label.setBounds(73, 89, 54, 15);
		this.getContentPane().add(label);
		
		new_pass = new JPasswordField();
		new_pass.setBounds(151, 85, 187, 21);
		this.getContentPane().add(new_pass);
		
		JLabel label_1 = new JLabel("\u518D\u6B21\u8F93\u5165");
		label_1.setFont(new Font("等线", Font.BOLD, 14));
		label_1.setBounds(73, 138, 72, 15);
		this.getContentPane().add(label_1);
		
		another_new_pass = new JPasswordField();
		another_new_pass.setBounds(151, 134, 187, 21);
		this.getContentPane().add(another_new_pass);
		
		confirm_btn = new JButton("\u786E\u8BA4\u4FEE\u6539");
		confirm_btn.setFont(new Font("等线", Font.BOLD, 12));
		confirm_btn.setBounds(104, 194, 93, 37);
		this.getContentPane().add(confirm_btn);
		
		cancle_btn = new JButton("\u53D6\u6D88");
		cancle_btn.setFont(new Font("等线", Font.BOLD, 12));
		cancle_btn.setBounds(256, 194, 93, 37);
		this.getContentPane().add(cancle_btn);
		this.setBounds(100, 100, 458, 308);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
