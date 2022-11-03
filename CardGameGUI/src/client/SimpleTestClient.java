package client;

import java.util.Deque;
import java.util.logging.Level;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import validate.Validator;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.MainFrame;

public class SimpleTestClient 
{
	public static void main(String args[])
	{
		final GameEngine gameEngine = new GameEngineImpl();
	    MainFrame mainFrame = new MainFrame(gameEngine);
	      
	    Validator.validate(true);

	    gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
	    gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainFrame, gameEngine));
	}

	@SuppressWarnings("unused")
	private static void printCards(Deque<PlayingCard> deck) 
	{
		for (PlayingCard card : deck)
			GameEngineCallbackImpl.logger.log(Level.INFO, card.toString());
	}
}
