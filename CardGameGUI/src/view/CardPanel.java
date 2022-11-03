package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.interfaces.PlayingCard;
import view.card.Card;

public class CardPanel extends JPanel 
{
	private int cardIndex;
	private Card card[];
	
	public CardPanel() 
	{
		cardIndex = 0;
		card = new Card[6];
		setLayout(new GridLayout(1, 6, 10, 0));
		
		for (int i = 0; i < card.length - 2; i++) 
		{
			card[i] = new Card(false);
			add(card[i]);
		}
		
		card[4] = new Card(true);
		add(card[4]);
		card[5] = new Card(false);
		add(card[5]);
	}

	public void setCardWithIndex(PlayingCard playingCardImpl, int index) 
	{
		if (index < 0 || index > 6)
			return;

		card[index].setCard(playingCardImpl);
	}

	public void clearCards() 
	{
		for (int i = 0; i < card.length; i++) 
			card[i].setCard(null);
	}

	public void appendCard(PlayingCard card) 
	{
		if (cardIndex < 0 || cardIndex > 4)
			cardIndex = 0;

		this.card[cardIndex++].setCard(card);		
	}
}
