package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import view.MainFrame;

public class RemoveButtonListener implements ActionListener
{
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	
	public RemoveButtonListener(MainFrame mainFrame, GameEngine gameEngine) 
	{
		this.mainFrame = mainFrame;	
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Player selectedPlayer = mainFrame.getSelectedPlayer();
		JComboBox combo = mainFrame.getCombo();
		
		if(selectedPlayer == null)
			return;
		
		String name = mainFrame.getCombo().getSelectedItem().toString();
		
		if(combo.getItemCount() == 1 && name.equals("House")) 
		{
			mainFrame.getDealBtn().setEnabled(false);
			mainFrame.getBetBtn().setEnabled(false);
			mainFrame.setSelectedPlayer(null);
			return;
		}
		
		if(combo.getItemCount() != 1) 
		{
			combo.removeItemAt(combo.getSelectedIndex());
			gameEngine.removePlayer(selectedPlayer);
			mainFrame.removeRowById(selectedPlayer.getPlayerId());
			combo.setSelectedIndex(0);
			name = combo.getItemAt(0).toString();	
			
			if(name == null || name.isEmpty())
				return;
			
			for(Player simplePlayer : gameEngine.getAllPlayers()) 
			{
				if(simplePlayer.getPlayerName().equals(name)) 
				{
					selectedPlayer = simplePlayer;
					break;
				}
			}
		}
		
		if(mainFrame.getCombo().getItemCount() -1 == mainFrame.getDealCnt()) 
		{
			mainFrame.setDealCnt(0);
			JOptionPane.showMessageDialog(null, "Press OK to Continue", "House Ready", JOptionPane.PLAIN_MESSAGE);
			mainFrame.getCombo().setSelectedIndex(0);
			gameEngine.dealHouse(10);
		}
		
		mainFrame.getBetBtn().setEnabled(false);
		mainFrame.getDealBtn().setEnabled(false);
	}

}
