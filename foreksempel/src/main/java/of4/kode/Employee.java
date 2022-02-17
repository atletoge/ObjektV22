package of4.kode;

public class Employee {

    private Department employer;


    public Empoyee(Department department) {
        this.employer = department;
        this.employer.addEmployee(this);
    }

}
