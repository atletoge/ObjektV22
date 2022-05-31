package oving7;

public class DebitAccount extends AbstractAccount {

    @Override
    void internalWithdraw(double withdrawn) {
        if(withdrawn > balance || withdrawn < 0){
            throw new IllegalStateException("Du kan ikke ta ut mer penger enn du har på konto");
        }
        this.balance -= withdrawn;
    }
    
}
