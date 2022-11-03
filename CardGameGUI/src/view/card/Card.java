package view.card;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.interfaces.PlayingCard;

public class Card extends JPanel 
{
	private final Color backgroundColor;
	private PlayingCard playingCard;

	public Card(boolean isLast) 
	{
		this.backgroundColor = isLast ? Color.LIGHT_GRAY : Color.WHITE;

	}

	public void setCard(PlayingCard playingCard) 
	{
		this.playingCard = playingCard;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if (playingCard == null)
			return;

		String imgName = playingCard.getSuit().toString() + ".PNG";
		int textMargin = 30;
		int width = getWidth();
		int height = getHeight();
		int centerX = width / 2;
		int centerY = height / 2;
		String cardAlphabet = getValueCharacter();
		int ratio = (width / 10) + (height / 10) * 2;
		int ratioFontSize = ratio / 9;

		String path = System.getProperty("user.dir") + "\\img\\" + imgName;

		// draw backgroud
		g.setColor(backgroundColor);
		g.fillRoundRect(0, 0 + ratio, width, height - ratio * 2, 20, 20);

		// put the suit image in the middle
		try {
			BufferedImage image = ImageIO.read(new File(path));
			int imageSize = ratioFontSize * 2;
			g.drawImage(image, width / 2 - imageSize / 2, height / 2 - imageSize / 2, imageSize, imageSize, this);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (playingCard.getSuit().toString().matches("HEARTS|DIAMONDS"))
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLACK);

		// draw the value on left top and right bottom
		g.setFont(new Font("Calibri", Font.BOLD, 10 + ratioFontSize));
		g.drawString(cardAlphabet, 20, 10 + textMargin + ratio);
		g.drawString(cardAlphabet, width - textMargin - 10, height - textMargin - ratio);
	}

	private String getValueCharacter() 
	{
		String val = "8";
		switch (playingCard.getValue().toString()) 
		{
			case "EIGHT": val = "8"; break;
			case "NINE": val = "9";	break;
			case "TEN": val = "T"; break;
			case "JACK": val = "J"; break;
			case "QUEEN": val = "Q"; break;
			case "KING": val = "K"; break;
			case "ACE": val = "A"; break;
		}
		return val;
	}
}