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
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import dbUtil.dbConnection;

@SuppressWarnings("serial")
public class register extends JFrame
{
	public static JPanel contentPane;
	
	private JTextField firstName, lastName, email_in, studentNum_in;
	private JPasswordField password_in;
	private JSpinner grade_in;
	private String division = "student";

	// Testing Purposes
	public static void main(String[] args)
	{
		new register().setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public register()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e2)
		{
			JOptionPane.showMessageDialog(contentPane, e2);
		}
		
		// Creation of Register Menu
		setResizable(false);
		setTitle("Community Service Tracker - Test - Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 412);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 27));
		lblNewLabel.setBounds(32, 0, 200, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("First Name:");
		lblName.setBounds(10, 67, 73, 14);
		contentPane.add(lblName);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		firstName.setBorder(new LineBorder(new Color(0, 0, 0)));
		firstName.setBounds(115, 61, 153, 20);
		contentPane.add(firstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 92, 73, 14);
		contentPane.add(lblLastName);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBorder(new LineBorder(new Color(0, 0, 0)));
		lastName.setBounds(115, 86, 153, 20);
		contentPane.add(lastName);
		
		JLabel lblAmil = new JLabel("Email:");
		lblAmil.setBounds(10, 117, 73, 14);
		contentPane.add(lblAmil);
		
		email_in = new JTextField();
		email_in.setColumns(10);
		email_in.setBorder(new LineBorder(new Color(0, 0, 0)));
		email_in.setBounds(115, 111, 153, 20);
		contentPane.add(email_in);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 141, 73, 14);
		contentPane.add(lblPassword);
		
		password_in = new JPasswordField();
		password_in.setColumns(10);
		password_in.setEchoChar('*');
		password_in.setBorder(new LineBorder(Color.BLACK));
		password_in.setBounds(115, 138, 153, 20);
		contentPane.add(password_in);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setBounds(10, 166, 102, 14);
		contentPane.add(lblStudentNumber);
		
		studentNum_in = new JTextField();
		studentNum_in.setColumns(10);
		studentNum_in.setBorder(new LineBorder(new Color(0, 0, 0)));
		studentNum_in.setBounds(115, 163, 153, 20);
		contentPane.add(studentNum_in);
		
		JLabel lblDivision = new JLabel("Division:");
		lblDivision.setBounds(10, 217, 73, 14);
		contentPane.add(lblDivision);
		
		JComboBox division_in = new JComboBox();
		division_in.setBorder(new LineBorder(Color.BLACK));
		division_in.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		division_in.setModel(new DefaultComboBoxModel(new String[] {"Teacher", "Student"}));
		division_in.setSelectedIndex(1);
		division_in.setBounds(115, 214, 153, 20);
		contentPane.add(division_in);
		
		JButton register = new JButton("REGISTER");
		register.setBorder(new LineBorder(new Color(0, 0, 0)));
		register.setFont(new Font("Tahoma", Font.BOLD, 11));
		register.setBounds(10, 274, 258, 56);
		contentPane.add(register);
		
		JButton loginPage = new JButton("Already Have an account?");
		loginPage.setBorder(new LineBorder(new Color(0, 0, 0)));
		loginPage.setBounds(10, 341, 258, 26);
		contentPane.add(loginPage);
		
		JCheckBox showPass = new JCheckBox("Show Password");
		showPass.setBounds(115, 241, 157, 23);
		contentPane.add(showPass);
		
		JLabel lblGrade = new JLabel("Grade (9-12): ");
		lblGrade.setBounds(10, 191, 102, 14);
		contentPane.add(lblGrade);
		
		grade_in = new JSpinner();
		grade_in.setModel(new SpinnerNumberModel(9, 9, 12, 1));
		grade_in.setBorder(new LineBorder(new Color(0, 0, 0)));
		grade_in.setBounds(115, 186, 153, 20);
		contentPane.add(grade_in);
		// End of creation of Register Menu
		
		// Button Fuctions
		division_in.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (division_in.getSelectedItem().equals("Teacher"))
				{
					// Only Run if the user selects the teacher option
					division = "teacher";
					studentNum_in.setEnabled(false);
					grade_in.setEnabled(false);
				}
				else if (division_in.getSelectedItem().equals("Student"))
				{
					// Only run if the user selects the student option
					division = "student";
					studentNum_in.setEnabled(true);
					grade_in.setEnabled(true);
				}
			}
		});
		
		// Shows and hides the users password in the register menu
		showPass.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (showPass.isSelected())
					password_in.setEchoChar((char)0);
				else
					password_in.setEchoChar('*');
			}
		});
		
		// Opens the login page if clicked
		loginPage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new login().setVisible(true);
				dispose();
			}
		});
		
		// Registers the user as the selected division
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String pass = new String(password_in.getPassword());
				
				if (checkEmail(email_in.getText()) && !firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !pass.isEmpty() && pass.length() >= 8)
				{
					if (division.equals("student") && !studentNum_in.toString().isEmpty() && studentNum_in.toString().length() >= 8)
					{
						registerAccount();
					}
					else if (division.equals("teacher"))
					{
						registerAccount();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, "[Register] Please Check your credentials!");
				}
			}
		});
	}
	
	private int getNextAvailableID()
	{
		int i = 0;
		
		PreparedStatement st;
		ResultSet rs;
		
		String sql = "SELECT * FROM `users` WHERE `id` = ?";
		
		while (true)
		{
			try
			{
				st = dbConnection.getConnection().prepareStatement(sql);
				st.setInt(1, i);
				rs = st.executeQuery();
				
				if (!rs.next())
				{
					st.close();
					rs.close();
					
					return i;
				}
				
				++i;
				
				st.close();
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return 0;
			}
		}
	}
	
	private boolean checkEmail(String email)
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex); 
		
		if (email.isEmpty())
			return false;
		else if (pat.matcher(email).matches())
		{
			PreparedStatement ps;
			ResultSet rs;
			
			String sql = "SELECT * FROM `users` WHERE `email` = ?";
			
			try
			{
				ps = dbConnection.getConnection().prepareStatement(sql);
				ps.setString(1, email);
				rs = ps.executeQuery();
				
				if (rs.next())
				{
					JOptionPane.showMessageDialog(contentPane, "Email already exists.");
					ps.close();
					rs.close();
					return false;
				}
				else
				{
					ps.close();
					rs.close();
					return true;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	private void registerAccount()
	{
		Random ran = new Random();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		
		String verificationCode = "";
		
		for (int i = 0; i <= 12; i++)
		{
			verificationCode = verificationCode + characters.charAt(ran.nextInt(characters.length()));
		}
		
		try
		{
			emailVerification.JavaMailUtil.sendMail(email_in.getText(), verificationCode);
			
			// For testing purposes
			System.out.println(verificationCode);
			
			String verify = JOptionPane.showInputDialog(contentPane, "Enter your Verification Code: ", "Verfy Account - Community Service Tracker", 3);
			
			if (verify.equals(verificationCode))
			{
				PreparedStatement ps;
				String RegisterUserSql = "INSERT INTO 'users' ('id', 'name', 'email', 'password', 'studentNum', 'grade', 'division') VALUES (?, ?, ?, ?, ?, ?, ?)";
				
				try
				{	
					ps = dbConnection.getConnection().prepareStatement(RegisterUserSql);
					ps.setInt(1, getNextAvailableID());
					ps.setString(2, lastName.getText() + ", " + firstName.getText());
					ps.setString(3, email_in.getText());
					ps.setString(4, new String(password_in.getPassword()));
					
					try
					{
						if (division.equals("teacher"))
						{
							ps.setInt(5, 0);
							ps.setInt(6, 0);
						}
						else if (division.equals("student"))
						{
							ps.setInt(5, Integer.parseInt(studentNum_in.getText()));
							ps.setInt(6, Integer.parseInt(grade_in.getValue().toString()));
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
						JOptionPane.showMessageDialog(contentPane, "[Register] There was an error when saving your Information to the database.");
						return;
					}
					
					ps.setString(7, division);
					
					if (ps.executeUpdate() != 0)
					{
						JOptionPane.showMessageDialog(contentPane, "[Register] Account Successfully Created!");
						
						ps.close();
						
						new login().setVisible(true);
						dispose();
					}
					else
					{
						ps.close();
					}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(contentPane, "[Register] Your verification code is invalid!");
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		verificationCode = null;
	}
}
