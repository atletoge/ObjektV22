package Eksamener.ord19.main.java.del2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/** * A patient has a set of (health) conditions (of type String) that needs to be treated.
 * Supports iterating over the patient's conditions.
 */
public class Patient {
 
    
    // Add fields, constructors, and methods here: // 2a
    private String name;
    private List<String> conditions;


    public Patient(String name, String ... condtions){
        this.name = name;
        this.conditions = new ArrayList<>();
        for (String string : condtions) {
            this.conditions.add(string);
        }
    }

    public Patient(String name) {
        this.name = name;
    }

    public Collection<String> getConditions() { // Du kan strengt tatt iterere gjennom dennne
        return new ArrayList<>(conditions);
    }

    public Iterator<String> getIterator() { // MEn lager en iterator i tilfelle det er det som er ønsket. 
        return conditions.iterator();
    }

    public void addCondition(String condition) {
        if(!conditions.contains(condition)) conditions.add(condition);
    }

    public void removeCondition(String condition) {
        if(conditions.contains(condition)) conditions.remove(condition);
    }

     // Support iteration // 2a
  /**
 * Indicates if this patient has conditions that needs to be treated.
 * @return true if this patient has conditions that needs to be treated,
 * false otherwise.
 */
    public boolean requiresTreatment() { // 2a
        return conditions.isEmpty();
    }
}


/**
* A doctor has the capacity to treat one patient at a time.
* The doctor as a list of competencies (of type String) that
* indicates what conditions s/he can treat.
*/