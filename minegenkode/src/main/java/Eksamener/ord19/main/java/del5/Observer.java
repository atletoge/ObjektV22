package Eksamener.ord19.main.java.del5;

import Eksamener.ord19.main.java.del4.Doctor;
import Eksamener.ord19.main.java.del4.Patient;

public interface Observer {
    public void updateStatus(Patient patient, Doctor doctor);
}
