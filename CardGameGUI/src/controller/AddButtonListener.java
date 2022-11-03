package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class AddButtonListener implements ActionListener 
{
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private int idNum = 1;
	
	public AddButtonListener(MainFrame mainFrame, GameEngine gameEngine) 
	{
		this.mainFrame = mainFrame;	
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Player selectedPlayer = mainFrame.getSelectedPlayer();
		String name = JOptionPane.showInputDialog("Enter player's name.");
		if(name != null && !name.isEmpty()) 
		{
			SimplePlayer player = new SimplePlayer(String.valueOf(idNum++), name, 1000);
			mainFrame.setSelectedPlayer(player); 
			gameEngine.addPlayer(mainFrame.getSelectedPlayer());
			
			JComboBox combo = mainFrame.getCombo();
			combo.addItem(name);
			combo.setSelectedIndex(combo.getItemCount()-1);
			
			mainFrame.getBetBtn().setEnabled(true);
			mainFrame.getDealBtn().setEnabled(false);			
			mainFrame.addPlayer(player);
		}
	}
}
