package Eksamener.ord19.main.java.del3;

public class EmergencyPriorityTreatmentUnit extends TreatmentUnit {
    
    public EmergencyPriorityTreatmentUnit() {
        super();
    }

    @Override
    protected boolean startTreatment(final Patient patient) {
        if(getAvailableDoctors().stream().anyMatch(d -> d.canTreat(patient) != 0.0)) {
            Doctor doctor = getAvailableDoctors().stream().filter(d -> d.canTreat(patient) != 0.0).toList().get(0);
            doctor.setPatient(patient);
            if(waitList.contains(patient)) waitList.remove(patient);
            return true;
        } else {
            for (Doctor doctor : doctors) {
                if((doctor.canTreat(patient) != 0.0) && (doctor.getPatient().getPriority() < patient.getPriority())) {
                    waitList.add(doctor.getPatient());
                    doctor.removePatient();
                    doctor.setPatient(patient);
                    if(waitList.contains(patient)) waitList.remove(patient);
                    return true;
                }
            }
        } return false;
    }
}
