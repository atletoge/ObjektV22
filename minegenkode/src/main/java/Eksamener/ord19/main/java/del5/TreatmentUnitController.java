package Eksamener.ord19.main.java.del5;

import Eksamener.ord19.main.java.del4.Doctor;
import Eksamener.ord19.main.java.del4.Patient;
import Eksamener.ord19.main.java.del4.TreatmentUnit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

// TreatmentUnitController.java:
public class TreatmentUnitController implements Observer{
 
    @FXML
    private Label patientMessage;
            TreatmentUnit treatmentUnit;
             
            public TreatmentUnitController() {
                    treatmentUnit = new TreatmentUnit();
                    treatmentUnit.addObserver(this);
                        // ... 5 b) other initialization ...
                    patientMessage.setText("");
            }

            @Override
            public void updateStatus(Patient patient, Doctor doctor) {
                patientMessage.setText(patient+" gå til "+doctor);
                
            }
 
 
            // ... 5 b) declarations and methods here...
}
