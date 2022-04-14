package salesinvoicegenerator;

import listner.listnerMenuBar.LoadInvoiceActionListner;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import listner.listnerActionButton.CancelActionButton;
import listner.listnerActionButton.CreateNewInvoiceActionButton;
import listner.listnerActionButton.DeleteInvoiceActionButton;
import listner.listnerMenuBar.LoadInvoiceActionListner;
import listner.listnerActionButton.SaveActionButton;
import listner.listnerMenuBar.SaveInvoiceActionListner;

public class Frame extends JFrame implements ActionListener {

//    LoadInvoiceActionListner loadInvoiceListner;
    
    // Left Side Pane
    private JPanel leftSidePanel;
    private JPanel leftSideTopPanel;
    private JPanel leftSideBottomPanel;

    // Right Side Pane
    private JPanel rightSidePanel;
    private JPanel rightSideTopPanel;
    private JPanel rightSideBottomPanel;

    private JPanel tableInvoiceContainerPane;
    private JPanel invoiceNumPanel;
    private JPanel invoiceDatePanel;
    private JPanel customerNamePanel;
    private JPanel invoiceTotalPanel;
    private JPanel invoiceItemsTableLabelPanel;

    // Table Left
    public JTable invoicesTable;
    private JScrollPane scrollPaneInvoiceTable;

    // Table Right    
    public JTable invoiceItemsTable;
    private JScrollPane scrollPaneInvoiceItems;

    // Label Left Side
    private JLabel invoicesTableLabel;

    // Label Right Side    
    private JLabel invoiceItemsLabel;
    private JLabel invoiceNumLabel;
    public JTextField invoiceNumValue;

    private JLabel invoiceDateLabel;
    public JTextField invoiceDateInput;

    private JLabel customerNameLabel;
    public JTextField customerNameInput;

    private JLabel invoiceTotalLabel;
    public JTextField invoiceTotalValue;

    // Buttons Left Side
    private JButton createNewInvoiceBtn;
    private JButton deleteInvoiceBtn;

    // Buttons Right Side    
    private JButton saveChangesBtn;
    private JButton cancelChangesBtn;

    private JOptionPane errorMsgs;

    
    private String filePath;
    private String[][] invoicesData;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadInvoiceMenuItem;
    private JMenuItem saveInvoiceMenuItem;
    private JMenuItem closeMenuItem;

    public String path1 = "./InvoiceHeader.csv";
    public String path2 = "./InvoiceLine.csv";
    
    public DefaultTableModel headerModel;
    public DefaultTableModel lineModel;

    private DateFormat dateFt = new SimpleDateFormat("dd-MM-yyyy");


    public Frame() {
        super("Sales Invoice Generator");

        setLayout(new GridLayout(1, 2, 25, 0));

//        loadInvoiceListner = new LoadInvoiceActionListner(this);


        /*
        * Left Side Initialize
         */
        leftSidePanel = new JPanel();
        leftSidePanel.setBorder(new EmptyBorder(15, 12, 10, 0)); // Padding Border
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel, BoxLayout.Y_AXIS)); // Layout Left Side Panel

        // Left Top Side
        leftSideTopPanel = new JPanel(new GridLayout(2, 2, 0, -40));
        invoicesTableLabel = new JLabel("Invoices Table");
        invoicesTableLabel.setAlignmentX(CENTER_ALIGNMENT);
        invoicesTableLabel.setFont(new Font("Arial", WIDTH, 80));

        tableInvoiceContainerPane = new JPanel();
        tableInvoiceContainerPane.setLayout(new BoxLayout(tableInvoiceContainerPane, BoxLayout.Y_AXIS));
        tableInvoiceContainerPane.setPreferredSize(new Dimension(200, 90));

        // Left Bottom Side
        leftSideBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));

        // Create New Invoice
        createNewInvoiceBtn = new JButton("Create New Invoice");
        createNewInvoiceBtn.setBackground(Color.BLACK);
        createNewInvoiceBtn.setForeground(Color.WHITE);
        createNewInvoiceBtn.setMargin(new Insets(8, 13, 8, 13));
        createNewInvoiceBtn.setPreferredSize(new Dimension(150, 38));
        createNewInvoiceBtn.setActionCommand("createNewInvoice");
        createNewInvoiceBtn.addActionListener(this);

        // Delete Invoice
        deleteInvoiceBtn = new JButton("Delete Invoice");
        deleteInvoiceBtn.setBackground(Color.BLACK);
        deleteInvoiceBtn.setForeground(Color.WHITE);
        deleteInvoiceBtn.setMargin(new Insets(8, 13, 8, 13));
        deleteInvoiceBtn.setPreferredSize(new Dimension(150, 38));
        deleteInvoiceBtn.setActionCommand("deleteButton");
        deleteInvoiceBtn.addActionListener(this);


        
        /*
        * Right Side Initialize
         */
        rightSidePanel = new JPanel();
        rightSidePanel.setBorder(new EmptyBorder(15, 12, 10, 15)); // Padding Border
        rightSidePanel.setLayout(new BoxLayout(rightSidePanel, BoxLayout.Y_AXIS)); // Layout Left Side Panel

        // Right Top Side Panel
        rightSideTopPanel = new JPanel();
        rightSideTopPanel.setLayout(new BoxLayout(rightSideTopPanel, BoxLayout.Y_AXIS));

        // Invoice Number
        invoiceNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceNumLabel = new JLabel("Invoice Number");
        invoiceNumLabel.setFont(new Font("Arial", WIDTH, 14));
        invoiceNumLabel.setPreferredSize(new Dimension(130, 15));

        invoiceNumValue = new JTextField("0", 10);
        invoiceNumValue.setFont(new Font("Arial Italic", WIDTH, 14));
        invoiceNumValue.setBorder(null);
        invoiceNumValue.setBackground(new java.awt.Color(240, 240, 240));

        // Invoice Date
        invoiceDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceDateLabel = new JLabel("Invoice Date");
        invoiceDateLabel.setFont(new Font("Arial", WIDTH, 14));
        invoiceDateLabel.setPreferredSize(new Dimension(130, 15));

        invoiceDateInput = new JTextField(40);
        invoiceNumValue.setBorder(null);
        invoiceNumValue.setBackground(new java.awt.Color(240, 240, 240));

        // Customer Name
        customerNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        customerNameLabel = new JLabel("Customer Name");
        customerNameLabel.setFont(new Font("Arial", WIDTH, 14));
        customerNameLabel.setPreferredSize(new Dimension(130, 15));

        customerNameInput = new JTextField(40);

        // Invoice Total
        invoiceTotalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceTotalLabel = new JLabel("Invoice Total");
        invoiceTotalLabel.setFont(new Font("Arial", WIDTH, 14));
        invoiceTotalLabel.setPreferredSize(new Dimension(130, 15));

        invoiceTotalValue = new JTextField("0.00", 10);
        invoiceTotalValue.setFont(new Font("Arial Italic", WIDTH, 14));
        invoiceTotalValue.setBorder(null);
        invoiceTotalValue.setBackground(new java.awt.Color(240, 240, 240));

        // Invoice Items Table
        invoiceItemsTableLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        invoiceItemsLabel = new JLabel("Invoice Items");
        invoiceItemsLabel.setFont(new Font("Arial", WIDTH, 20));

        // Right Bottom Side
        rightSideBottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));

        // Save Button
        saveChangesBtn = new JButton("Save");
        saveChangesBtn.setBackground(Color.BLACK);
        saveChangesBtn.setForeground(Color.WHITE);
        saveChangesBtn.setMargin(new Insets(8, 13, 8, 13));
        saveChangesBtn.setPreferredSize(new Dimension(150, 38));
        saveChangesBtn.setActionCommand("saveButton");
        saveChangesBtn.addActionListener(this);

        // Cancel Button
        cancelChangesBtn = new JButton("Cancel");
        cancelChangesBtn.setBackground(Color.BLACK);
        cancelChangesBtn.setForeground(Color.WHITE);
        cancelChangesBtn.setMargin(new Insets(8, 13, 8, 13));
        cancelChangesBtn.setPreferredSize(new Dimension(150, 38));
        cancelChangesBtn.setActionCommand("cancelButton");
        cancelChangesBtn.addActionListener(this);


        invoicesTable = new JTable();
        scrollPaneInvoiceTable = new JScrollPane();
        invoicesTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "No.", "Date", "Customer", "Total"
                }
        ));

        invoicesTable.setColumnSelectionAllowed(false);
        invoicesTable.setRowSelectionAllowed(true);
        scrollPaneInvoiceTable.setViewportView(invoicesTable);
        invoicesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        invoiceItemsTable = new JTable();
        scrollPaneInvoiceItems = new JScrollPane();
        invoiceItemsTable.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "No.", "Item Name", "Item Price", "Count", "Item Total"
                }
        ));
        invoicesTable.setRowSelectionAllowed(true);
        scrollPaneInvoiceItems.setViewportView(invoicesTable);
        invoicesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        errorMsgs = new JOptionPane();

        /*
        * Adds To Frame
         */
        // Menu Bar
        addMenuBar();

        // Left Side
        add(leftSidePanel);
        leftSidePanel.add(leftSideTopPanel);
        leftSideTopPanel.add(tableInvoiceContainerPane);
        tableInvoiceContainerPane.add(invoicesTableLabel);
        leftSidePanel.add(new JScrollPane(invoicesTable));
        leftSideBottomPanel.add(createNewInvoiceBtn);
        leftSideBottomPanel.add(deleteInvoiceBtn);
        leftSidePanel.add(leftSideBottomPanel);

        // Right Side
        add(rightSidePanel);
        invoiceNumPanel.add(invoiceNumLabel);
        invoiceNumPanel.add(invoiceNumValue);

        invoiceDatePanel.add(invoiceDateLabel);
        invoiceDatePanel.add(invoiceDateInput);

        customerNamePanel.add(customerNameLabel);
        customerNamePanel.add(customerNameInput);

        invoiceTotalPanel.add(invoiceTotalLabel);
        invoiceTotalPanel.add(invoiceTotalValue);

        rightSideTopPanel.add(invoiceNumPanel);
        rightSideTopPanel.add(invoiceDatePanel);
        rightSideTopPanel.add(customerNamePanel);
        rightSideTopPanel.add(invoiceTotalPanel);

        invoiceItemsTableLabelPanel.add(invoiceItemsLabel);

        rightSideTopPanel.add(invoiceItemsTableLabelPanel);

        rightSidePanel.add(rightSideTopPanel);

        rightSidePanel.add(new JScrollPane(invoiceItemsTable));

        rightSideBottomPanel.add(saveChangesBtn);
        rightSideBottomPanel.add(cancelChangesBtn);

        rightSidePanel.add(rightSideBottomPanel);

        setSize(1300, 700);
        setLocation(30, 30);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    // function to appear menuBar
    private void addMenuBar() {

        loadInvoiceMenuItem = new JMenuItem("Load Invoice");
        loadInvoiceMenuItem.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        loadInvoiceMenuItem.addActionListener(this);
        loadInvoiceMenuItem.setActionCommand("load");

        saveInvoiceMenuItem = new JMenuItem("Save Invoice");
        saveInvoiceMenuItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        saveInvoiceMenuItem.addActionListener(this);
        saveInvoiceMenuItem.setActionCommand("save");

        closeMenuItem = new JMenuItem("Close");
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke('W', InputEvent.CTRL_DOWN_MASK));
        closeMenuItem.addActionListener(this);
        closeMenuItem.setActionCommand("close");

        fileMenu = new JMenu("File");
        fileMenu.add(loadInvoiceMenuItem);
        fileMenu.add(saveInvoiceMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(closeMenuItem);

        menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "load":
                loadInvoiceItem();
                break;
            case "save":
                saveInvoiceItem();
                break;
            case "close":
                closeInvoiceItem();
                break;
            case "createNewInvoice":
                createNewInvoiceActionButtonMethod();
                break;
            case "saveButton":
                saveActionButtonMethod();
                break;
            case "deleteButton":
                deleteInvoiceActionButtonMethod();
                break;
            case "cancelButton":
                cancelActionButtonMethod();
                break;
        }
    }

    private void loadInvoiceItem() {
       new LoadInvoiceActionListner(this);
    }

    private void saveInvoiceItem() {
        new SaveInvoiceActionListner(this);
    }

    private void closeInvoiceItem() {
        System.exit(0);
    }
    
    private void createNewInvoiceActionButtonMethod() {
        new CreateNewInvoiceActionButton(this);
    }

    private void deleteInvoiceActionButtonMethod() {
        new DeleteInvoiceActionButton(this);
    }
    
    private void saveActionButtonMethod() {
        new SaveActionButton(this);
    }
    
    private void cancelActionButtonMethod() {
        new CancelActionButton(this);
    }
}
