package Eksamener.kont19.main.java.del1;

import java.time.LocalDateTime;

public class RentalInfo {
    
    private LocalDateTime start;
    private LocalDateTime end;


    public RentalInfo(LocalDateTime start, LocalDateTime end) {
        setStart(start);
        setEnd(end);
    }

    public LocalDateTime getStart() {
        return start;
    }
    private void setStart(LocalDateTime start) { //Skal ikke kunne endres etter startstidspunkt.
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
