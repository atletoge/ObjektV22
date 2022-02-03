package stateandbehavior;

public class Digit {
    int tallSystem;
    int sifferVerdi;

    public Digit(int tallSystem) {
        this.tallSystem = tallSystem;
        sifferVerdi = 0;
    }

    public int getValue() {
        return sifferVerdi;
    }

    public boolean increment() {
        if ((sifferVerdi + 1) < tallSystem) {
            sifferVerdi += 1;
            return false;
        }
        else {
            sifferVerdi = 0;
            return true;
        }
    }

    public int getBase() {
        return tallSystem;
    }

    @Override
    public String toString() {
        String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (sifferVerdi > 9) {
            int tempTall = sifferVerdi - 10;
            char tallVerdi = alfabet.charAt(tempTall);
            return String.valueOf(tallVerdi);
        }
        else {
            return String.valueOf(sifferVerdi);
        }
        
    }

    public static void main(String[] args) {
        Digit digit = new Digit(12);
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        digit.increment();
        System.out.println(digit.getValue());
        digit.increment();
        digit.increment();
        System.out.println(digit.getValue());
        digit.increment();
        System.out.println(digit);
    }
}
