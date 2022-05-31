package oving7;

import java.util.ArrayList;
import java.util.Iterator;

public class CardContainerImpl implements CardContainer {

    protected ArrayList<Card> cards;
    private int maxCardCount;

    public CardContainerImpl(int max) {
        cards = new ArrayList<Card>();
        this.maxCardCount = max;
    }

    public int getMaxCardCount() {
        return maxCardCount;
    }

    public int getCardCount() {
		return cards.size();
	}

	public Card getCard(int i) {
		if (i < 0 || i >= getCardCount()) {
			throw new IllegalArgumentException(
					String.format("%s is an illegal card index, when the size of the deck is %s", i, getCardCount()));
		}
		return cards.get(i);
	}
    

    @Override
    public String toString() {
		return "[Deck " + cards.toString().substring(1);
	}

    @Override
    public Iterator<Card> iterator() {
		return cards.iterator();
	}

    protected void addCard(Card card) {
		if(getCardCount() == getMaxCardCount()) {
            throw new IllegalStateException("Kan ikke legge til flere kort");
        }
        cards.add(card);
	}
}
