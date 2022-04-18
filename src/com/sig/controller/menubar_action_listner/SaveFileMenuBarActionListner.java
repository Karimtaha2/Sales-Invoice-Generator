/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sig.controller.menubar_action_listner;

import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.view.MyFrame;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author karim taha
 */
public class SaveFileMenuBarActionListner {
    
    private MyFrame frame;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // day - month - year

    public SaveFileMenuBarActionListner(MyFrame frame) {this.frame = frame;}
    
    public void saveFileMenuBar() {
        String headers = "";
        String lines = "";
        for (InvoiceHeader header : frame.getInvoicesHeaderList()) {
            headers += header.getDataAsCSV();
            headers += "\n";
            for (InvoiceLine line : header.getInvoiceLines()) {
                lines += line.getDataAsCSV();
                lines += "\n";
            }
        }

        JOptionPane.showMessageDialog(frame, "Please, select file to save header data.", "Header File", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter filewriterl = new FileWriter(headerFile);
                filewriterl.write(headers);
                filewriterl.flush();
                filewriterl.close();

                JOptionPane.showMessageDialog(frame, "Please, select file to save lines data.", "Lines File", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter filewriterh = new FileWriter(linesFile);
                    filewriterh.write(lines);
                    filewriterh.flush();
                    filewriterh.close();
                }
            } catch (Exception ex) {JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE);}
        }
        JOptionPane.showMessageDialog(frame, "Data saved successfully", "Saved", JOptionPane.INFORMATION_MESSAGE);
    }
}
