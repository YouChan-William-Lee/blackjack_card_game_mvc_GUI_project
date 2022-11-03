package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class BetButtonListener implements ActionListener 
{
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	
	public BetButtonListener(MainFrame mainFrame, GameEngine gameEngine) 
	{
		this.mainFrame = mainFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Player selectedPlayer = mainFrame.getSelectedPlayer();
		if(selectedPlayer == null)
			return;
		try 
		{
			Object bat = JOptionPane.showInputDialog(
	                  null,
	                  "Enter Bet for " + selectedPlayer.getPlayerName(),
	                  "Place Bet",
	                  JOptionPane.INFORMATION_MESSAGE, null, null, selectedPlayer.getBet());
			
			if(bat == null) 
			{
				if(selectedPlayer.getBet() == 0)
					mainFrame.getDealBtn().setEnabled(false);
				return;
			}
			
			selectedPlayer.setBet(Integer.valueOf(bat.toString()));
			
			if(selectedPlayer.getBet() == 0) 
			{
				mainFrame.getDealBtn().setEnabled(false);
				return;
			}
			
			gameEngine.placeBet(selectedPlayer, selectedPlayer.getBet());
			mainFrame.updateTableValue(selectedPlayer.getPlayerId(), 3, String.valueOf(selectedPlayer.getBet()));
			mainFrame.getDealBtn().setEnabled(true);
		} 
		catch (NumberFormatException ignored) 
		{
		}
	}

}
