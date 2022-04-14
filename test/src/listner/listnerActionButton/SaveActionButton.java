/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listner.listnerActionButton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JOptionPane;
import salesinvoicegenerator.Frame;

/**
 *
 * @author karim
 */
public class SaveActionButton {

    public SaveActionButton(Frame frame) {

        // save invoice items table in the specified csv file
        if (frame.invoiceItemsTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Empty Table, please load a txt file");
        } else {

            BufferedWriter bwFile2 = null;
            File file2 = null;
            try {
                file2 = new File(frame.path2);
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
                JOptionPane.showMessageDialog(null, "Saved successfully");

            } catch (IOException es) {
                JOptionPane.showMessageDialog(null, es.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
            }
            try {
                bwFile2.close();
            } catch (IOException es) {
                JOptionPane.showMessageDialog(null, es.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
            }

        }
    }

}
