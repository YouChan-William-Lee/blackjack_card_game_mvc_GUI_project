package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBarPanel extends JPanel
{
	private JLabel statusLabel = new JLabel("status", JLabel.CENTER);
	private final String Version = "12";
	
	public StatusBarPanel()
	{
		setLayout(new BorderLayout());
	
		statusLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setStatus(Version);
		
		add(statusLabel);
	}
	
	public void setStatus(String s)
	{
		statusLabel.setText(String.format("Card Game Version %s", s));
	}
}
