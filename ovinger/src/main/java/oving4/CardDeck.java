package oving4;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {
    
    public List<Card> kortstokk = new ArrayList();


    public CardDeck(){

    }

    public CardDeck(int n) {
        List<Character> gyldige = Arrays.asList('S','H','D','C');
        if ((n > -1) && (n< 14)) {
            for (int i = 0; i < gyldige.size(); i++) {
                for (int j = 1; j < n+1; j++) {
                    Card card = new Card(gyldige.get(i), j);
                    kortstokk.add(card);
                }
            }
        
        } else {
            throw new IllegalArgumentException("Kan ikke lage kortstokk med "+n*4 +" kort.");
        }
        //System.out.println(kortstokk);
    }

    public int getCardCount() {
        return this.kortstokk.size();
    }

    public Card getCard(int n) {
        if ((n < kortstokk.size()) && (n> -1)) {
            return kortstokk.get(n);
        } else {
            throw new IllegalArgumentException("Det er ikke et kort på plass "+n+" i bunken");
        }
    }


    public void shufflePerfectly() {
        List <Card> topp = kortstokk.subList(0, getCardCount()/2);
        List <Card> bunn = kortstokk.subList(getCardCount()/2, getCardCount());
        Card[] array1 = new Card[topp.size()];
        Card[] array2 = new Card[topp.size()];
        topp.toArray(array1);
        bunn.toArray(array2);
        // Kødda mye her, hadde rydda opp i dette om det var noe annet enn en øving :)) 
        for (int j = 0; j < bunn.size(); j++) {
            kortstokk.set(j*2, array1[j]);
            kortstokk.set(j*2+1, array2[j]);
        }
        System.out.println(this.kortstokk);
    }

    public void deal(CardHand cardHand, int n) {
        for (int i = 0; i < n; i++) {
            cardHand.addCard(this.kortstokk.get(this.kortstokk.size()-1));
            this.kortstokk.remove(this.kortstokk.size()-1);
        }
    }


    public static void main(String[] args) {
        CardDeck cardDeck = new CardDeck(0);
        //System.out.println(cardDeck.getCard(39));
        cardDeck.shufflePerfectly();
    }

}
