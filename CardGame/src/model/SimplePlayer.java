package model;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class SimplePlayer implements Player 
{
	private String id;
	private String name;
	private int point;
	private int bet;
	private int result;
	
	public SimplePlayer(String id, String name, int point) 
	{
		this.id = id;
		this.name = name;
		this.point = point;
	}

	@Override
	public String getPlayerName() 
	{
		return name;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.name = playerName;
	}

	@Override
	public int getPoints() 
	{
		return point;
	}

	@Override
	public void setPoints(int points) 
	{
		// TODO Auto-generated method stub
		this.point = points;
	}

	@Override
	public String getPlayerId() 
	{
		return id;
	}

	@Override
	public boolean setBet(int bet) 
	{
		if (point < bet)
			return false;
		this.bet = bet;
		return true;
	}

	@Override
	public int getBet() 
	{
		return bet;
	}

	@Override
	public void resetBet() 
	{
		bet = 0;
	}

	@Override
	public int getResult() 
	{
		return result;
	}

	@Override
	public void setResult(int result) 
	{
		this.result = result;
	}

	@Override
	public boolean equals(Player player) 
	{
		return player == null ? false : this.id.equals(player.getPlayerId());
	}
	
	@Override 
	public boolean equals(Object player)
	{
		if (player == null || !(player instanceof Player))
			return false;
		
		return this.id.equals(((Player)player).getPlayerId());
	}
	
	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
	
	@Override
	public int compareTo(Player player) 
	{
		return this.getPlayerId().compareTo(player.getPlayerId());
	}
	
	@Override
	public String toString()
	{
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d\n", 
				getPlayerId(), getPlayerName(), getBet(), getPoints(), getResult());
	}
}
