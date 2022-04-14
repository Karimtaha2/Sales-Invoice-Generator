/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listner.listnerActionButton;

import javax.swing.table.DefaultTableModel;
import salesinvoicegenerator.Frame;

/**
 *
 * @author karim
 */
public class CreateNewInvoiceActionButton {
    static int NoNum;
    public CreateNewInvoiceActionButton(Frame frame) {
        // create new invoice row as you typed in invoice data
        frame.headerModel = (DefaultTableModel) frame.invoicesTable.getModel();
        frame.headerModel.addRow(new Object[]{
            frame.invoiceNumValue.getText(),
            frame.invoiceDateInput.getText(),   
            frame.customerNameInput.getText(),
            frame.invoiceTotalValue.getText()
        });
        NoNum = frame.headerModel.getRowCount();
        NoNum++; //to increase invoice number
        frame.invoiceNumValue.setText(String.valueOf(NoNum));        
    }
}
