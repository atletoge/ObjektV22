package oving7;

import java.util.ArrayList;
import java.util.Iterator;

public class CardHand extends CardContainerImpl {

	// array to hold Card objects, filled in the constructor
	private ArrayList<Card> cards;

	public CardHand(int max) {
		super(max);
	}

	public void addCard(Card card) {
		super.addCard(card);
	}


	public Card play(int i) {
		return cards.remove(i);
	}

	// Iterable<Card> in CardContainer<Card>

}
