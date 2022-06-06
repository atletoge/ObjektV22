package Eksamener.ord19.main.java.del4;

import java.util.List;

public class PriorityAllocator implements DoctorAllocator {

    @Override
    public Patient allocatePatient(Doctor doctor, TreatmentUnit treatmentUnit) {
        List<Patient> sortedByPrio = new ArrayList<>(getWaitingPatients().stream().toList());
        sortedByPrio.sort(PatientComparator.patientPriorityComparator());
        for (Patient patient : sortedByPrio) {
            if(doctor.canTreat(patient) != 0.0) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public Doctor allocateDoctor(Patient patient, TreatmentUnit treatmentUnit) {
        for (Doctor doctor : treatmentUnit.getAvailableDoctors()) {
            if(doctor.canTreat(patient) > 0.0) return doctor;
        }return null;
    }
    
}
