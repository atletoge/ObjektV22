package del2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppController {

	// TODO - Add any needed fields here
	@FXML
	private TextField username, password;
	@FXML
	private Label brukernavnl, passordl;
	@FXML
	private Button logginn;
	@FXML
	public TextArea output;
	public static String logInSuccess = "Gratulerer du har logget inn!";
	public static String logInFailed = "Feil brukernavn eller passord";
	private String brukernavn;
	private String passord;


	public void onLogIn() {
		brukernavn = username.getText();
		passord = password.getText();
		if (brukernavn.equals("admin") && passord.equals("admin")) {
			output.setText(logInSuccess);
		} else {
			output.setText(logInFailed);
		}
	}

	/**
	 * TODO - what should be the name of this method? Uncomment this part of the
	 * code and put it inside the correct function public void { String brukernavn;
	 * // Extract username from FXML here String passord; // Extract password from
	 * FXML here
	 * 
	 * if (brukernavn.equals("admin") && passord.equals("admin")) {
	 * output.setText(logInSuccess); } else { output.setText(logInFailed); } }
	 */

}
