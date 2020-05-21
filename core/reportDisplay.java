package core;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class reportDisplay extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	public JTextArea displayStudents;

	public static void main(String[] args)
	{
		new reportDisplay().setVisible(true);
	}

	public reportDisplay()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e2)
		{
			JOptionPane.showMessageDialog(contentPanel, e2);
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Generated Menu");
		setResizable(false);
		setBounds(100, 100, 500, 720);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(12, 12, 466, 620);
		contentPanel.add(scrollPane);
		
		displayStudents = new JTextArea();
		displayStudents.setText("TESETSET SET\nTESTS");
		displayStudents.setOpaque(false);
		displayStudents.setEditable(false);
		displayStudents.setLineWrap(true);
		displayStudents.setWrapStyleWord(true);
		scrollPane.setViewportView(displayStudents);
		
		JButton saveReport = new JButton("Save");
		saveReport.setBounds(256, 644, 105, 27);
		contentPanel.add(saveReport);
		
		JButton exitReport = new JButton("Exit");
		exitReport.setBounds(373, 644, 105, 27);
		contentPanel.add(exitReport);
		
		exitReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		
		saveReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Saves the infro from the text document to a save file you can choose
				try
				{
					JFileChooser fc = new JFileChooser();
					fc.showSaveDialog(displayStudents);
					
					File f = fc.getSelectedFile();
					
					FileWriter fw = new FileWriter(f);
					
					String tx = displayStudents.getText();
					fw.write(tx);
					fw.close();
					
					JOptionPane.showMessageDialog(contentPanel, "[Report Generator] Saved Report Successfully");
				}
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(contentPanel, "[Report Generator] Error saving the report please try again!");
					e.printStackTrace();
				}
			}
		});
	}
}
