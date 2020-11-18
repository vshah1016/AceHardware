package sup.mr.geary.dataClass;

import sup.mr.geary.Main;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Data {
    private final ArrayList<Employee> employees = new ArrayList<>();
    private Double leastEarning;
    private Double highestEarning;


    public ArrayList<Employee> highestEarners() {
        return employees.stream()
                .filter(employee -> employee.getSales().totalSales() == highestEarning)
                .collect(Collectors.toCollection(ArrayList<Employee>::new));
    }

    public ArrayList<Employee> leastEarners() {
        return employees.stream()
                .filter(employee -> employee.getSales().totalSales() == leastEarning)
                .collect(Collectors.toCollection(ArrayList<Employee>::new));
    }

    public void addEmployee(Employee employee){
        leastEarning = leastEarning == null ? employee.getSales().totalSales() : Math.min(leastEarning, employee.getSales().totalSales());
        highestEarning = highestEarning == null ? employee.getSales().totalSales() : Math.max(highestEarning, employee.getSales().totalSales());
        employees.add(employee);
        if (employees.size() >= 2) Main.enable();
        DefaultTableModel defaultTableModel = (DefaultTableModel) Main.singleton.getApp().table1.getModel();
        defaultTableModel.addRow(new String[]{employee.name, Main.singleton.getNumberFormat().format(employee.sales.getQ(1)), Main.singleton.getNumberFormat().format(employee.sales.getQ(2)), Main.singleton.getNumberFormat().format(employee.sales.getQ(3)), Main.singleton.getNumberFormat().format(employee.sales.getQ(4)), Main.singleton.getNumberFormat().format(employee.sales.totalSales())});
    }
}
