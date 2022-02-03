package oving2;

public class Account {

    private double rentefot;
    private double balanse;

    public Account(double balanse, double rentefot) {
        this.sjekkTall(balanse);
        this.sjekkTall(rentefot);
        this.balanse = balanse;
        this.rentefot = rentefot;
    }


    private void sjekkTall(double tall) {
        if (tall < 0) {
            throw new IllegalArgumentException(tall+" er ikke gyldig. Tallet kan ikke være mindre enn 0");
        }
    }

    public double getBalance() {
        return balanse;
    }

    public double getInterestRate() {
        return rentefot;
    }

    public void setInterestRate(double rentefot) {
        this.sjekkTall(rentefot);
        this.rentefot = rentefot;
    }

    public void deposit(double belop) {
        this.sjekkTall(belop);
        balanse += belop;
    }

    public void withdraw(double belop) {
        this.sjekkTall(belop);
        // this.sjekktall(balanse-=belop)
        // Kan du forklare hvorfor dette ikke fungerer? Ser ut som dette endrer selve balanse-feltet også?
        this.sjekkTall(balanse-belop);
        balanse -= belop;
    }

    public void addInterest() {
        balanse += balanse*rentefot/100;
    }

    @Override
    public String toString() {
        return "Med" + this.getInterestRate() + "som rentefot har du " + this.getBalance() + "på konto";
    }

    public static void main(String[] args) {
        Account konto = new Account(100, 5);
        konto.setInterestRate(5);
        System.out.println(konto);
        konto.withdraw(100);
        System.out.println(konto);
    }
}

//TEORI


// Forklar hvorfor metodene over kan sies å være en komplett innkapsling av tilstanden?
// På grunn av at man ikke endrer noe variabler direkte, man bruker metoder til å gjøre all endring, i tillegg er feltene private så man kan ikke "force" en endring på de utenfor klassen.

// Er denne klassen data-orientert eller tjeneste-orientert?
// Er vell en blanding? Den behandler data i form av status på kontoen. Men mest tjenesteorientert da den utfører beregninger for brukeren knyttet til kontoen.

