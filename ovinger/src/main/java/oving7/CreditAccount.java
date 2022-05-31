package oving7;

public class CreditAccount extends AbstractAccount {

    private double creditLine;

    public double getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(double creditLine) {
        if(creditLine <0) {
            throw new IllegalArgumentException("Tilgjengelig kreditt må være 0 eller større");
        } if((creditLine+balance) >= 0) {
            this.creditLine = creditLine;
        } else {
            throw new IllegalStateException("Kan ikke endre kreditt når det blir negativ balanse på konto");
        }
        
    }

    @Override
    void internalWithdraw(double withdrawn) {
        if(withdrawn > (balance+creditLine) || withdrawn < 0){
            throw new IllegalStateException("Du kan ikke ta ut mer penger enn du har på konto");
        } balance -= withdrawn;
        
    }

    public CreditAccount(double kreditt) {
        super();
        setCreditLine(kreditt);
    }
    
    
}
