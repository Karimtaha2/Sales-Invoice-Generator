/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sig.controller.button_action_listner;

import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceHeaderJTableModel;
import com.sig.model.InvoiceLine;
import com.sig.model.InvoiceLineJTableModel;
import com.sig.view.CreateNewInvoiceHeaderDialog;
import com.sig.view.MyFrame;
import com.sig.view.SaveLineDialog;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author karim taha
 */
public class ButtonsActionListners {

    private MyFrame frame;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // day - month - year

    public ButtonsActionListners(MyFrame frame) {this.frame = frame;}

    public void displayNewInvoiceDialog() {
        frame.setNewInvoiceDialog(new CreateNewInvoiceHeaderDialog(frame));
        frame.getNewInvoiceDialog().setVisible(true);
    }

    public void insertButtonInDialog() {
        String customerNameDialogString = frame.getNewInvoiceDialog().getCustomerNameJTextField().getText();
        String dateDialogString = frame.getNewInvoiceDialog().getInvoiceDateJTextField().getText();

        frame.getNewInvoiceDialog().setVisible(false);
        frame.getNewInvoiceDialog().dispose();
        frame.setNewInvoiceDialog(null);

        try {
            Date dateDialog = dateFormat.parse(dateDialogString);
            int invoiceNumber = getNextInvoiceNumberDialog();
            InvoiceHeader invoiceHeader = new InvoiceHeader(invoiceNumber, customerNameDialogString, dateDialog);
            frame.getInvoicesHeaderList().add(invoiceHeader);
            frame.getInvoiceHeaderJTableModel().fireTableDataChanged();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(frame, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        displayInvoices();
    }

    public int getNextInvoiceNumberDialog() {
        int max = 0;
        for (InvoiceHeader header : frame.getInvoicesHeaderList()) {
            if (header.getInvoiceNumber() > max) {
                max = header.getInvoiceNumber();
            }
        }
        return max + 1;
    }

    public void displayInvoices() {
        System.out.println("===========================");
        for (InvoiceHeader header : frame.getInvoicesHeaderList()) {
            System.out.println(header);
        }
        System.out.println("===========================");
    }

    public void cancelButtonInDialog() {
        frame.getNewInvoiceDialog().setVisible(false);
        frame.getNewInvoiceDialog().dispose();
        frame.setNewInvoiceDialog(null);
    }

    public void deleteInvoiceButton() {
        int invoiceIndex = frame.getInvoiceTableJTableLeftSide().getSelectedRow();
        InvoiceHeader header = frame.getInvoiceHeaderJTableModel().getInvoicesHeaderList().get(invoiceIndex);
        frame.getInvoiceHeaderJTableModel().getInvoicesHeaderList().remove(invoiceIndex);
        frame.getInvoiceHeaderJTableModel().fireTableDataChanged();
        frame.setInvoiceLineJTableModel(new InvoiceLineJTableModel(new ArrayList<InvoiceLine>()));
        frame.getInvoiceItemJTableRightSide().setModel(frame.getInvoiceLineJTableModel());
        frame.getInvoiceLineJTableModel().fireTableDataChanged();
        frame.getCustomerNameJTextField().setText("");
        frame.getInvoiceDateJTextField().setText("");
        frame.getInvoiceNumberJLabel().setText("");
        frame.getInvoiceTotalJLabel().setText("");
    }

    public void displaySaveButtonNewLineDialog() {
        frame.setSaveLineDialog(new SaveLineDialog(frame));
        frame.getSaveLineDialog().setVisible(true);
    }

    public void okButtonNewLineInDialog() {

        int itemNumber = InvoiceHeaderJTableModel.ItemNumberInt;
        String itemNameString = frame.getSaveLineDialog().getItemNameJTextField().getText();
        String itemCountString = frame.getSaveLineDialog().getItemCountJTextField().getText();
        String itemPriceString = frame.getSaveLineDialog().getItemPriceJTextField().getText();

        frame.getSaveLineDialog().setVisible(false);
        frame.getSaveLineDialog().dispose();
        frame.setSaveLineDialog(null);

        int itemCountInt = Integer.parseInt(itemCountString);
        double itemPriceDouble = Double.parseDouble(itemPriceString);
        int headerIndexInt = frame.getInvoiceTableJTableLeftSide().getSelectedRow();
        InvoiceHeader invoice = frame.getInvoiceHeaderJTableModel().getInvoicesHeaderList().get(headerIndexInt);

        InvoiceLine invoiceLine = new InvoiceLine(itemNumber, itemNameString, itemPriceDouble, itemCountInt, invoice);
        invoice.addInvoiceLine(invoiceLine);
        frame.getInvoiceLineJTableModel().fireTableDataChanged();
        frame.getInvoiceHeaderJTableModel().fireTableDataChanged();
        frame.getInvoiceTotalJLabel().setText("" + invoice.getInvoiceTotal());
        displayInvoices();
    }

    public void cancelButtonNewLineInDialog() {
        frame.getSaveLineDialog().setVisible(false);
        frame.getSaveLineDialog().dispose();
        frame.setSaveLineDialog(null);
    }

    public void cancelButton() {
        int lineIndex = frame.getInvoiceItemJTableRightSide().getSelectedRow();
        InvoiceLine line = frame.getInvoiceLineJTableModel().getInvoicesLinesList().get(lineIndex);
        frame.getInvoiceLineJTableModel().getInvoicesLinesList().remove(lineIndex);
        frame.getInvoiceLineJTableModel().fireTableDataChanged();
        frame.getInvoiceHeaderJTableModel().fireTableDataChanged();
        frame.getInvoiceTotalJLabel().setText("" + line.getInvoiceHeader().getInvoiceTotal());
    }
}
