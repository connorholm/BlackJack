package blackJack_Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI extends JFrame{
	CreateDeck deck = new CreateDeck();
	private ArrayList<CreateCard> playersCards = new ArrayList<CreateCard>();
	private ArrayList<CreateCard> dealersCards = new ArrayList<CreateCard>();
	
	//Button Statuses
	private int cardsTaken = 0;
	private boolean hitStatus = true;
	private int hitCounter = 0;
	private boolean stayStatus = false;
	private boolean isGameDone = false;
	private boolean yesStatus = false;
	private boolean enterStatus = true;
	private boolean canBet = true;
	private int counter = 0;
	
	
	//Scores
	private int playerTotal = 0;
	private int dealerTotal = 0;
	private boolean didPlayerWin;
	private int money =100;
	private String betDisplay = "TextBox Display";
	private int bet;
	
	int aW = 1300;
	int aH = 800;
	
	int cardYgap = 30;
	int cardXgap = 20;
	int cardWidth = 127;
	int cardHeight = 200;
	int fullLength = cardWidth*7+cardXgap*8;
	
	BufferedImage br;
	
	Color colorBackground = new Color(39,119,20);
	Color colorLines = new Color(0,0,0);
	
	Font fontButton = new Font("Times New Roman",Font.PLAIN, 30);
	Font fontTitle = new Font("Times New Roman", Font.BOLD, 40);
	Font fontHeader = new Font("Times New Roman", Font.BOLD, 25);
	Font fontText = new Font("Times New Roman", Font.PLAIN, 30);
	Font fontSmallText = new Font("Times New Roman", Font.PLAIN, 15);
	
	JButton bHit = new JButton();
	JButton bStay = new JButton();
	JButton bYes = new JButton();
	JButton bNo = new JButton();
	JButton bEnter = new JButton();
	
	JLabel dealersTotal = new JLabel();
	
	
	
	public GUI() throws IOException {
		//Deals out cards
		dealCards();
		/*
		for (int i =0; i<2; i++) {
			int random = (int)(Math.random()*(52-cardsTaken));
			playersCards.add(deck.getDeck().get(random));
			deck.getDeck().remove(random);
			cardsTaken++;
			random = (int)(Math.random()*(52-cardsTaken));
			dealersCards.add(deck.getDeck().get(random));
			deck.getDeck().remove(random);
			cardsTaken++;
			
		}
		if (dealerTotal<17&&stayStatus == true) {
			int random = (int)(Math.random()*(52-cardsTaken));
			dealersCards.add(deck.getDeck().get(random));
			deck.getDeck().remove(random);
		}
		//Finds Players total initially
		for (CreateCard card: playersCards) {
			playerTotal += card.getCardValue();
		}
		for (CreateCard card: dealersCards) {
			dealerTotal+= card.getCardValue();
		}
		*/
		this.setSize(aW,aH);
		this.setTitle("BlackJack Game");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board board = new Board();
		this.setContentPane(board);
		this.setLayout(null);
		
		//Adds 4 buttons
		ActHit aHit = new ActHit();
		bHit.addActionListener(aHit);
		bHit.setBounds(fullLength+30,(cardYgap*4)+(cardHeight*2)+45-100,200,100);
		bHit.setText("Hit");
		bHit.setFont(fontButton);
		board.add(bHit);
		
		ActStay aStay = new ActStay();
		bStay.addActionListener(aStay);
		bStay.setBounds(fullLength+30,(cardYgap*4)+(cardHeight*2)+60,200,100);
		bStay.setText("Stay");
		bStay.setFont(fontButton);
		board.add(bStay);
		
		ActYes aYes = new ActYes();
		bYes.addActionListener(aYes);
		bYes.setBounds(fullLength/4-250,(cardYgap*4)+(cardHeight*2)+45+60,200,50);
		bYes.setText("Yes");
		bYes.setFont(fontButton);
		board.add(bYes);
		
		ActNo aNo = new ActNo();
		bNo.addActionListener(aNo);
		bNo.setBounds(fullLength/4+50,(cardYgap*4)+(cardHeight*2)+45+60,200,50);
		bNo.setText("No");
		bNo.setFont(fontButton);
		board.add(bNo);
		
		ActEnter aEnter = new ActEnter();
		bEnter.addActionListener(aEnter);
		bEnter.setBounds(3*fullLength/4,(cardYgap*4)+(cardHeight*2)+45+30+30,100, 20);
		bEnter.setText("Enter");
		bEnter.setFont(fontButton);
		board.add(bEnter);
		
		
		TextField textbox = new TextField();
		textbox.setBounds(3*fullLength/4-250,(cardYgap*4)+(cardHeight*2)+45+30+55,200,20);
		textbox.addTextListener(new Text(counter));
		
		
		board.add(textbox);
		
		
		
		
		//Makes it so you can't hit after staying
		
		
		/*
		dealersTotal.setText("Dealer's Total: ");
		dealersTotal.setForeground(Color.white);
		dealersTotal.setLocation(0,0);
		board.add(dealersTotal);
		
		br = ImageIO.read(new File("deckofcards.jpg"));
		ImageIcon i = new ImageIcon("deckofcards.jpg");
		JLabel l = new JLabel();
		l.setIcon(i);
		this.getContentPane().add(l);
		*/
		
		/*
		if (canBet){
			String temp = textbox.getText();
			bet = Integer.parseInt(temp);
			System.out.println(temp);
		}
		*/	
				
			
		
			
		
		
	}
	
	public class Board extends JPanel {
		public void paintComponent(Graphics g) {
		
			g.setColor(colorBackground);
			g.fillRect(0,0,aW,aH);
			
			//Creating Grids and Layout of the User Interface
			g.setColor(colorLines);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			
			//Draws the Grids
			g.drawLine(0,45,1300,45);
			g.drawLine(1049,45,1049,800);
			g.drawLine(0,(cardYgap*2)+cardHeight+45,fullLength,cardYgap*2+cardHeight+45);
			g.drawLine(0,(cardYgap*4)+(cardHeight*2)+45,fullLength,(cardYgap*4)+(cardHeight*2)+45);
			
			//Adds the Text
			g.setFont(fontTitle);
			g.drawString("BlackJack",600,35);
			g.setFont(fontHeader);
			g.drawString("Player's Hand:",fullLength+30,130);
			g.drawString("Dealer's Hand: ",fullLength+30,390);
			g.setFont(fontText);
			g.drawString(playerTotal+"/21",fullLength+30,160);
			if (playerTotal>21) {
				g.drawString("Player Busted",fullLength+30,190);
			}
			if (isGameDone) {
				g.drawString(dealerTotal+"/21",fullLength+30,420);
			}
			if (dealerTotal>21) {
				g.drawString("Dealer Busted",fullLength+30,450);
			}
			if (isGameDone) {
				g.drawString("Do You Want To Play Again?",fullLength/4-180,(cardYgap*4)+(cardHeight*2)+45+30);
			}
			if (!(isGameDone)) {
				g.drawString("Game in Progress...",fullLength/4-110,(cardYgap*4)+(cardHeight*2)+45+30);
			}
			//Winning text
			if (isGameDone) {
				if (dealerTotal<playerTotal) {
					didPlayerWin = true;
				}else {
					didPlayerWin = false;
				}
				if (dealerTotal>21) {
					didPlayerWin = true;
				}
				if (playerTotal>21) {
					didPlayerWin = false;
				}
				
				if (didPlayerWin) {
					money += bet;
					bet = 0;
					g.drawString("You Won!!!",fullLength+30,(cardYgap*2)+cardHeight+45);
				}else {
					g.drawString("You Lost :(",fullLength+30,(cardYgap*2)+cardHeight+45);
					money-=bet;
					bet = 0;
					if (money<0) {
						money = 0;
					}
				}
				
			}
			g.drawString("Money:$ "+money,3*fullLength/4-250,(cardYgap*4)+(cardHeight*2)+45+30);
			g.drawString("Bet:$ "+bet,3*fullLength/4+50,(cardYgap*4)+(cardHeight*2)+45+30);
			g.drawLine(fullLength/2,(cardYgap*4)+(cardHeight*2)+45,fullLength/2,800);
			
			g.fillRect(fullLength/4-250,(cardYgap*4)+(cardHeight*2)+45+60,500,50);
			
			g.setFont(fontSmallText);
			if (playerTotal<21 || stayStatus == false ||hitCounter == 0) {
				g.drawString("Enter Your Bet Now",3*fullLength/4-250,(cardYgap*4)+(cardHeight*2)+45+50);
				g.drawString(betDisplay,3*fullLength/4-250,(cardYgap*4)+(cardHeight*2)+45+70);
			}else {
				g.drawString("Bet Next Round",3*fullLength/4-250,(cardYgap*4)+(cardHeight*2)+45+50);
				canBet = false;
			}
			
			g.setFont(fontText);
			
			/*
			ImageIcon i = new ImageIcon("deckofcards.png");
			g.drawImage(i.getImage(), 0,120,400,400,null);
			*/
			//Adds cards to the screen
			for (int i = 0; i<playersCards.size(); i++) {
				ImageIcon i2 = null;
			
				try {
					i2 = playersCards.get(i).getCardImage();
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (yesStatus == false) {
					g.drawImage(i2.getImage(),cardXgap*(i+1)+cardWidth*i,45+cardYgap,cardWidth,cardHeight,null);
				}
				
			}
			
			for (int i = 0; i<dealersCards.size();i++) {
				/*
				if (dealersCards.size() == 2 && stayStatus == false) {
					ImageIcon backofCard = new ImageIcon("backofcard.png");
					g.drawImage(backofCard.getImage(),cardXgap*(1)+cardWidth*0,45+cardYgap*3+cardHeight,cardWidth,cardHeight,null);
					g.drawImage(backofCard.getImage(),cardXgap*(2)+cardWidth*1,45+cardYgap*3+cardHeight,cardWidth,cardHeight,null);
				}
				*/
				if (!(isGameDone)) {
					ImageIcon backofCard = new ImageIcon("backofcard.png");
					g.drawImage(backofCard.getImage(),cardXgap*(1)+cardWidth*0,45+cardYgap*3+cardHeight,cardWidth,cardHeight,null);
					g.drawImage(backofCard.getImage(),cardXgap*(2)+cardWidth*1,45+cardYgap*3+cardHeight,cardWidth,cardHeight,null);
				}
				
				if (isGameDone) {
					
						ImageIcon i2 = null;
					
						try {
							i2 = dealersCards.get(i).getCardImage();
						} catch (IOException e) {
						// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
						g.drawImage(i2.getImage(),cardXgap*(i+1)+cardWidth*i,45+cardYgap*3+cardHeight,cardWidth,cardHeight,null);
					
				}
			}
			
			
			
			
		}
	}
	public class ActHit implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			hitCounter++;
			if (hitStatus == true) {
			int random = (int)(Math.random()*(52-cardsTaken));
			cardsTaken++;
			playersCards.add(deck.getDeck().get(random));
			playerTotal += deck.getDeck().get(random).getCardValue();
			deck.getDeck().remove(random);
			}
			if (playerTotal>21) {
				hitStatus = false;
				
				fillDealer();
				isGameDone = true;
				
			}
			
		}
	}
	public class ActStay implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			hitStatus = false;
			stayStatus = true;
			fillDealer();
			isGameDone = true;
			
			
		}
	
	}
	public class ActYes implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Reseting everything for a new game
			
			//Reseting All Variables
			cardsTaken = 0;
			hitStatus = true;
			stayStatus = false;
			isGameDone = false;
			yesStatus = true;
			canBet = true;
			enterStatus = true;
			hitCounter = 0;
			
			playerTotal = 0;
			dealerTotal = 0;
			bet = 0;
			
			//Reseting the ArrayList of cards from the dealer and player
			

			for (CreateCard card: playersCards) {
				deck.getDeck().add(card);
			}
			playersCards.clear();
			for (CreateCard card: dealersCards) {
				deck.getDeck().add(card);
			}
			dealersCards.clear();
			
			yesStatus =false;
			dealCards();
		}
	}
	public class ActNo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			isGameDone = true;
			System.out.println("Program is done");
			System.exit(1);
			
			
		}
	}
	public class ActEnter implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (enterStatus) {
				canBet = false;
				bet = Integer.parseInt(betDisplay);
				 
				if (bet<0) {
				bet = 0;
				}
				if (bet>money) {
				bet = money;
				}
				enterStatus = false;
			}
			
		}
	}
	public class Text implements TextListener{
		public Text(int num) {
			counter++;
		}
		public void textValueChanged(TextEvent e) {
			if (canBet){
				TextComponent tc = (TextComponent) e.getSource();
				betDisplay = tc.getText();
				//bet = Integer.parseInt(tc.getText());
			}
			
			
			
			
		}
	}
	//Fills the dealers hands with cards
	public void fillDealer() {
		if (playerTotal<22) {
			while(dealerTotal<17) {
			int random = (int)(Math.random()*(52-cardsTaken));
			dealersCards.add(deck.getDeck().get(random));
			cardsTaken++;
			dealerTotal+= deck.getDeck().get(random).getCardValue();
			deck.getDeck().remove(random);
			stayStatus = true;
			}
		}
		
	}
	//Deals the players with cards
	public void dealCards() {
		for (int i =0; i<2; i++) {
			int random = (int)(Math.random()*(52-cardsTaken));
			playersCards.add(deck.getDeck().get(random));
			deck.getDeck().remove(random);
			cardsTaken++;
			random = (int)(Math.random()*(52-cardsTaken));
			dealersCards.add(deck.getDeck().get(random));
			deck.getDeck().remove(random);
			cardsTaken++;
			
		}
		if (dealerTotal<17&&stayStatus == true) {
			int random = (int)(Math.random()*(52-cardsTaken));
			dealersCards.add(deck.getDeck().get(random));
			deck.getDeck().remove(random);
		}
		//Finds Players total initially
		for (CreateCard card: playersCards) {
			playerTotal += card.getCardValue();
		}
		for (CreateCard card: dealersCards) {
			dealerTotal+= card.getCardValue();
		}
	}
}
