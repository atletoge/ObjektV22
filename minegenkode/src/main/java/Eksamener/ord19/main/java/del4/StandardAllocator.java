package Eksamener.ord19.main.java.del4;

public class StandardAllocator implements DoctorAllocator{

    @Override
    public Patient allocatePatient(Doctor doctor, TreatmentUnit treatmentUnit) {
        for (Patient patient : treatmentUnit.getWaitingPatients()) {
            if(doctor.canTreat(patient) != 0.0) return patient;
        } return null;
    }

    @Override
    public Doctor allocateDoctor(Patient patient, TreatmentUnit treatmentUnit) {
        for (Doctor doctor : treatmentUnit.getAvailableDoctors()) {
            if(doctor.canTreat(patient) > 0.0) return doctor;
        }return null;
    }
    
}
