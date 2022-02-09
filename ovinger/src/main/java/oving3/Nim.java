package oving3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Nim {
    
    private List<Integer> hauger = new ArrayList();

    public void removePieces(int number, int targetPile) {
        if (!(isGameOver())) {
            if ((number > 0) && (number < hauger.get(targetPile)+1) && (targetPile >= 0) && (targetPile < 3)) {
                int antall = hauger.get(targetPile);
                hauger.set(targetPile, antall -= number);
            } else {
                throw new IllegalArgumentException("Ugyldig hau og/eller antall. ");
            }
        } else {
            throw new IllegalStateException("Spillet er over, du kan ikke fjerne brikker nå.");
        }
    }


    public boolean isValidMove(int number, int targetPile) {
        if (!(isGameOver())) {
            if ((number > 0) && (number < hauger.get(targetPile)+1) && (targetPile >= 0) && (targetPile < 3)) {
                return true;
            } else { return false;}
            
        } else {
            return false;
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < 3; i++) {
            if (hauger.get(i) == 0) {
                return true;
            }
        }
        return false;
    }

    public int getPile(int targetPile) {
        if ((targetPile >= 0) && (targetPile < 3)) {
            return hauger.get(targetPile);
        }
        throw new IllegalArgumentException("Ugyldig haug med brikker");
    }

    @Override
    public String toString() {
        return "Haug 1,2 og 3 består av henholdsvis " +hauger + " brikker.";
    }


    public Nim(int pileSize) {
        for (int i = 0; i < 3; i++) {
            hauger.add(pileSize);
            
        }
    }

    public Nim() {
        for (int i = 0; i < 3; i++) {
            hauger.add(10);
        }
    }

    public static void main(String[] args) {
        Nim nim = new Nim();
        nim.removePieces(10, 3);
        System.out.println(nim.isGameOver());
        System.out.println(nim);
    }
}
