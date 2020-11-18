#<ins>**Algorithm**</ins>
## Level 0
> Make Singleton With All of My Objects\
> Create Main GUI\
> Add Column Names\
> Disable Highest/Lowest Employee Buttons\
> Program Add Employee Button\
> Program Higest Earners Button\
> Program Lowest Earners Button

## Level 1
> Make Singleton With All of my Objects:
```java
private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
private final Scanner scanner = new Scanner(System.in);
private final Data data = new Data(); //holds all data for the entire project
private final App app = new App();
```
> Create Main Gui:
```java
JFrame jFrame = new JFrame();
jFrame.setTitle("Ace Widget Program");
jFrame.setContentPane(app.frame);
jFrame.pack();
jFrame.setSize(500,200);
jFrame.setVisible(true);
```
> Add Column Names:
```java
DefaultTableModel tableModel = new MyDataModel();
Arrays.stream(new String[]{"Name", "Q1 Sales", "Q2 Sales", "Q3 Sales", "Q4 Sales", "Total"}).forEach(tableModel::addColumn);
```
> Disable Higest/Lowest Employee Buttons:
```java
app.LOWESTSALESButton.setEnabled(false);
app.HIGHESTSALESButton.setEnabled(false);
```
> Program Add Employee Button:
   >> Make Add Employee Window\
   Program OK button to add employee\
   Program Cancel Button

> Program The Highest Earners Button:
   >> Fetch the highest earners from data class\
    Add The Highest Earners to Table\
    Program OK button

> Program Lowest Earners Button:
   >> Same as the Highest Earners but fetch the Lowest Earners\
## Level 2
> Make Add Employee Window:
```java
AddEmployee addEmployee = new AddEmployee();
JFrame jFrame1 = new JFrame();
jFrame1.setTitle("Add Employee");
jFrame1.setContentPane(addEmployee.frame);
jFrame1.pack();
jFrame1.setVisible(true);
```
> Program OK Button to add Employee
```java
try {
        singleton.getData().addEmployee(new Employee(addEmployee.employeeName.getText(), new Sales(Double.parseDouble(addEmployee.q1Sales.getText()), Double.parseDouble(addEmployee.q2Sales.getText()), Double.parseDouble(addEmployee.q3Sales.getText()), Double.parseDouble(addEmployee.q4Sales.getText()))));
        jFrame1.dispatchEvent(new WindowEvent(jFrame1, WindowEvent.WINDOW_CLOSING));
    } catch (Exception invalid) {
        JOptionPane.showMessageDialog(null, "Invalid Input Please Try Again. Use actual numbers.");
    }
```
> Program Cancel Button
```java
addEmployee.CANCELButton.addActionListener(e12 -> jFrame1.dispatchEvent(new WindowEvent(jFrame1, WindowEvent.WINDOW_CLOSING)));
```

>Fetch Highest Earners from Data Class:
```java
ArrayList<Employee> highestEarners = data.highestEarners();
```
>Add Highest Earners to Table:
```java
DefaultTableModel dataModel = new MyDataModel();
Arrays.stream(new String[]{"Name", "Q1 Sales", "Q2 Sales", "Q3 Sales", "Q4 Sales", "Total"}).forEach(dataModel::addColumn);
highestLowestEmployee.table.setModel(dataModel);
earners.forEach(employee -> dataModel.addRow(new String[]{employee.name, numberFormat.format(employee.sales.getQ(1)), numberFormat.format(employee.sales.getQ(2)), numberFormat.format(employee.sales.getQ(3)), numberFormat.format(employee.sales.getQ(4)), numberFormat.format(employee.sales.totalSales())}));
```
>OK Button:
```java
highestLowestEmployee.OKButton.addActionListener(e -> jFrame1.dispatchEvent(new WindowEvent(jFrame1, WindowEvent.WINDOW_CLOSING)));
```
## Level 3
> Find Highest Earners
```java
return employees.stream()
                .filter(employee -> employee.getSales().totalSales() == highestEarning)
                .collect(Collectors.toCollection(ArrayList<Employee>::new));
```
>Find Lowest Earners
```java
return employees.stream()
                .filter(employee -> employee.getSales().totalSales() == leastEarning)
                .collect(Collectors.toCollection(ArrayList<Employee>::new));
```
>Add Employees:
```java
leastEarning = leastEarning == null ? employee.getSales().totalSales() : Math.min(leastEarning, employee.getSales().totalSales());
highestEarning = highestEarning == null ? employee.getSales().totalSales() : Math.max(highestEarning, employee.getSales().totalSales());
employees.add(employee);
if (employees.size() >= 2) Main.enable();
DefaultTableModel defaultTableModel = (DefaultTableModel) Main.singleton.getApp().table1.getModel();
defaultTableModel.addRow(new String[]{employee.name, Main.singleton.getNumberFormat().format(employee.sales.getQ(1)), Main.singleton.getNumberFormat().format(employee.sales.getQ(2)), Main.singleton.getNumberFormat().format(employee.sales.getQ(3)), Main.singleton.getNumberFormat().format(employee.sales.getQ(4)), Main.singleton.getNumberFormat().format(employee.sales.totalSales())});
```