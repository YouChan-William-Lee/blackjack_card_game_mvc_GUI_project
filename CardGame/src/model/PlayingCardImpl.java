package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard 
{
	private Suit suit;
	private Value value;
	
	public PlayingCardImpl(Suit suit, Value value) 
	{
		this.suit = suit;
		this.value = value;
	}
	
	@Override
	public Suit getSuit() 
	{
		return suit;
	}

	@Override
	public Value getValue() 
	{
		return value;
	}

	@Override
	public int getScore() 
	{
		switch (value)
		{
			case EIGHT: return 8;
			case NINE: return 9;
			case ACE: return 11;
			default: return 10; //TEN, JACK, QUEEN, KING
		}
	}

	@Override
	public String toString()
	{
		return String.format("Suit: %s, Value: %s, Score: %d", formatting(getSuit().toString()),
				formatting(getValue().toString()), getScore());
	}
	
	@Override
	public boolean equals(PlayingCard card) 
	{
		return card == null ? false : this.suit == card.getSuit() && this.value == card.getValue();
	}
	
	@Override
	public boolean equals(Object card)
	{
		if (card == null || !(card instanceof PlayingCard))
			return false;
		
		return this.suit == ((PlayingCard)card).getSuit() && this.value == ((PlayingCard)card).getValue();
	}
	
	@Override
	public int hashCode()
	{
		return suit.hashCode() + value.hashCode();
	} 
	
	private String formatting(String value) // Human readable String ex) DIAMONDS -> Diamonds 
	{
		return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
	}
}
