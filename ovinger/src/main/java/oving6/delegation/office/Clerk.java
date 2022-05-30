package oving6.delegation.office;

import java.util.function.BinaryOperator;

public class Clerk implements Employee {

    private Printer printer;
    private int taskCount = 0;

    public Clerk(Printer printer) {
        this.printer = printer;
    }

    @Override
    public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
        taskCount += 1;
        return operation.apply(value1, value2);
    }

    @Override
    public void printDocument(String document) {
        printer.printDocument(document, this);
    }

    @Override
    public int getTaskCount() {
        try {
            return taskCount+printer.getPrintHistory(this).size();
        } catch (Exception e) {
            return taskCount;
        }
    }

    @Override
    public int getResourceCount() {
        return 1;
    }
    
}
