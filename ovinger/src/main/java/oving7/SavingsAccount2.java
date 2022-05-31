package oving7;

public class SavingsAccount2 extends AbstractAccount {

    private int withdrawals;
    private double fee;
    private int counter;

    @Override
    void internalWithdraw(double withdrawn) {
        if(counter >= withdrawals){
            if((balance-withdrawn-fee) < 0){
                throw new IllegalStateException("Kan ikke ta ut mer penger enn du har på konto.");
            } this.balance -= (withdrawn+fee);
        } else {
            if((balance-withdrawn) < 0) {
                throw new IllegalStateException("Kan ikke ta ut mer penger enn du har på konto.");
            } else {
                balance -= withdrawn;
                this.counter++;
            }
        }
        
    }
    
    public SavingsAccount2(int withdrawals, double fee) {
        super();
        this.withdrawals = withdrawals;
        this.fee = fee; 
    }
}
