/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listner.listnerMenuBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import salesinvoicegenerator.Frame;

/**
 *
 * @author karim
 */
public class SaveInvoiceActionListner {
    
    
    public SaveInvoiceActionListner (Frame frame) {
        // save 2 csv files

        BufferedWriter bwFile1 = null;
        BufferedWriter bwFile2 = null;

        try {
            File file = new File(frame.path1);
            bwFile1 = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            for (int i = 0; i < frame.invoicesTable.getRowCount(); i++) {
                for (int j = 0; j < frame.invoicesTable.getColumnCount(); j++) {
                    String s = (String) frame.invoicesTable.getModel().getValueAt(i, j);
                    if (s == null) {
                        bwFile1.write(" ");
                    } else {
                        bwFile1.write(s + ",");
                    }
                }
                bwFile1.write("\n");
            }
             File file2 = new File(frame.path2);
            bwFile2 = new BufferedWriter(new FileWriter(file2.getAbsoluteFile()));
            for (int i = 0; i < frame.invoiceItemsTable.getRowCount(); i++) {
                for (int j = 0; j < frame.invoiceItemsTable.getColumnCount(); j++) {
                    String s = (String) frame.invoiceItemsTable.getModel().getValueAt(i, j);
                    if (s == null) {
                        bwFile2.write(" ");
                    } else {
                        bwFile2.write(s + ",");
                    }
                }
                bwFile2.write("\n");
            }

        } catch (IOException es) {
            JOptionPane.showMessageDialog(null, es.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
        } finally {
            try {
                bwFile1.close();
                bwFile2.close();
            } catch (IOException es) {
                JOptionPane.showMessageDialog(null, es.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);

            }

        }
        JOptionPane.showMessageDialog(null, "Two files saved sucessfully");
    }
}
