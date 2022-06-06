package Eksamener.ord19.main.java.del4;

public class EmergencyAllocator implements DoctorAllocator{

    @Override
    public Patient allocatePatient(Doctor doctor, TreatmentUnit treatmentUnit) {
        for (Patient patient : treatmentUnit.getWaitingPatients()) {
            if(doctor.canTreat(patient) != 0.0) return patient;
        } return null;
    }

    @Override
    public Doctor allocateDoctor(Patient patient, TreatmentUnit treatmentUnit) {
        if(treatmentUnit.getAvailableDoctors().stream().anyMatch(d -> d.canTreat(patient) != 0.0)) {
            return treatmentUnit.getAvailableDoctors().stream().filter(d -> d.canTreat(patient) != 0.0).toList().get(0);
        }
        else {
            for (Doctor doctor : treatmentUnit.getDoctors()) {
                if((doctor.canTreat(patient) != 0.0) && (doctor.getPatient().getPriority() < patient.getPriority())) {
                    return doctor;
                }
            }
        }return null;
    }
    
}
