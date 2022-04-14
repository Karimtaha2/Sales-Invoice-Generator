/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listner.listnerActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import salesinvoicegenerator.Frame;

/**
 *
 * @author karim
 */
public class CancelActionButton {

    public CancelActionButton(Frame frame) {
        // undo changes , read the file again
        frame.lineModel = (DefaultTableModel) frame.invoiceItemsTable.getModel();

        if (frame.invoiceItemsTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Empty Table, please load a txt file");
        } else {
            frame.lineModel.setNumRows(0);
            BufferedReader br2 = null;
            try {
                File file2 = new File(frame.path2);
                br2 = new BufferedReader(new FileReader(file2));
                Object[] tableLines = br2.lines().toArray();
                for (int i = 0; i < tableLines.length; i++) {
                    String line = tableLines[i].toString().trim();
                    String[] dataRow = line.split(",");
                    frame.lineModel.addRow(dataRow);
                }
            } catch (IOException ec) {
                    JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
            } finally {
                try {
                    br2.close();
                } catch (IOException ec) {
                    JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }

    }
}
