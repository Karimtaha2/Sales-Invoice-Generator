/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author karim taha
 */
public class CreateNewInvoiceHeaderDialog extends JDialog {

    private JLabel customerNameJLabel;
    private JTextField customerNameJTextField;

    private JLabel invoiceDateJLabel;
    private JTextField invoiceDateJTextField;

    private JButton insertJButton;
    private JButton cancelJButton;

    public CreateNewInvoiceHeaderDialog(MyFrame frame) {

        setTitle("Create New Invoice");
        setLocation(300, 300);
        
        customerNameJLabel = new JLabel("Customer Name");
        customerNameJTextField = new JTextField(15);

        invoiceDateJLabel = new JLabel("Invoice Date");
        invoiceDateJTextField = new JTextField(15);

        insertJButton = new JButton("Insert");
        insertJButton.setActionCommand("InsertButtonInDialog");
        insertJButton.addActionListener(frame.getListner());

        cancelJButton = new JButton("Cancel");
        cancelJButton.setActionCommand("CancelButtonInDialog");
        cancelJButton.addActionListener(frame.getListner());

        setLayout(new GridLayout(3, 2, 15, 15));

        add(customerNameJLabel);
        add(customerNameJTextField);

        add(invoiceDateJLabel);
        add(invoiceDateJTextField);

        add(insertJButton);
        add(cancelJButton);

        pack();
    }

    public JTextField getCustomerNameJTextField() {return customerNameJTextField;}

    public JTextField getInvoiceDateJTextField() {return invoiceDateJTextField;}
}
