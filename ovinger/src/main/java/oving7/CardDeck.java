package oving7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardDeck extends CardContainerImpl {




	public CardDeck(int suitSize) {
		super(52);
		for (int i = 0; i < Card.SUITS.length(); i++) {
			for (int face = 1; face <= suitSize; face++) {
				Card card = new Card(Card.SUITS.charAt(i), face);
				cards.add(card);
			}
		}
	}

	

	public void deal(CardHand hand, int handSize) {
		for (int i = 0; i < handSize; i++) {
			hand.addCard(cards.remove(cards.size() - 1));
		}
	}



	public void shufflePerfectly() {
		int halfSize = cards.size() / 2;
		for (int i = 0; i < halfSize; i++) {
			Card card = cards.remove(halfSize + i);
			cards.add(i * 2 + 1, card);
		}
	}

	// methods using Predicate<Card>

	public boolean hasCard(Predicate<Card> predicate) {
		// Streams solution:
		// return this.cards.stream().anyMatch(predicate);

		// Manual solution
		for (Card card : cards) {
			if (predicate.test(card)) {
				// Avoid looping through the rest if we have found a match
				return true;
			}
		}

		return false;
	}

	public int getCardCount(Predicate<Card> predicate) {
		// Streams solution:
		// return (int) this.cards.stream().filter(predicate).count();

		// Manual solution
		int count = 0;
		for (Card card : cards) {
			if (predicate.test(card)) {
				count++;
			}
		}

		return count;
	}

	public List<Card> getCards(Predicate<Card> predicate) {
		// Streams solution:
		// return this.cards.stream().filter(predicate).collect(Collectors.toList());

		// Manual solution
		List<Card> matchingCards = new ArrayList<Card>();
		for (Card card : cards) {
			if (predicate.test(card)) {
				matchingCards.add(card);
			}
		}

		return matchingCards;
	}

	

}
