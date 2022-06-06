package Eksamener.ord19.main.java.del3;

import java.util.ArrayList;
import java.util.List;

public class PriorityTreatmentUnit extends TreatmentUnit{
    
    //private HashMap<Patient, Integer> priorityList;

    public PriorityTreatmentUnit () {
        super(); // Intialisere superklasse for å få listene på plass osv. 
    }

    @Override
    protected boolean startTreatment(final Doctor doctor) {
        List<Patient> sortedByPrio = new ArrayList<>(getWaitingPatients().stream().toList());
        sortedByPrio.sort(PatientComparator.patientPriorityComparator());
        for (Patient patient : sortedByPrio) {
            if(doctor.canTreat(patient) != 0.0) {
                waitList.remove(patient);
            }
            doctor.setPatient(patient);
            return true;
        }return false;
    }





}
