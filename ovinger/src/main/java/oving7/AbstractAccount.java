package oving7;

public abstract class AbstractAccount {

    protected double balance;
    
    protected void deposit(double deposited) {
        if(deposited <= 0) {
            throw new IllegalArgumentException("Du kan ikke sette inn negativt med penger");
        } balance += deposited;
    }

    public AbstractAccount() {
        this.balance = 0;
    }

    protected void withdraw(double withdrawn) {
        if(withdrawn < 0) {
            throw new IllegalArgumentException("Tallet må være positivt");
        }
        internalWithdraw(withdrawn);
    }

    abstract void internalWithdraw(double withdrawn);

    protected double getBalance() {
        return balance;
    }
}
