import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class SelfServiceCheckout {
    
    private String day;
    private String phoneNumber;
    static List<String> days = Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun");

    private void validateDay(String day) {
        if (!days.contains(day)) {
            throw new IllegalArgumentException("Invalid weekday");
        }
    }

    public SelfServiceCheckout(String day) {
        validateDay(day);
        this.day = day;
    }

    public void registerPhoneNumber(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Not a valid phonenumber!");
        }
        this.phoneNumber = phoneNumber;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String cleanPhoneNumber = phoneNumber.replaceAll("\\s", "");
        if (cleanPhoneNumber.startsWith("00479") || cleanPhoneNumber.startsWith("00474") ) {
            if (cleanPhoneNumber.length() != 12) {
                return false;
            }
        }
        else if(cleanPhoneNumber.startsWith("+479") || cleanPhoneNumber.startsWith("00474")) {
            if (cleanPhoneNumber.length() != 11) {
                return false;
            }
        }
        else {
            return false;
        }

        char[] chars = cleanPhoneNumber.toCharArray();
        boolean firstIndex = true;
        for (char c : chars) {
            if (!Character.isDigit(c)) {
                if (c != "+" && !firstIndex) {
                    return false;
                }
                firstIndex = false;
                
            }
        }

        return true;
    }

    public void scanItem(String itemName, int amount, double price) {
        double rebate = 0.0;
        if (phoneNumber != null && day.equals("thu")) {
            rebate = 0.1;
        } 
        System.out.println(amount + "x "+ itemName + ": " + (price - (price*rebate))+ "kr");
    }

    public static void main(String[] args) {
        SelfServiceCheckout checkout = new SelfServiceCheckout("thu");
        checkout.registerPhoneNumber("004742345678");
    }

}
