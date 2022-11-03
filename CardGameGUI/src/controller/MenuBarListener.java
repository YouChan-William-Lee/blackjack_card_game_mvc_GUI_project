package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MainFrame;

public class MenuBarListener implements ActionListener
{
	private MainFrame mainFrame;
	
	public MenuBarListener(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource().equals(mainFrame.getExitItem()))
			System.exit(0);
		
		if(e.getSource().equals(mainFrame.getHelpItem()))
			JOptionPane.showMessageDialog(mainFrame,
					"Bust level is 42\n\n*** WIN / LOSE / DRAW ***\n"
					+ "WIN : A player gets higher score than House\n"
					+ "LOSE : A player gets lower score than House\n"
					+ "DRAW : A player gets same score with House\n"
					+ "(Although the player gets 42 when House gets 42, it is DRAW\n\n"
					+ "*** Remove a player ***\n"
					+ "A player can be removed only if the player haven't dealt\n\n"
					+ "*** Bet *** \n"
					+ "A player can change bet only before deal",
					"*** Rule ***", 1);
    }
}