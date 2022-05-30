package oving5;

import java.lang.ProcessBuilder.Redirect.Type;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {
    
    private char type;
    private int tall;



    public Card(char type, int tall) {
        if (validateSuit(type)) {
            this.type = type;
        }
        if (validateFace(tall)) {
            this.tall = tall;
        }
    }


    private boolean validateSuit(char type) {
        List<Character> gyldige = Arrays.asList('S','D','H','C');
        if (gyldige.contains(type)) {
            return true;
        } else {
            throw new IllegalArgumentException(type+ " er ikke en gyldig farge.");
        }
    }

    private boolean validateFace(int tall) {
        if ((tall < 14) && (tall > 0)){
            return true;
        } else {
            throw new IllegalArgumentException(tall+" er ikke et gyldig tall på kortet. ");
        }
    }

    public char getSuit() {
        return type;
    }

    public int getFace() {
        return tall;
    }

    @Override
    public String toString() {
        return Character.toString(type)+Integer.toString(tall);
    }

    public static void main(String[] args) {
        Card card = new Card('S',1);
        System.out.println(card);
    }


    
}
