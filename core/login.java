package core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import dbUtil.dbConnection;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class login extends JFrame
{
	private JPanel contentPane;
	private String loginas = "student";
	public static String passEmail;
	public static boolean division_teacher;

	public static void main(String[] args)
	{
		new login().setVisible(true);
	}

	public login()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e2)
		{
			JOptionPane.showMessageDialog(contentPane, e2);
		}
		
		setResizable(false);
		setTitle("Community Service Tracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 305);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 27));
		lblLogin.setBounds(38, 20, 201, 49);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Email:");
		lblUsername.setBounds(10, 86, 37, 14);
		contentPane.add(lblUsername);
		
		JTextField email_in = new JTextField();
		email_in.setColumns(10);
		email_in.setBorder(new LineBorder(new Color(0, 0, 0)));
		email_in.setBounds(86, 80, 181, 20);
		contentPane.add(email_in);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 111, 68, 14);
		contentPane.add(lblPassword);
		
		JPasswordField pass_in = new JPasswordField();
		pass_in.setEchoChar('*');
		pass_in.setBorder(new LineBorder(new Color(0, 0, 0)));
		pass_in.setBounds(86, 102, 181, 20);
		contentPane.add(pass_in);
		
		JRadioButton logasteacher = new JRadioButton("Teacher");
		logasteacher.setBounds(86, 150, 80, 23);
		contentPane.add(logasteacher);
		
		JRadioButton logasstudent = new JRadioButton("Student");
		logasstudent.setSelected(true);
		logasstudent.setBounds(182, 150, 85, 23);
		contentPane.add(logasstudent);
		
		JButton login = new JButton("LOGIN");
		login.setBorder(new LineBorder(new Color(0, 0, 0)));
		login.setBounds(8, 180, 259, 49);
		contentPane.add(login);
		
		JButton register_page = new JButton("Don't Have an Account?");
		register_page.setBorder(new LineBorder(new Color(0, 0, 0)));
		register_page.setBounds(10, 235, 257, 23);
		contentPane.add(register_page);
		
		JCheckBox showPass = new JCheckBox("Show Password");
		showPass.setBounds(86, 124, 181, 23);
		contentPane.add(showPass);
		
		// Radio Buttons
		logasteacher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				logasstudent.setSelected(false);
				logasteacher.setSelected(true);
				loginas = "teacher";
			}
		});
		
		logasstudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				logasteacher.setSelected(false);
				logasstudent.setSelected(true);
				loginas = "student";
			}
		});
		
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				passEmail = email_in.getText();
				
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				String LoginUserSql = "SELECT * FROM `users` WHERE `email` = ? AND `password` = ? AND `division` = ?";
				
				try
				{
					ps = dbConnection.getConnection().prepareStatement(LoginUserSql);
					ps.setString(1, email_in.getText());
					ps.setString(2, new String(pass_in.getPassword()));
					ps.setString(3, loginas);

					rs = ps.executeQuery();
					
					if (rs.next())
					{
						if (loginas == "student")
						{
							division_teacher = false;
							new mainMenu().setVisible(true);
							
							ps.close();
							rs.close();
							
							dispose();
						}
						else if (loginas == "teacher")
						{
							division_teacher = true;
							new mainMenu().setVisible(true);
							
							ps.close();
							rs.close();
							
							dispose();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "[Login] Wrong Credentials");
					}
				}
				catch (SQLException e1)
				{
					e1.getStackTrace();
				}
			}
		});
		
		register_page.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new register().setVisible(true);
				dispose();
			}
		});
		
		// Check box's
		showPass.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (showPass.isSelected())
				{
					pass_in.setEchoChar((char)0);
				}
				else
				{
					pass_in.setEchoChar('*');
				}
			}
		});
	}
}
