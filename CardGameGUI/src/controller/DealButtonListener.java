package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;

public class DealButtonListener implements ActionListener
{
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	
	public DealButtonListener(MainFrame mainFrame, GameEngine gameEngine) 
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
		
		mainFrame.removeCardPanel(selectedPlayer.getPlayerId());
		selectedPlayer.setResult(0);		
		gameEngine.dealPlayer(selectedPlayer, 100);
		mainFrame.setDealCnt(mainFrame.getDealCnt()+1);
		
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