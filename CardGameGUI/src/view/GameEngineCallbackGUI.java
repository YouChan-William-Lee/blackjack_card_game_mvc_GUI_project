package view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback 
{
	public static final Logger logger = Logger.getLogger(GameEngineCallbackImpl.class.getName());
	private GameEngine gameEngine;
	private MainFrame mainFrame;
	private int cardIndex = 0;
	private ArrayList<PlayingCard> cards;
	
	public GameEngineCallbackGUI(MainFrame mainFrame, GameEngine gameEngine) 
	{
		this.mainFrame = mainFrame;
		this.gameEngine = gameEngine;
		cards = new ArrayList<PlayingCard>();
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				mainFrame.addCard(player.getPlayerId(), card);
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				mainFrame.addCard(player.getPlayerId(), card);
			}
		});
	}

	@Override
	public void result(Player player, int result, GameEngine engine) 
	{
		mainFrame.updateTableValue(player.getPlayerId(), 4, String.valueOf(result));
		
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				mainFrame.addCard("0", card);
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				mainFrame.addCard("0", card);
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				String Result="";
				if (result > 42) 
				{
					for (Player player : engine.getAllPlayers()) 
					{
						if (player.getResult() > 42)
							Result = "LOSE";
						else 
							Result = "WIN";
						
						mainFrame.updateTableValue(player.getPlayerId(), 5, Result);
						logger.log(Level.INFO, String.format("Player %s .. %s", player.getPlayerId(), Result));
					}
				} 
				else 
				{
					for (Player player : engine.getAllPlayers()) 
					{
						if (player.getResult() == result)
							Result = "DRAW";
						else if (player.getResult() > result) 
						{
							if (player.getResult() > 42)
								Result = "LOSE";
							else
								Result = "WIN";
						} 
						else
							Result = "LOSE";
						
						mainFrame.updateTableValue(player.getPlayerId(), 5, Result);
						logger.log(Level.INFO, String.format("Player %s .. %s", player.getPlayerId(), Result));
					}
				}
				
				ArrayList<Player> players= new ArrayList<Player>(engine.getAllPlayers());
				
				for(int i = 0; i < players.size(); i++) 
				{
					Player player = players.get(i);
					mainFrame.updateTableValue(player.getPlayerId(), 2, String.valueOf(player.getPoints()));
					if (player.getPoints() < 1) 
					{
						mainFrame.getCombo().removeItem(player.getPlayerName());
						mainFrame.removeRowById(player.getPlayerId());
						gameEngine.removePlayer(player);
					}
				}
				
				if (JOptionPane.showConfirmDialog(null, "Play Again?", "House Result:" + result, JOptionPane.YES_NO_OPTION) == 1)
					System.exit(0);
			}
		});
	}
}