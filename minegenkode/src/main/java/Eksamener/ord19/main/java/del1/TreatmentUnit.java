package Eksamener.ord19.main.java.del1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * A class for managing a set of doctors and the patients they're treating.
 * When doctors or patients arrive, it is made sure that patients are treated as soon as possible.
 */
public class TreatmentUnit {
 
 
    // Internal variables go here: // 1b

    private List<Patient> waitList = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();


     
    /**
     * Adds a doctor and makes sure s/he starts treating a patient, if one is waiting.
     * @param doctor
     */
    public void addDoctor(final Doctor doctor) {  // 1b
           startTreatment(doctor);
           doctors.add(doctor);


    }


    /**
     * @return the currently available doctors
     */
    public Collection<Doctor> getAvailableDoctors() {  // 1b
        return doctors.stream().filter(d -> d.isAvailable()).toList();
    }


    /**
     * Adds a patient to this treatment unit, and makes sure treatment starts if any doctor is available.
     * Otherwise the patient is queued for treatment when a doctor becomes available.
     * @param patient
     */
    public void addPatient(final Patient patient) {  // 1b
        startTreatment(patient);
    }


    /**
     * @param pred the predicate that the doctor must satisfy
     * @return some doctor satisfying the predicate
     */
    public Doctor getDoctor(final Predicate<Doctor> pred) {  // 1b
        return doctors.stream().filter(pred).findAny().orElse(null);
    }


    /**
     * Find the doctor, if any, that treats the provided patient.
     * @param patient
     * @return the doctor treating the provided patient, or null, of the patient isn't currently being treated
     */
    public Doctor getDoctor(final Patient patient) {  // 1b
        return doctors.stream().filter(d -> d.getPatient().equals(patient)).findAny().orElse(null);
    }


    /**
     * Find all patients that are not currently being treated.
     * @return the patients not currently being treated.
     */
    public Collection<Patient> getWaitingPatients() {  // 1b
        final Collection<Patient> result = new ArrayList<>(waitList);
        return result;
    }


    /**
     * Finds a waiting patient and sets him/her as the provided doctor's patient.
     * @param doctor the doctor for which a patient to treat should be found
     * @return true if a patient for the provided doctor was found, false
* otherwise.
     */
    private boolean startTreatment(final Doctor doctor) {   // 1c
           if(waitList.isEmpty()) return false;
           doctor.setPatient(waitList.get(0));
           waitList.remove(0);
           return true;
    }


    /**
     * Finds an available doctor for the provided patient, and sets that doctor to
* treat the patient.
     * @param patient the patient for which a treating doctor should be found.
     * @return true if a doctor for the provided patient was found, false
* otherwise.
     */
    private boolean startTreatment(final Patient patient) {   // 1c
           if(getAvailableDoctors().isEmpty()) {
               if(!waitList.contains(patient)){
                    waitList.add(patient);
               }
               return false;
           }
           else {
               getAvailableDoctors().iterator().next().setPatient(patient);
               if(waitList.contains(patient)) {
                    waitList.remove(patient);
               } 
               return true;
           }
    }


    /**
     * Removes the link between doctor and patient, after treatment is finished.
     * Since the patient is fully treated, s/he is removed from this treatment
* unit.
     * Also ensure the doctor starts treating another patient.
     * @param doctor the doctor that has finished treating his/her patient.
     */
    public void treatmentFinished(final Doctor doctor) {  // 1c
           doctor.removePatient();
           startTreatment(doctor);
    }
}