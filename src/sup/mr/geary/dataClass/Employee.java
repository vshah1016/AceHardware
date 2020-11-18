package sup.mr.geary.dataClass;

public class Employee {
    final public String name;
    final public Sales sales;

    public Employee(String name, Sales sales) {
        this.name = name;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public Sales getSales() {
        return sales;
    }
}
