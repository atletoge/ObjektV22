package oving6.delegation.office;

import java.util.Collection;
import java.util.List;
import java.util.function.BinaryOperator;

public class Manager implements Employee {

    private List<Employee> employees;
    private int total;
    private int current = 1;
    
    public Manager(Collection<Employee> employees){
        if(employees.isEmpty()){
            throw new IllegalArgumentException("Listen med anstatte kan ikke være tom");
        } this.employees = employees.stream().toList();
        this.total = employees.size()+1;
    }

    @Override
    public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
        return nesteAnsatt().doCalculations(operation, value1, value2);
    }

    @Override
    public void printDocument(String document) {
        nesteAnsatt().printDocument(document);
        
    }

    @Override
    public int getTaskCount() {
        return employees.stream().mapToInt(employee -> employee.getTaskCount()).sum();
    }

    @Override
    public int getResourceCount() {
        int sum = 1;
        for (Employee employee : employees) {
            sum += employee.getResourceCount();
        } return sum;
        //return total;
    }

    private Employee nesteAnsatt() {

        int dummy = current;
        if(employees.size()+1 >= current) {
            current = 1;
        } else {
            current += 1;
        }
        return employees.get(dummy-1);
    }
}
