package blackJack_Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
// Creates new card
public class CreateCard {
	
	private String[] cardOptions = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	private String[] suiteOptions = {"Clubs","Spades","Hearts","Diamond"};
	private String card;
	private String suite;
	private int cardValue;
	private int cardIndex;
	
	//Constructor for the card object
	public CreateCard(int index) {
		this.card = cardOptions[index%13];
		this.suite = suiteOptions[index/13];
		if (index%13 == 0) {
			this.cardValue = 11;
		}else if (index%13>9) {
			this.cardValue = 10;
		}else {
			this.cardValue = index%13+1;
		}
		this.cardIndex = index;
		
		
	}
	public String getCardNum() {
		return this.card;
	}
	public String getSuite() {
		return this.suite;
	}
	public int getCardValue() {
		return this.cardValue;
	}
	
	//returns the ImageIcon or CardImage
	public ImageIcon getCardImage() throws IOException {
		BufferedImage DeckOfCards;
		BufferedImage card;
		int xCord = 1;
		int yCord = 1;
		int cardWidth = 83;
		int cardHeight = 112;
		
		DeckOfCards = ImageIO.read(new File("deckofcards.png"));
		
		for (int i = 0; i<this.cardIndex%13; i++) {
			xCord = xCord+cardWidth+4;
			
		}
		if (this.cardIndex%13>7) {
			xCord+=1;
		}
		if (this.cardIndex%13>4) {
			xCord+=1;
		}
		if (this.cardIndex%13>2) {
			xCord +=1;
		}
		for (int k = 0; k<this.cardIndex/13;k++) {
			yCord = yCord+cardHeight+5;
		}
		if (this.cardIndex/13>2) {
			yCord+=1;
		}
		
		card = DeckOfCards.getSubimage(xCord,yCord,cardWidth,cardHeight);
		ImageIcon imageOfCard = new ImageIcon(card);
		return imageOfCard;
		
	}
}
