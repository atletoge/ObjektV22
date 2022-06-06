package Eksamener.kont19.main.java.del1;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    // refers to the app's BikeRental object
    private final BikeRental bikeRental;
 
    // refers to the app user
    private final Person me;
     
    // refers to currently selected bike in map
    private Bike bike;
    @FXML
    private TextField fromInput, toInput;
    
    // Egentlig to buttoner også? Men de har ikke fxid

    @FXML
    private void plus1HourAction() {
        setToTime(getToTime().plusHours(1));
    }

    @FXML
    private void minus1HourAction() {
        setToTime(getToTime().plusHours(1));
    }

    @FXML
    private void rentAction() {
        bikeRental.rentBike(me, bike, getFromTime(), getToTime());
    }

    private LocalDateTime getFromTime() {
           return LocalDateTime.parse(fromInput.getText());
    }
/**
 * Updates the from input field value according to the LocalDateTime argument
 * @param time
 */
    private void setFromTime(final LocalDateTime time) {
        fromInput.setText(time.toString());
    }
 
/**
 * @return a LocalDataTime object corresponding to the from input field value
 */
    private LocalDateTime getToTime() {
        return LocalDateTime.parse(toInput.getText());
    }
/**
 * Updates the from input field value according to the LocalDateTime argument
 * @param time
 */
    private void setToTime(final LocalDateTime time) {
        toInput.setText(time.toString());
    }
}
