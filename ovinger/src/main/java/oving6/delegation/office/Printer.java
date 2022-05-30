package oving6.delegation.office;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Printer {
    
    private HashMap<Employee, Collection<String>> historikk;

    public Printer() {
        this.historikk = new HashMap<Employee, Collection<String>>();
    }


    public void printDocument(String document, Employee employee) {
        if(historikk.containsKey(employee)) {
            historikk.get(employee).add(document);
        } else{
            historikk.put(employee, new ArrayList<String>());
            historikk.get(employee).add(document);
        }
        
    }

    public Collection<String> getPrintHistory(Employee employee){
        if(!historikk.containsKey(employee)){
            return Collections.emptyList();
        } return new ArrayList<String>(historikk.get(employee));
    }
}
