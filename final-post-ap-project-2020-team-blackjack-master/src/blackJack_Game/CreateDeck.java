package blackJack_Game;

import java.io.IOException;
import java.util.ArrayList;

// creates the ArrayList deck that consists of 52 objects (cards)
// fixed outofbounds error
public class CreateDeck {
	private static ArrayList<CreateCard> deck = new ArrayList<CreateCard>();

	public CreateDeck() {

		for (int i = 0; i < 52; i++) {
			CreateCard card = new CreateCard(i);
			deck.add(card);

		}

	}

	public ArrayList<CreateCard> getDeck() {
		return deck;
	}

}
