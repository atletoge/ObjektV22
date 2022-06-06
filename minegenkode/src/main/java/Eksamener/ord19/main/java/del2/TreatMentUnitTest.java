package Eksamener.ord19.main.java.del2;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TreatmentUnitTest {
 
 
    private TreatmentUnit treatmentUnit;


    @Before
    public void setUp() {
          ...
}




    @Test
    public void testAddDoctorsPatient() {
       final Doctor doctor1 = new Doctor(...); // new doctor can treat "flu"
       final Doctor doctor2 = new Doctor(); // new doctor can treat "noseblead" and "pneumonia"
    treatmentUnit.addDoctor(doctor1);
    treatmentUnit.addDoctor(doctor2);
       // Test that both doctors are available.
    ...
           
        final Patient patient = new Patient();
     patient.addConditions(...); // patient has conditions "flu" and "noseblead"
        // 2e) start sequence diagram 
     treatmentUnit.addPatient(patient);
        // Test that only one of the doctors are available:
     ...
     Doctor patientDoctor = treatmentUnit.getDoctor(patient);
     patientDoctor.treat();
     treatmentUnit.treatmentFinished(patientDoctor);
  // 2e) end sequence diagram
        // Test that the previous doctor is available and that a
  // new doctor has been assigned to the patient.
      ...
           
      patientDoctor = treatmentUnit.getDoctor(patient);
      patientDoctor.treat();
       treatmentUnit.treatmentFinished(patientDoctor);
          // Test that both doctors are available:
          ...
}
}
