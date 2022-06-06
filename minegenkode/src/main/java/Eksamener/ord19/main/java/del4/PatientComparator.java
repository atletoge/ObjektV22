package Eksamener.ord19.main.java.del4;

import java.util.Comparator;

public class PatientComparator {
    
    public static Comparator<Patient> patientPriorityComparator() {
        return (Patient p1, Patient p2) -> {
            return (p2.getPriority() - p1.getPriority());
        };
    }
}
