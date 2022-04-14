/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listner.listnerActionButton;

import salesinvoicegenerator.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author karim
 */
public class LoadInvoiceActionListner  {
    // if you want to choose the 2 files
    //two path fields will be changed as you selected

    public LoadInvoiceActionListner (Frame frame) {

        BufferedReader brFile1 = null;
        BufferedReader brFile2 = null;
        frame.headerModel = (DefaultTableModel) frame.invoicesTable.getModel();
        int total = 0;
        int invoiceNumberInt = 1;

        JFileChooser fileChooser1 = new JFileChooser();
        int result = fileChooser1.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            frame.path1 = fileChooser1.getSelectedFile().getPath();

            try {
                brFile1 = new BufferedReader(new FileReader(new File(frame.path1)));
                Object[] tableLines = brFile1.lines().toArray(); // convert lines to array
                for (int i = 0; i < tableLines.length; i++) {
                    String line = tableLines[i].toString().trim();
                    String[] dataRow = line.split(",");
                    frame.headerModel.addRow(dataRow);
                }
                // add invoices lines
                JFileChooser fileChooser2 = new JFileChooser();
                int result2 = fileChooser2.showOpenDialog(frame);
                if (result2 == fileChooser2.APPROVE_OPTION) {
                    frame.path2 = fileChooser2.getSelectedFile().getPath();

                    brFile2 = new BufferedReader(new FileReader(new File(frame.path2)));
                    frame.lineModel = (DefaultTableModel) frame.invoiceItemsTable.getModel();
                    Object[] tableLines2 = brFile2.lines().toArray();
                    int[] n = new int[tableLines2.length];//  n = itemCount * itemPrice
                    int num;
                    int j = 0;
                    for (int i = 0; i < tableLines2.length; i++) {
                        String line = tableLines2[i].toString().trim();
                        String[] dataRow = line.split(",");
                        frame.lineModel.addRow(dataRow);
                        n[i] = Integer.parseInt(frame.lineModel.getValueAt(i, 2).toString()) * Integer.parseInt(frame.lineModel.getValueAt(i, 3).toString());

                        String invoiceNumberStr = dataRow[0];
                        num = n[i];

                        // add Tolal to header
                        if (Integer.parseInt(invoiceNumberStr) == invoiceNumberInt) {
                            total += num;
                        } else {
                            frame.headerModel.setValueAt(String.valueOf(total), j, 3);
                            invoiceNumberInt++;
                            j++;
                            total = 0;
                            total += num;
                            //System.out.println(ttt);
                        }
                        //end of adding Tolal to header

                        frame.lineModel.setValueAt(String.valueOf(num), i, 4);
                    }
                }

            } catch (Exception el) {
                JOptionPane.showMessageDialog(null, el.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
                System.out.println(el);
            } finally {
                try {
                    brFile1.close();
                    brFile2.close();
                } catch (IOException el) {
                    JOptionPane.showMessageDialog(null, el.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
                    System.out.println(el);
                }
            }
        }
    }
}
