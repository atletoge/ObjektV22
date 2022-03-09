package oving5;

import java.util.Iterator;
import java.util.List;

public interface CardContainer extends Iterable<Card> {
    public int getCardCount();

    public Card getCard(int n);

}
