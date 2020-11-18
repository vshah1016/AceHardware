package sup.mr.geary.guiComponent;

import javax.swing.table.DefaultTableModel;

public class MyDataModel extends DefaultTableModel {
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}
