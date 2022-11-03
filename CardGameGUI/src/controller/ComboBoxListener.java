package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class ComboBoxListener implements ActionListener
{
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	
	public ComboBoxListener(MainFrame mainFrame, GameEngine gameEngine) 
	{
		this.mainFrame = mainFrame;	
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String name = ((JComboBox)e.getSource()).getSelectedItem().toString();
		
		if(name.equals("House")) 
		{
			mainFrame.getBetBtn().setEnabled(false);
			mainFrame.getDealBtn().setEnabled(false);
			mainFrame.getRemoveBtn().setEnabled(false);
			mainFrame.setSelectedPlayer(null);
			mainFrame.showSelectedCardById("0");
			return;
		}
		
		for(Player simplePlayer : gameEngine.getAllPlayers()) 
		{
			if(simplePlayer.getPlayerName().equals(name)) 
			{				
				mainFrame.setSelectedPlayer(simplePlayer);
				mainFrame.getBetBtn().setEnabled(true);
				mainFrame.getRemoveBtn().setEnabled(true);
				mainFrame.showSelectedCardById(simplePlayer.getPlayerId());
				if(simplePlayer.getBet() != 0) 
					mainFrame.getDealBtn().setEnabled(true);
				else
					mainFrame.getDealBtn().setEnabled(false);
				
				break;
			}
		}		
	}
}
