package Eksamener.ord19.main.java.del4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import Eksamener.ord19.main.java.del5.Observer;

public class TreatmentUnit {
 
 
    // Internal declaration hidden
    protected List<Patient> waitList = new ArrayList<>();
    protected List<Doctor> doctors = new ArrayList<>();
    protected DoctorAllocator allocator = new StandardAllocator();
    protected List<Observer> observers = new ArrayList<>();
     
    /**
 * Adds a doctor and makes sure s/he starts treating a patient, if one
 * is waiting.
 * @param doctor
 */
    public void addDoctor(final Doctor doctor) {
        startTreatment(doctor);
        doctors.add(doctor);
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Doctor doctor, Patient patient) {
        for (Observer observer : observers) {
            observer.updateStatus(patient, doctor);
        }
    }

    protected void setAllocator(DoctorAllocator allocator) {
        this.allocator = allocator;
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }


    /**
 * @return the currently available doctors
 */
    public Collection<Doctor> getAvailableDoctors() {
        return doctors.stream().filter(d -> d.isAvailable()).toList();
   }


    /**
 * Adds a patient to this treatment unit, and makes sure treatment starts
 * if any doctor is available.
 * Otherwise the patient is queued for treatment when a doctor becomes
 * available.
 * @param patient
 */
    public void addPatient(final Patient patient) {
        startTreatment(patient);
    }


    /**
 * @param pred the predicate that the doctor must satisfy
 * @return some doctor satisfying the predicate
 */
    public Doctor getDoctor(final Predicate<Doctor> pred) {
        return doctors.stream().filter(pred).findAny().orElse(null);
    }


    /**
 * Find the doctor, if any, that treats the provided patient.
 * @param patient
 * @return the doctor treating the provided patient, or null if the
 * patient isn't currently being treated.
 */
    public Doctor getDoctor(final Patient patient) {
        return doctors.stream().filter(d -> d.getPatient().equals(patient)).findAny().orElse(null);
    }


    /**
 * Find all patients that are not currently being treated
 * @return the patients not currently being treated
 */
    public Collection<Patient> getWaitingPatients() {
        final Collection<Patient> result = new ArrayList<>(waitList);
        return result;
    }


    /**
 * Finds a waiting patient and sets him/her as the provided doctor's patient.
 * Will only accept a patient that has some condition that the doctor actually
 * can treat.
 * @param doctor the doctor for which a patient to treat should be found
 * @return true if a patient for the provided doctor was found, false
 * otherwise.
 */
    protected boolean startTreatment(final Doctor doctor) {
        final Patient patient = allocator.allocatePatient(doctor, this);
        if(patient != null) {
            if(waitList.contains(patient)) {
                waitList.remove(patient);
            }
            doctor.setPatient(patient);
            notifyObservers(doctor, patient);
            return true;
        }
        return false;
    }


    /**
 * Finds an available doctor for the provided patient, and sets that
 * doctor to treat the patient.
 * Will only accept a doctor that actually can treat some condition for the
 * provided patient.
 * @param patient the patient for which a treating doctor should be found
 * @return true if a doctor for the provided patient was found, false
 * otherwise.
 */
    protected boolean startTreatment(final Patient patient) {
        final Doctor doctor = allocator.allocateDoctor(patient, this);
        if(doctor != null) {
            doctor.setPatient(patient);
            notifyObservers(doctor, patient);
            if(waitList.contains(patient)) waitList.remove(patient);
            return true;
        }
        else {
            if(!waitList.contains(patient)) waitList.add(patient);
            return false;
        }
    }


    /**
 * Removes the link between doctor and patient, after treatment is finished.
 * If the patient is fully treated, s/he is removed from this treatment unit,
 * otherwise another round of treatment is initiated.
 * Also ensure the doctor starts treating another patient.
 * @param doctor the doctor that has finished treating his/her patient
 */
    public void treatmentFinished(final Doctor doctor) {
        doctor.treat();
        if(!doctor.getPatient().getConditions().isEmpty()) {
            Patient patient = doctor.getPatient();
            doctor.removePatient();
            startTreatment(patient);
        } doctor.removePatient();
    }


}