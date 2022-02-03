package stateandbehavior;

public class Account {
    
    float balance;
    float interestRate;

    

    public Account() {
    }

    public void deposit(float tall) {
        if (tall > 0) {
            this.balance += tall;
        }
    }

    public void addInterest() {
        balance += balance * interestRate / 100;
    }
    public float getBalance() {
        return balance;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "Med" + this.getInterestRate() + "som rentefot har du " + this.getBalance() + "på konto";
    }

    public static void main(String[] args) {
        Account konto = new Account();
        konto.setInterestRate(5);
        konto.deposit(100);
        konto.addInterest();
        System.out.println(konto);

    }
}
