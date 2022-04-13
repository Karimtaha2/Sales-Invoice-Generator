/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listner;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import salesinvoicegenerator.Frame;

/**
 *
 * @author karim
 */
public class DeleteInvoiceActionButton {

    public DeleteInvoiceActionButton(Frame frame) {
        frame.headerModel = (DefaultTableModel) frame.invoicesTable.getModel();
        if (frame.invoicesTable.getSelectedRowCount() == 1) { //if you already select a row
            frame.headerModel.removeRow(frame.invoicesTable.getSelectedRow());            // remove selected row from the model
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
        } else {
            if (frame.invoicesTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Empty Table, please load a txt file");
            } else {
                JOptionPane.showMessageDialog(null, "Select a row to delete");

            }
        }
    }
}
