package sup.mr.geary;

import sup.mr.geary.dataClass.Data;
import sup.mr.geary.dataClass.Employee;
import sup.mr.geary.dataClass.Sales;
import sup.mr.geary.dataClass.Singleton;
import sup.mr.geary.guiComponent.AddEmployee;
import sup.mr.geary.guiComponent.App;
import sup.mr.geary.guiComponent.HighestLowestEmployee;
import sup.mr.geary.guiComponent.MyDataModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static Singleton singleton = Singleton.instance;

    public static void main(String[] args) {
        NumberFormat numberFormat = singleton.getNumberFormat();
        Data data = singleton.getData();
        App app = singleton.getApp();

        JFrame jFrame = new JFrame();
        jFrame.setTitle("Ace Widget Program");

        DefaultTableModel tableModel = new MyDataModel();
        Arrays.stream(new String[]{"Name", "Q1 Sales", "Q2 Sales", "Q3 Sales", "Q4 Sales", "Total"}).forEach(tableModel::addColumn);
        app.table1.setModel(tableModel);

        app.LOWESTSALESButton.setEnabled(false);
        app.HIGHESTSALESButton.setEnabled(false);

        jFrame.setContentPane(app.frame);
        jFrame.pack();
        jFrame.setSize(500,200);
        jFrame.setVisible(true);

        app.ADDEMPLOYEEButton.addActionListener(e -> {
            AddEmployee addEmployee = new AddEmployee();
            JFrame jFrame1 = new JFrame();
            jFrame1.setTitle("Add Employee");
            jFrame1.setContentPane(addEmployee.frame);
            jFrame1.pack();
            jFrame1.setVisible(true);
            addEmployee.OKButton.addActionListener(e1 -> {
                try {
                    singleton.getData().addEmployee(new Employee(addEmployee.employeeName.getText(), new Sales(Double.parseDouble(addEmployee.q1Sales.getText()), Double.parseDouble(addEmployee.q2Sales.getText()), Double.parseDouble(addEmployee.q3Sales.getText()), Double.parseDouble(addEmployee.q4Sales.getText()))));
                    jFrame1.dispatchEvent(new WindowEvent(jFrame1, WindowEvent.WINDOW_CLOSING));
                } catch (Exception invalid) {
                    JOptionPane.showMessageDialog(null, "Invalid Input Please Try Again. Use actual numbers.");
                }
            });
            addEmployee.CANCELButton.addActionListener(e12 -> jFrame1.dispatchEvent(new WindowEvent(jFrame1, WindowEvent.WINDOW_CLOSING)));
        });

        app.HIGHESTSALESButton.addActionListener(e -> {
            ArrayList<Employee> highestEarners = data.highestEarners();
            HighestLowestEmployee highestLowestEmployee = new HighestLowestEmployee();
            highestLowestEmployee.label.setText("Highest Earners:");
            JFrame jFrame1 = new JFrame();
            jFrame1.setTitle("Highest Earning Employees");
            notableEarners(highestEarners, highestLowestEmployee, jFrame1, numberFormat);
        });

        app.LOWESTSALESButton.addActionListener(e -> {
            ArrayList<Employee> lowestEarners = data.leastEarners();
            HighestLowestEmployee highestLowestEmployee = new HighestLowestEmployee();
            highestLowestEmployee.label.setText("Lowest Earners:");
            JFrame jFrame1 = new JFrame();
            jFrame1.setTitle("Lowest Earning Employees");
            notableEarners(lowestEarners, highestLowestEmployee, jFrame1, numberFormat);
        });
    }

    private static void notableEarners(ArrayList<Employee> earners, HighestLowestEmployee highestLowestEmployee, JFrame jFrame1, NumberFormat numberFormat) {
        DefaultTableModel dataModel = new MyDataModel();
        Arrays.stream(new String[]{"Name", "Q1 Sales", "Q2 Sales", "Q3 Sales", "Q4 Sales", "Total"}).forEach(dataModel::addColumn);
        highestLowestEmployee.table.setModel(dataModel);
        earners.forEach(employee -> dataModel.addRow(new String[]{employee.name, numberFormat.format(employee.sales.getQ(1)), numberFormat.format(employee.sales.getQ(2)), numberFormat.format(employee.sales.getQ(3)), numberFormat.format(employee.sales.getQ(4)), numberFormat.format(employee.sales.totalSales())}));
        highestLowestEmployee.OKButton.addActionListener(e -> jFrame1.dispatchEvent(new WindowEvent(jFrame1, WindowEvent.WINDOW_CLOSING)));
        jFrame1.setContentPane(highestLowestEmployee.frame);
        jFrame1.pack();
        jFrame1.setVisible(true);
    }

    public static void enable(){
        singleton.getApp().LOWESTSALESButton.setEnabled(true);
        singleton.getApp().HIGHESTSALESButton.setEnabled(true);
    }
}
