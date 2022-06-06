package Eksamener.ord19.main.java.del4;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
 
 
    // Internal variables go here // 2b
    private List<String> competencies;
    private String name;
    private Patient patient;

    /**
 * Initialise this doctor with a set of competencies.
 * @param competencies
 */
    public Doctor(String name, String ... competencies) { // 2b
        this.name = name;
        this.competencies = new ArrayList<>();
        for (String string : competencies) {
            this.competencies.add(string);
        }
    }
     
    /**
  * Indicates to what extent this doctor can treat the provided patient.
  * The value is the number of the patient's conditions this doctor
  * can treat divided by the number of conditions the patient has.
  * Conditions and competences are matched using simple String comparison.
  * @param patient
  * @return the ratio of the patient's conditions that this
  * doctor can treat.
  */
    public double canTreat(final Patient patient) { // 2b
        if(patient.getConditions().size() == 0) return 0.0;
        int tot = patient.getConditions().size();
        int treatable = 0;
        for (String string : patient.getConditions()) {
            if(competencies.contains(string)) {
                treatable++;
            }
        } 
        return (double)treatable/(double)tot;
    }


    /**
 * "Treats" the patient by removing all the patient's conditions
 * that this doctor can treat.
 */
    public void treat() { // 2b
        for (String comp : competencies) {
            if(patient.getConditions().contains(comp)) patient.removeCondition(comp);
        }
    }


    /**
 * @return the patient this doctor is treating, or null if s/he
 * isn't currently treating any patient.
 */
    public Patient getPatient() {
        return patient;
    }


    /**
 * @return true if this doctor is currently treating a patient, otherwise
 * false.
 */
    public boolean isAvailable() {
        return getPatient() != null;
    }
    public void removePatient() {
        if(isAvailable()) throw new IllegalArgumentException("No patient");
        this.patient = null;

    }

    /**
 * Sets the patient that this doctor is treating, use null to indicate
 * s/he isn't currently treating any patient.
 * @param patient
 */
    public void setPatient(final Patient patient) {
        if(!isAvailable()) throw new IllegalArgumentException("Doctor is not available to take patients");
        this.patient = patient;
    }
}