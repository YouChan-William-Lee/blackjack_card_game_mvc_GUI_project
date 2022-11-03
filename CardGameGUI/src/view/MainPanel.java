package view;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JPanel;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class MainPanel extends JPanel 
{
	private SummaryPanel summaryPanel;
	private CardPanel cardPanel;

	private HashMap<String, CardPanel> cardDeck;

	MainPanel() 
	{
		this.cardDeck = new HashMap<String, CardPanel>();
		this.summaryPanel = new SummaryPanel();
		this.cardPanel = new CardPanel();

		setLayout(new BorderLayout());

		add(this.summaryPanel, BorderLayout.NORTH);
		add(this.cardPanel, BorderLayout.CENTER);
	}

	public void addPlayer(Player player) 
	{
		summaryPanel.addPlayer(player);
	}

	public void removeRowById(String id) 
	{
		summaryPanel.removeRowById(id);
	}

	public void updateTableValue(String id, int colIndex, String value) 
	{
		summaryPanel.updateTableValue(id, colIndex, value);
	}

	public void showSelectedCardById(String userId) 
	{
		if (cardDeck.containsKey(userId)) 
			updateCardPanel(userId);
		else 
		{
			if (cardPanel != null)
				this.remove(cardPanel);
			cardPanel = null;
		}
		revalidate();
		repaint();
	}

	public void addCard(String userId, PlayingCard card) 
	{
		if (cardDeck.containsKey(userId) == false) 
		{
			CardPanel cardPanel = new CardPanel();
			cardPanel.appendCard(card);
			cardDeck.put(userId, cardPanel);
			updateCardPanel(userId);
		} 
		else 
		{
			CardPanel cardPanel = cardDeck.get(userId);
			cardPanel.appendCard(card);
		}
	}

	private void updateCardPanel(String userId) 
	{
		if (cardPanel != null)
			this.remove(cardPanel);
		
		cardPanel = cardDeck.get(userId);
		this.add(cardPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	public void removeCardPanel(String userId) 
	{
		if (cardPanel != null)
			this.remove(cardPanel);
		cardPanel = null;
		cardDeck.remove(userId);
	}

	public void clearCards() 
	{
		cardPanel.clearCards();
	}
}