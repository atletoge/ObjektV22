package of3.kode;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SelfServiceCheckout {

    // Konstanter - del 1
    public static final Collection<String> days = Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun");

    // Felter / Tilstand
    private String day;
    private boolean adminMode;
    private String password;
    private String phoneNumber;

    // Konstruktør - del 3
    public SelfServiceCheckout(String day, String password) {
        validateDay(day);
        validatePassword(password);
        this.day = day;
        this.password = password;
        this.adminMode = false;
    }

    // Aktiver admin-modus
    public void activateAdminMode(String password) {
        if (this.adminMode) {
            throw new IllegalStateException("Illegal operation");
        }
        if (this.password.equals(password)) {
            this.adminMode = true;
        } else {
            throw new IllegalArgumentException("Wrong password, permission denied");
        }
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
        if (phoneNumber != null) {
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

    private boolean validatePassword(String password) {
        if (password.length() < 6 || password.length() > 10) {
            return false;
        }
        char[] chars = password.toCharArray();
        boolean containsNumeric = false;
        boolean containsAlphabetic = false;
        for (char c : chars) {
            if (Character.isDigit(c)) {
                containsNumeric = true;
            } else if (Character.isAlphabetic(c)) {
                containsAlphabetic = true;
            }
        }
        return containsAlphabetic && containsNumeric;
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

        SelfServiceCheckout checkout = new SelfServiceCheckout("thu", "passord123");

        checkout.scanItem("Norvegia", 2, 120.0);

        checkout.registerPhoneNumber("004742345678");

        checkout.scanItem("Tomat", 3, 5.0);

        checkout.activateAdminMode("passord123");

    }

}
