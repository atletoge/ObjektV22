package uke5;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CarController {

	Car	car;
	
	@FXML TextField carPlate;
	@FXML TextField carSeats;
	@FXML TextField driverName;
	@FXML TextField driverAge;
	@FXML TextField passengerName;
	@FXML TextField passengerAge;
	@FXML TextField removePassenger;
	@FXML Label carInfo;
	@FXML Label ageInfo;
	@FXML Button setDriver;
	@FXML Button addPassenger;
	@FXML Button setCar;
	@FXML Button removePassengerButton;
	

	@FXML void handleSetCar() {
		String regnr = carPlate.getText();
		int antall = Integer.parseInt(carSeats.getText());
		try {
			car = new Car(regnr, antall);
			carInfo.setText(car.toString());
		} catch (Exception e) {
			carInfo.setText(e.getMessage());
		}	
	}

	@FXML void handleSetDriver() {
	}

	@FXML void handleAddPassenger() {
	}

	@FXML void handleRemovePassenger() {
	}


}
