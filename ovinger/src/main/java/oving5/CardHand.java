package oving5;

import java.util.ArrayList;
import java.util.List;

public class CardHand  extends CardDeck {


    public CardHand() {
        super(0);
    }

    //List<Card> cardList = new ArrayList();

    public CardHand(int n) {
        super(n);
        //TODO Auto-generated constructor stub
    }
    
    public void addCard(Card card) {
        kortstokk.add(card);
    }

    public Card play(int n) {
        if (n < (kortstokk.size())) {
            Card kort = kortstokk.get(n);
            kortstokk.remove(n);
            return kort;
        }
        throw new IllegalArgumentException("Kortet på plassnr "+n+" eksisterer ikke");
    }

    
    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck(3);
        CardHand cardHand = new CardHand();
        cardHand.deal(cardHand, 3);
    }
}
