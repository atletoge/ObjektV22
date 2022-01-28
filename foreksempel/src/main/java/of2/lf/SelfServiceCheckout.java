package of2.lf;

import java.util.Arrays;
import java.util.Collection;

public class SelfServiceCheckout {

    // Konstanter - del 1
    public static final Collection<String> days = Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun");

    // Felter / Tilstand
    private String day;
    private String phoneNumber;

    // Konstruktør - del 3
    public SelfServiceCheckout(String day) {
        validateDay(day);
        this.day = day;
    }

    // Registrer medlems-ID (telefonnummer)
    public void registerPhoneNumber(String phoneNumer) {
        if (this.phoneNumber != null) {
            throw new IllegalStateException("Illegal operation");
        }
        if (isValidPhoneNumber(phoneNumer)) {
            this.phoneNumber = phoneNumer;
        } else {
            throw new IllegalArgumentException("Not a valid phone number, please re-enter.");
        }
    }

    // Scanne varer
    public void scanItem(String itemName, int amount, double price) {
        double rebate = 0;
        if (phoneNumber != null && day.equals("thu")) {
            rebate = 0.1;
        }
        System.out.println(amount + "x " + itemName + ": " + (price - (price * rebate)) + " kr");
    }

    // Valideringsmetoder - del 2
    private void validateDay(String day) {
        if (!days.contains(day)) {
            throw new IllegalArgumentException("Invalid weekday");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String cleanedPhoneNumber = phoneNumber.replaceAll("\\s", "");
        if (cleanedPhoneNumber.startsWith("00479") || cleanedPhoneNumber.startsWith("00474")) {
            if (cleanedPhoneNumber.length() != 12) {
                return false;
            }
        } else if (cleanedPhoneNumber.startsWith("+479") || cleanedPhoneNumber.startsWith("+474")) {
            if (cleanedPhoneNumber.length() != 11) {
                return false;
            }
        } else {
            return false;
        }

        char[] chars = cleanedPhoneNumber.toCharArray();
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // main-metode for å teste koden
    public static void main(String[] args) {

        SelfServiceCheckout checkout = new SelfServiceCheckout("thu");

        checkout.scanItem("Norvegia", 2, 120.0);

        checkout.registerPhoneNumber("004742345678");

        checkout.scanItem("Tomat", 3, 5.0);

    }

}
