package oving4;

import java.util.ArrayList;
import java.util.List;

public class CardHand  extends CardDeck {


    public CardHand() {
        
    }

    List<Card> kortstokk = new ArrayList();

    public CardHand(int n) {
        super(n);
        //TODO Auto-generated constructor stub
    }
    
    public void addCard(Card card) {
        this.kortstokk.add(card);
    }

    public Card play(int n) {
        if (n < (this.kortstokk.size()-1)) {
            Card kort = this.kortstokk.get(n);
            this.kortstokk.remove(n);
            return kort;
        }
        throw new IllegalArgumentException("Kortet på plassnr "+n+" eksisterer ikke");
    }

}
