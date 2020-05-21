package core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dbUtil.dbConnection;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class mainMenu extends JFrame
{
	private JPanel contentPane;
	private JTextField fName_in, lName_in, studentNum_in, grade_in, title_in, description_in, timeSpent_in, getClassCode;
	private JButton classCode;
	private JLabel have_community_award, have_service_award, have_achievement_award, display_hours;
	private int hrs, mins, reportGenHours, reportGenMins;
	private static DefaultTableModel service;
	private JTable displayServiceHrs;
	private String[] timeSpentSplit;
	private ArrayList<String> studentID, studentNAME;
	private String myClassCode;
	private reportDisplay report = new reportDisplay();
	
	public static int Userid;

	public static void main(String[] args)
	{
		new mainMenu().setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public mainMenu()
	{
		// Sets the look to the native operations systems
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e2)
		{
			JOptionPane.showMessageDialog(contentPane, e2);
		}
		
		// Creates main Menu
		setResizable(false);
		setTitle("Community Service Tracker - Menu");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 540, 347);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane menu_pane = new JTabbedPane(JTabbedPane.TOP);
		menu_pane.setBounds(0, 0, 540, 313);
		contentPane.add(menu_pane);
		
		JPanel profile_menu = new JPanel();
		profile_menu.setBorder(null);
		menu_pane.addTab("Profile", null, profile_menu, null);
		profile_menu.setLayout(null);
		
		JPanel edit_profileInfo = new JPanel();
		edit_profileInfo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Edit Profile", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		edit_profileInfo.setBounds(10, 11, 254, 261);
		profile_menu.add(edit_profileInfo);
		edit_profileInfo.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(10, 30, 109, 14);
		edit_profileInfo.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 55, 109, 14);
		edit_profileInfo.add(lblLastName);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setBounds(10, 80, 109, 14);
		edit_profileInfo.add(lblStudentNumber);
		
		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(10, 102, 109, 14);
		edit_profileInfo.add(lblGrade);
		
		JLabel lblClasscode = new JLabel("Class Code:");
		lblClasscode.setBounds(10, 164, 109, 14);
		edit_profileInfo.add(lblClasscode);
		
		fName_in = new JTextField();
		fName_in.setDisabledTextColor(Color.GRAY);
		fName_in.setEnabled(false);
		fName_in.setColumns(10);
		fName_in.setBorder(new LineBorder(Color.BLACK));
		fName_in.setBounds(129, 27, 115, 17);
		edit_profileInfo.add(fName_in);
		
		lName_in = new JTextField();
		lName_in.setDisabledTextColor(Color.GRAY);
		lName_in.setEnabled(false);
		lName_in.setColumns(10);
		lName_in.setBorder(new LineBorder(Color.BLACK));
		lName_in.setBounds(129, 52, 115, 17);
		edit_profileInfo.add(lName_in);
		
		studentNum_in = new JTextField();
		studentNum_in.setDisabledTextColor(Color.GRAY);
		studentNum_in.setEnabled(false);
		studentNum_in.setColumns(10);
		studentNum_in.setBorder(new LineBorder(Color.BLACK));
		studentNum_in.setBounds(129, 77, 115, 17);
		edit_profileInfo.add(studentNum_in);
		
		grade_in = new JTextField();
		grade_in.setDisabledTextColor(Color.GRAY);
		grade_in.setEnabled(false);
		grade_in.setColumns(10);
		grade_in.setBorder(new LineBorder(Color.BLACK));
		grade_in.setBounds(129, 102, 115, 17);
		edit_profileInfo.add(grade_in);
		
		getClassCode = new JTextField();
		getClassCode.setDisabledTextColor(Color.GRAY);
		getClassCode.setColumns(10);
		getClassCode.setBorder(new LineBorder(Color.BLACK));
		getClassCode.setBounds(89, 163, 155, 17);
		edit_profileInfo.add(getClassCode);
		
		JRadioButton edit_profile = new JRadioButton("Edit");
		edit_profile.setBounds(10, 128, 71, 23);
		edit_profileInfo.add(edit_profile);
		
		JButton update_profile = new JButton("Update");
		update_profile.setBorder(new LineBorder(new Color(0, 0, 0)));
		update_profile.setBounds(89, 128, 157, 23);
		edit_profileInfo.add(update_profile);
		
		JButton log_out = new JButton("Log Out");
		log_out.setBorder(new LineBorder(new Color(0, 0, 0)));
		log_out.setBounds(10, 226, 236, 23);
		edit_profileInfo.add(log_out);
		
		classCode = new JButton("Generate Class Code");
		classCode.setBorder(new LineBorder(new Color(0, 0, 0)));
		classCode.setBounds(10, 190, 236, 23);
		edit_profileInfo.add(classCode);
		
		JPanel award_category = new JPanel();
		award_category.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Community Service Award Category", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		award_category.setBounds(274, 11, 245, 246);
		profile_menu.add(award_category);
		award_category.setLayout(null);
		
		have_community_award = new JLabel("Community Award");
		have_community_award.setBorder(new LineBorder(Color.RED));
		have_community_award.setHorizontalAlignment(SwingConstants.CENTER);
		have_community_award.setBounds(10, 29, 225, 20);
		award_category.add(have_community_award);
		
		have_service_award = new JLabel("Service Award");
		have_service_award.setHorizontalAlignment(SwingConstants.CENTER);
		have_service_award.setBorder(new LineBorder(Color.RED));
		have_service_award.setBounds(10, 60, 225, 20);
		award_category.add(have_service_award);
		
		have_achievement_award = new JLabel("Achievement Award");
		have_achievement_award.setBorder(new LineBorder(Color.RED));
		have_achievement_award.setHorizontalAlignment(SwingConstants.CENTER);
		have_achievement_award.setBounds(10, 91, 225, 20);
		award_category.add(have_achievement_award);
		
		JTextArea txtrThisWillShow = new JTextArea();
		txtrThisWillShow.setOpaque(false);
		txtrThisWillShow.setEditable(false);
		txtrThisWillShow.setForeground(Color.BLACK);
		txtrThisWillShow.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtrThisWillShow.setLineWrap(true);
		txtrThisWillShow.setWrapStyleWord(true);
		txtrThisWillShow.setText("This will show the awards that you have received through your community service hours. Depending on the amount of hours you have total it will give you an award from above also the red border will turn green when you own that award. You need 50 hours for the community award, 200 hours for the service award, and 500 hours for the achievement award.");
		txtrThisWillShow.setBounds(10, 135, 225, 100);
		award_category.add(txtrThisWillShow);
		
		display_hours = new JLabel("Total Hours: ");
		display_hours.setBounds(10, 114, 225, 20);
		award_category.add(display_hours);
		
		JPanel serviceHrManager = new JPanel();
		serviceHrManager.setBorder(null);
		serviceHrManager.setLayout(null);
		
		displayServiceHrs = new JTable();
		displayServiceHrs.setBorder(null);
		displayServiceHrs.setBackground(Color.WHITE);
		displayServiceHrs.setForeground(Color.BLACK);
		displayServiceHrs.setBounds(282, 101, 297, 186);
		
		Object[] columns = {"Title", "Description", "Time Spent"};
		service = new DefaultTableModel();
		service.setColumnIdentifiers(columns);
		displayServiceHrs.setModel(service);
		
		JScrollPane table_scroll = new JScrollPane(displayServiceHrs);
		table_scroll.setViewportBorder(null);
		table_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		table_scroll.setBounds(10, 11, 298, 246);
		serviceHrManager.add(table_scroll);
		
		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setBounds(318, 12, 72, 14);
		serviceHrManager.add(lblTitle);
		
		title_in = new JTextField();
		title_in.setBorder(new LineBorder(Color.BLACK));
		title_in.setBounds(394, 9, 125, 20);
		serviceHrManager.add(title_in);
		title_in.setColumns(10);
		
		JLabel lblDescrition = new JLabel("Description:");
		lblDescrition.setBounds(318, 37, 72, 14);
		serviceHrManager.add(lblDescrition);
		
		description_in = new JTextField();
		description_in.setColumns(10);
		description_in.setBorder(new LineBorder(Color.BLACK));
		description_in.setBounds(394, 34, 125, 20);
		serviceHrManager.add(description_in);
		
		JLabel lblTimeSpent = new JLabel("Time Spent:");
		lblTimeSpent.setBounds(318, 65, 72, 14);
		serviceHrManager.add(lblTimeSpent);
		
		timeSpent_in = new JTextField();
		timeSpent_in.setColumns(10);
		timeSpent_in.setBorder(new LineBorder(Color.BLACK));
		timeSpent_in.setBounds(394, 62, 125, 20);
		serviceHrManager.add(timeSpent_in);
		
		// Initialize preview Text for time spent format information
		timeSpent_in.setText("hrs:mins");
		timeSpent_in.setForeground(Color.GRAY);
		
		timeSpent_in.addFocusListener(new FocusListener()
		{
			// Get Rid of preview text
			@Override
			public void focusGained(FocusEvent arg0)
			{
				switch (timeSpent_in.getText())
				{
					case "hrs:mins":
						timeSpent_in.setText("");
						break;
				}
				timeSpent_in.setForeground(Color.BLACK);
			}

			// Add back preview text
			@Override
			public void focusLost(FocusEvent arg0)
			{
				if (timeSpent_in.getText().isEmpty())
				{
					timeSpent_in.setText("hrs:mins");
					timeSpent_in.setForeground(Color.GRAY);
				}
				else
				{
					timeSpent_in.setForeground(Color.BLACK);
				}
			}
		});
		// End Preview Text for time spent format information
		
		JButton new_Entry = new JButton("New");
		new_Entry.setBorder(new LineBorder(new Color(0, 0, 0)));
		new_Entry.setBounds(318, 90, 201, 23);
		serviceHrManager.add(new_Entry);
		
		JButton update_Entry = new JButton("Update");
		update_Entry.setBorder(new LineBorder(new Color(0, 0, 0)));
		update_Entry.setBounds(318, 124, 201, 23);
		serviceHrManager.add(update_Entry);
		
		JButton remove_Entry = new JButton("Remove");
		remove_Entry.setBorder(new LineBorder(new Color(0, 0, 0)));
		remove_Entry.setBounds(318, 158, 201, 23);
		serviceHrManager.add(remove_Entry);
		
		JButton clear_Fields = new JButton("Clear Fields");
		clear_Fields.setBorder(new LineBorder(new Color(0, 0, 0)));
		clear_Fields.setBounds(318, 192, 201, 23);
		serviceHrManager.add(clear_Fields);
		
		JButton save_Entries = new JButton("Save");
		save_Entries.setBorder(new LineBorder(new Color(0, 0, 0)));
		save_Entries.setBounds(318, 226, 201, 23);
		serviceHrManager.add(save_Entries);
		
		JPanel generate_menu = new JPanel();
		generate_menu.setBorder(null);
		generate_menu.setLayout(null);
		
		JPanel print_options = new JPanel();
		print_options.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		print_options.setBounds(12, 12, 242, 260);
		generate_menu.add(print_options);
		print_options.setLayout(null);
		
		JComboBox time_report = new JComboBox();
		time_report.setModel(new DefaultComboBoxModel(new String[] {"Month Report", "Week Report"}));
		time_report.setSelectedIndex(0);
		time_report.setBounds(12, 87, 217, 26);
		print_options.add(time_report);
		
		JRadioButton hrProgReport = new JRadioButton("Hour Progression Report");
		hrProgReport.setToolTipText("Shows students progress for the amount of time selected.");
		hrProgReport.setSelected(true);
		hrProgReport.setBounds(12, 24, 217, 25);
		print_options.add(hrProgReport);
		
		JRadioButton awardCatReport = new JRadioButton("Award Categories Report");
		awardCatReport.setToolTipText("Shows the students in the class that are elegable for certain awards.");
		awardCatReport.setBounds(12, 53, 217, 26);
		print_options.add(awardCatReport);
		
		JButton genReport = new JButton("Generate Report");
		genReport.setBounds(12, 125, 218, 26);
		print_options.add(genReport);
		// End Creation of Main Menu
		
		// Prepare information and menu
		refreshData("profile_info");
		refreshData("servicehr_manage");
		
		if (login.division_teacher)
		{
			setSize(282, 347);
			getClassCode.setEditable(false);
			classCode.setText("Generate Class Code");
			menu_pane.addTab("Generate", null, generate_menu, null);
			award_category.setVisible(false);
		}
		else
		{
			setSize(540, 347);
			getClassCode.setEditable(true);
			menu_pane.addTab("Service Hour Manager", null, serviceHrManager, null);
			award_category.setVisible(true);
		}
		
		// Click Events for class code stuff
		classCode.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (login.division_teacher)
				{
					classCode.setEnabled(false);
					
					Random ran = new Random();
					String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
					
					String class_code = "";
					
					for (int i = 0; i <= 8; i++)
					{
						class_code = class_code + characters.charAt(ran.nextInt(characters.length()));
					}
					
					PreparedStatement ps = null;
					String sql = "UPDATE 'users' SET `class_code` = ? WHERE `id` = " + Userid;
					
					try
					{
						ps = dbConnection.getConnection().prepareStatement(sql);
						ps.setString(1, class_code);
						ps.executeUpdate();
						
						ps.close();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
					
					getClassCode.setText(class_code);
				}
				else
				{
					if (classCode.getText().equals("Join Class") && !getClassCode.getText().isEmpty())
					{	
						PreparedStatement ps = null;
						ResultSet rs = null;
						
						String sql = "SELECT * FROM `users` WHERE `class_code` = ? AND `division` = ?";
						
						try
						{
							ps = dbConnection.getConnection().prepareStatement(sql);
							ps.setString(1, getClassCode.getText());
							ps.setString(2, "teacher");
							rs = ps.executeQuery();
							
							if (rs.next())
							{
								JOptionPane.showMessageDialog(contentPane, "You Have joined " + rs.getString("name").split(", ")[1] + "'s Classroom!");
								
								ps.close();
								rs.close();
								
								sql = "UPDATE 'users' SET `class_code` = ? WHERE `id` = " + Userid;
								
								ps = dbConnection.getConnection().prepareStatement(sql);
								ps.setString(1, getClassCode.getText());
								ps.executeUpdate();
									
								ps.close();
							}
							else
							{
								getClassCode.setText("");
								JOptionPane.showMessageDialog(contentPane, "Class code doesn't exist!");
							}
							
							ps.close();
							rs.close();
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						classCode.setText("Leave Class");
					}
					else if (classCode.getText().equals("Leave Class"))
					{
						PreparedStatement ps = null;
						ResultSet rs = null;
						
						String sql = "UPDATE 'users' SET `class_code` = ? WHERE `id` = " + Userid;
						
						try
						{
							ps = dbConnection.getConnection().prepareStatement(sql);
							ps.setString(1, "");
							ps.executeUpdate();
							
							ps.close();
							
							sql = "SELECT * FROM `users` WHERE `class_code` = ? AND `division` = ?";
							ps = dbConnection.getConnection().prepareStatement(sql);
							ps.setString(1, getClassCode.getText());
							ps.setString(2, "teacher");
							rs = ps.executeQuery();
							
							if (rs.next())
							{
								JOptionPane.showMessageDialog(contentPane, "You Have successfully left " + rs.getString("name").split(", ")[1] + "'s Classroom!");
							}
							
							ps.close();
							rs.close();
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						
						classCode.setText("Join Class");
						getClassCode.setText("");
					}
				}
			}
		});
		
		
		// Click Events for generate page
		hrProgReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				hrProgReport.setSelected(true);
				awardCatReport.setSelected(false);
				
				time_report.setEnabled(true);
			}
		});
		
		awardCatReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				hrProgReport.setSelected(false);
				awardCatReport.setSelected(true);
				
				time_report.setEnabled(false);
			}
		});
		
		genReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (awardCatReport.isSelected())
				{
					/*
					 *  Run Code to get the students that are eligible for an award
					 *  
					 *  Open reportDisplay and load the info to it
					 */
					
					// Need to add code for the students award stuff
					report.dispose();
					report.displayStudents.setText("Students That have enough hours for an award:\n");
					
					// Code that gets the information
					PreparedStatement ps = null;
					ResultSet rs = null;
					
					String sql = "SELECT * FROM `users` WHERE `class_code` = ? AND `division` = ?";
					
					try
					{
						ps = dbConnection.getConnection().prepareStatement(sql);
						ps.setString(1, myClassCode);
						ps.setString(2, "student");
						rs = ps.executeQuery();
						
						while (rs.next())
						{
							// Code that is ran for ever student that has the same class code as the teacher
							if (Integer.parseInt(rs.getString("total_cs_hrs").split(":")[0]) >= 500)
							{
								report.displayStudents.setText(report.displayStudents.getText() + "\n" + rs.getString("name").split(", ")[1] + " is eligable for the Community Award, Service Award, and Achievement Award!");
							}
							else if (Integer.parseInt(rs.getString("total_cs_hrs").split(":")[0]) >= 200)
							{
								report.displayStudents.setText(report.displayStudents.getText() + "\n" + rs.getString("name").split(", ")[1] + " is eligable for the Community Award and Service Award!");
							}
							else if (Integer.parseInt(rs.getString("total_cs_hrs").split(":")[0]) >= 50)
							{
								report.displayStudents.setText(report.displayStudents.getText() + "\n" + rs.getString("name").split(", ")[1] + " is eligable for the Community Award!");
							}
						}
					}
					catch (SQLException se)
					{
						se.printStackTrace();
					}
					
					report.setVisible(true);
					
				}
				else if (hrProgReport.isSelected() && time_report.getSelectedItem().equals("Month Report"))
				{
					/*
					 *  Run Code to get the students' progress for the past month
					 *  
					 *  Open reportDisplay and load the info to it
					 */
					
					generateReport(30);
					
				}
				else if (hrProgReport.isSelected() && time_report.getSelectedItem().equals("Week Report"))
				{
					/*
					 * Run Code to get the students' progress for the past week
					 * 
					 * Open reportDisplay and load the info to it
					 */
					
					generateReport(7);
				}
			}
		});

		// Ask before closing program
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				if (JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to close this page?", "Community Service Tracker", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION)
				{
					System.exit(0);
				}
			}
		});
		
		// Profile Editing
		edit_profile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (edit_profile.isSelected())
				{
					fName_in.setEnabled(true);
					lName_in.setEnabled(true);
					studentNum_in.setEnabled(true);
					grade_in.setEnabled(true);
				}
				else
				{
					fName_in.setEnabled(false);
					lName_in.setEnabled(false);
					studentNum_in.setEnabled(false);
					grade_in.setEnabled(false);
				}
			}
		});
		
		// Saves all of your profile information when clicking the update button
		update_profile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PreparedStatement ps = null;
				String sql = "UPDATE 'users' SET `name` = ?, `studentNum` = ?, `grade` = ? WHERE `id` = " + Userid;
				
				try
				{
					ps = dbConnection.getConnection().prepareStatement(sql);
					ps.setString(1, lName_in.getText() + ", " + fName_in.getText());
					ps.setString(2, studentNum_in.getText());
					ps.setString(3, grade_in.getText());
					ps.executeUpdate();
					
					ps.close();
					
					edit_profile.setSelected(false);
					fName_in.setEnabled(false);
					lName_in.setEnabled(false);
					studentNum_in.setEnabled(false);
					grade_in.setEnabled(false);
					
					JOptionPane.showMessageDialog(contentPane, "[Database Entry] Updated Profile Info Successfully");
				}
				catch (SQLException ext)
				{
					ext.printStackTrace();
				}
			}
		});
	
		// Log out of profile and go back to the login page
		log_out.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new login().setVisible(true);
				Userid = 0;
				login.division_teacher = false;
				dispose();
			}
		});
		
		// Manage service hours page events
		displayServiceHrs.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int selectedRow = displayServiceHrs.getSelectedRow();
				
				title_in.setText(service.getValueAt(selectedRow, 0).toString());
				description_in.setText(service.getValueAt(selectedRow, 1).toString());
				timeSpent_in.setText(service.getValueAt(selectedRow, 2).toString());
				
				timeSpent_in.setForeground(Color.BLACK); // Undos the preview text color
			}
		});
		
		new_Entry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String[] newInfo = {title_in.getText(), description_in.getText(), timeSpent_in.getText()};
				service.addRow(newInfo);
				
				title_in.setText("");
				description_in.setText("");
				timeSpent_in.setText("");
			}
		});
		
		update_Entry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int selectedRow = displayServiceHrs.getSelectedRow();
				
				service.setValueAt(title_in.getText(), selectedRow, 0);
				service.setValueAt(description_in.getText(), selectedRow, 1);
				service.setValueAt(timeSpent_in.getText(), selectedRow, 2);
				
				title_in.setText("");
				description_in.setText("");
				timeSpent_in.setText("");
			}
		});
		
		remove_Entry.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int selectedRow = displayServiceHrs.getSelectedRow();
				
				if (selectedRow >= 0)
				{
					service.removeRow(selectedRow);
				}
				
				title_in.setText("");
				description_in.setText("");
				timeSpent_in.setText("");
			}
		});
		
		clear_Fields.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				title_in.setText("");
				description_in.setText("");
				timeSpent_in.setText("");
			}
		});
		
		save_Entries.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Remove all the entries of the user id in the database
				PreparedStatement ps = null;
				String sql = "DELETE FROM `cshrs` WHERE `id` = ?";
				try
				{
					ps = dbConnection.getConnection().prepareStatement(sql);
					ps.setInt(1, Userid);
					ps.executeUpdate();
					
					ps.close();
				}
				catch (SQLException se)
				{
					se.getStackTrace();
				}
				
				// Add all of the information from the table to the database with the same user id
				for (int i = 0; i < service.getRowCount(); i++)
				{
					PreparedStatement prep = null;
					String sqlite = "INSERT INTO 'cshrs' ('id', 'title', 'description', 'timeSpent', 'timeAdded') VALUES (?, ?, ?, ?, ?)";
					try
					{
						prep = dbConnection.getConnection().prepareStatement(sqlite);
						prep.setInt(1, Userid);
						prep.setString(2, service.getValueAt(i, 0).toString());
						prep.setString(3, service.getValueAt(i, 1).toString());
						prep.setString(4, service.getValueAt(i, 2).toString());
						
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy,dd,MM");
					    Date date = new Date();
						prep.setString(5, formatter.format(date));
						
						prep.execute();
						
						prep.close();
					}
					catch (SQLException e1)
					{
						e1.printStackTrace();
					}
				}
				
				refreshData("profile_info");
				refreshData("servicehr_manage");
				JOptionPane.showMessageDialog(contentPane, "[Service Manager] Saved Sucessfully!");
			}
		});
	}
	
	private void refreshData(String updateWhat)
	{
		// Update Profile page
		if (updateWhat.equals("profile_info"))
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM `users` WHERE `email` = ?";
			
			try
			{
				// Initilizses all your profile info
				ps = dbConnection.getConnection().prepareStatement(sql);
				ps.setString(1, login.passEmail);
				rs = ps.executeQuery();
				
				if (rs.next())
				{
					Userid = rs.getInt("id");
					
					String[] name = rs.getString("name").split(", ");
					lName_in.setText(name[0]);
					fName_in.setText(name[1]);
					
					studentNum_in.setText(rs.getString("studentNum"));
					
					grade_in.setText(rs.getString("grade"));
					
					String formathrs = rs.getString("total_cs_hrs");
					display_hours.setText("Total Hours: " + formathrs);
					getClassCode.setText(rs.getString("class_code"));
					
					
					//fix below
					if (!login.division_teacher && !getClassCode.getText().equals(""))
					{
						classCode.setText("Leave Class");
					}
					else if (!login.division_teacher && getClassCode.getText().equals(""))
					{
						classCode.setText("Join Class");
					}
					
					if (login.division_teacher)
					{
						if (!getClassCode.getText().equals(" "))
							classCode.setEnabled(false);
					}
					
					myClassCode = rs.getString("class_code");
				}
				rs.close();
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		// Update service hour manager information
		else if (updateWhat.equals("servicehr_manage"))
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "SELECT * FROM `cshrs` WHERE `id` = ?";
			
			try
			{
				ps = dbConnection.getConnection().prepareStatement(sql);
				ps.setInt(1, Userid);
				rs = ps.executeQuery();
				
				// Removes the all rows from the table
				if (service.getRowCount() > 0)
				{
					int rows = service.getRowCount();
					for (int i = 0; i < rows; i++)
					{
						service.removeRow(service.getRowCount() - 1);
					}
				}
				
				// Adds all nessasary rows back to the table and removes the ones that were not included in the database
				hrs = 0;
				mins = 0;
				
				while (rs.next())
				{
					service.addRow(new String[]{rs.getString("title"), rs.getString("description"), rs.getString("timeSpent")});
					
					timeSpentSplit = rs.getString("timeSpent").split(":");
					hrs = hrs + Integer.parseInt(timeSpentSplit[0]);
					mins = mins + Integer.parseInt(timeSpentSplit[1]);
					
					// Makes it to were if you have does over 60 mins then it will add hours according to the mins
					while (mins >= 60)
					{
						mins = mins - 60;
						++hrs;
					}
					
					timeSpentSplit = new String[0];
					display_hours.setText("Total Hours: " + hrs + "." + mins);
					
					// Checks to see if you have earned an award
					if (hrs >= 50)
					{
						have_community_award.setBorder(new LineBorder(Color.GREEN));
						if (hrs >= 200)
						{	
							have_service_award.setBorder(new LineBorder(Color.GREEN));
							if (hrs >= 500)
							{
								have_achievement_award.setBorder(new LineBorder(Color.GREEN));
							}
						}
					}
					
					if (hrs < 500)
					{
						have_achievement_award.setBorder(new LineBorder(Color.RED));
						if (hrs < 200)
						{
							have_service_award.setBorder(new LineBorder(Color.RED));
							if (hrs < 50)
							{
								have_community_award.setBorder(new LineBorder(Color.RED));
							}
						}
					}
				}
				
				ps.close();
				rs.close();

				// Makes sure the times are all synced up
				String sql1 = "UPDATE `users` SET `total_cs_hrs` = ? WHERE `id` = " + Userid;
				ps = dbConnection.getConnection().prepareStatement(sql1);
				
				ps.setString(1, hrs + ":" + mins);
				
				ps.executeUpdate();
				ps.close();
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public long daysBetween(Date one, Date two)
	{
		long diff = (one.getTime() - two.getTime()) / 86400000; // 86400000 is the number of milliseconds in a day
		
		System.out.println(diff);
		
		return diff;//Math.abs(diff);
	};
	
	private void generateReport(int progressTime)
	{
		report.dispose();
		report.displayStudents.setText("Students progress for the past " + progressTime + " days:\n\n");
		
		try
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			String sql = "SELECT * FROM `users` WHERE `class_code` = ? AND `division` = ?";
			
			ps = dbConnection.getConnection().prepareStatement(sql);
			ps.setString(1, myClassCode);
			ps.setString(2, "student");
			rs = ps.executeQuery();
			
			studentID = new ArrayList<String>();
			studentNAME = new ArrayList<String>();
			
			while (rs.next())
			{
				studentID.add(String.valueOf(rs.getInt("id")));
				studentNAME.add(rs.getString("name").split(", ")[1]);
			}
			
			ps.close();
			rs.close();
			
			if (progressTime == 30)
			{
				JOptionPane.showMessageDialog(contentPane, "[Report Generator] Generating Month Progression Report This may take a second!");
			}
			else if (progressTime == 7)
			{
				JOptionPane.showMessageDialog(contentPane, "[Report Generator] Generating Week Progression Report This may take a second!");
			}
			
			int i;
			
			for (i = 0; i < studentID.size(); i++)
			{
				System.out.println("id" + i);
				
				PreparedStatement ps1 = null;
				ResultSet rs1 = null;
				
				String sql1 = "SELECT * FROM `cshrs` WHERE `id` = ?";
				
				ps1 = dbConnection.getConnection().prepareStatement(sql1);
				ps1.setString(1, studentID.get(i));
				rs1 = ps1.executeQuery();
				
				while (rs1.next())
				{
					Date today = new Date();
					
					Calendar thatCalender = Calendar.getInstance();
					thatCalender.set(Integer.parseInt(rs1.getString("timeAdded").split(",")[0]), Integer.parseInt(rs1.getString("timeAdded").split(",")[2]) - 1, Integer.parseInt(rs1.getString("timeAdded").split(",")[1]));
					Date thatDay = thatCalender.getTime();
					
					long days = new mainMenu().daysBetween(today, thatDay);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy,dd,MM");
					String todaysDate = sdf.format(today);
					String thatDate = sdf.format(thatDay);
					
					System.out.println(days + " days from today's date of " + todaysDate + " until " + thatDate);
					
					if (days <= 30)
					{
						reportGenHours = reportGenHours + Integer.parseInt(rs1.getString("timeSpent").split(":")[0]);
						reportGenMins = reportGenMins + Integer.parseInt(rs1.getString("timeSpent").split(":")[1]);
						
						while (reportGenMins >= 60)
						{
							reportGenHours += 1;
							reportGenMins -= 60;
						}
					}
				}				
				
				if (progressTime == 30)
				{
					System.out.println(studentNAME.get(i) + " has completed " + reportGenHours + " hours and " + reportGenMins + " minutes of Community Service this month!");
					report.displayStudents.setText(report.displayStudents.getText() + "\n~ " + studentNAME.get(i) + " has completed " + reportGenHours + " hours and " + reportGenMins + " minutes of Community Service this month!");
				}
				else if (progressTime == 7)
				{
					System.out.println(studentNAME.get(i) + " has completed " + reportGenHours + " hours and " + reportGenMins + " minutes of Community Service this week!");
					report.displayStudents.setText(report.displayStudents.getText() + "\n~ " + studentNAME.get(i) + " has completed " + reportGenHours + " hours and " + reportGenMins + " minutes of Community Service this week!");
				}
				
				reportGenHours = 0;
				reportGenMins = 0;
				
				ps.close();
				rs.close();
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		
		report.setVisible(true);
	}
}
