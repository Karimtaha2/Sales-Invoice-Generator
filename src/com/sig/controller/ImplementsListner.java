/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change frame license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit frame template
 */
package com.sig.controller;

import com.sig.controller.button_action_listner.ButtonsActionListners;
import com.sig.controller.menubar_action_listner.CloseFileMenuBarActionListner;
import com.sig.controller.menubar_action_listner.LoadFileMenuBarActionListner;
import com.sig.controller.menubar_action_listner.SaveFileMenuBarActionListner;
import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.model.InvoiceLineJTableModel;
import com.sig.view.MyFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author karim taha
 */
public class ImplementsListner implements ActionListener, ListSelectionListener {

    private MyFrame frame;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // day - month - year
    CloseFileMenuBarActionListner menuBarItemsActionListners;
    ButtonsActionListners buttonsActionListners;
    LoadFileMenuBarActionListner loadFileMenuBarActionListner;
    SaveFileMenuBarActionListner saveFileMenuBarActionListner;
    CloseFileMenuBarActionListner closeFileMenuBarActionListner;
    
    
    public ImplementsListner(MyFrame frame) {
        this.frame = frame;
        this.buttonsActionListners = new ButtonsActionListners(frame);
        this.loadFileMenuBarActionListner = new LoadFileMenuBarActionListner(frame);
        this.saveFileMenuBarActionListner = new SaveFileMenuBarActionListner(frame);
        this.closeFileMenuBarActionListner = new CloseFileMenuBarActionListner(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "LoadFile" -> loadFileMenuBarActionListner.loadFileMenuBar();
            case "SaveFile" -> saveFileMenuBarActionListner.saveFileMenuBar();
            case "CloseFile" -> closeFileMenuBarActionListner.closeFileMenuBar();
            case "CreateNewInvoiceButton" -> buttonsActionListners.displayNewInvoiceDialog();
            case "DeleteInvoiceButton" -> buttonsActionListners.deleteInvoiceButton();
            case "SaveButton" -> buttonsActionListners.displaySaveButtonNewLineDialog();
            case "CancelButton" -> buttonsActionListners.cancelButton();
            case "InsertButtonInDialog" -> buttonsActionListners.insertButtonInDialog();
            case "CancelButtonInDialog" -> buttonsActionListners.cancelButtonInDialog();
            case "createLineOK" -> buttonsActionListners.okButtonNewLineInDialog();
            case "createLineCancel" -> buttonsActionListners.cancelButtonNewLineInDialog();
            default -> throw new AssertionError();
        }
    }

    private void rowSelected_InvoiceTable() {
        
        int selectedRowIndex_InvoiceTable = frame.getInvoiceTableJTableLeftSide().getSelectedRow();
        
        if (selectedRowIndex_InvoiceTable >= 0) { // row >= 0 cause don't work with negative
            
            // effect on data in the top right side 
            InvoiceHeader row = frame.getInvoiceHeaderJTableModel().getInvoicesHeaderList().get(selectedRowIndex_InvoiceTable);
            frame.getCustomerNameJTextField().setText(row.getCustomerName());
            frame.getInvoiceDateJTextField().setText(dateFormat.format(row.getInvoiceDate()));
            frame.getInvoiceNumberJLabel().setText("" + row.getInvoiceNumber());
            frame.getInvoiceTotalJLabel().setText("" + row.getInvoiceTotal());
            ArrayList<InvoiceLine> lines = row.getInvoiceLines();

            // line table model
            frame.setInvoiceLineJTableModel(new InvoiceLineJTableModel(lines));
            frame.getInvoiceItemJTableRightSide().setModel(frame.getInvoiceLineJTableModel());
            frame.getInvoiceLineJTableModel().fireTableDataChanged();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Invoice Selected Ya Karim");
        rowSelected_InvoiceTable();
    }

}
