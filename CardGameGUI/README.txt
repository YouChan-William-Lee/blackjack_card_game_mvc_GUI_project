README

--- Run

You can simply run SimpleTestClient.java
Or please write code like below in main method

public static void main(String args[])
{
	final GameEngine gameEngine = new GameEngineImpl();
	MainFrame mainFrame = new MainFrame(gameEngine);
		      
	Validator.validate(false);
	
	gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
	gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainFrame, gameEngine));
}

--- Assumption

You only can remove a player when the player haven't dealt
