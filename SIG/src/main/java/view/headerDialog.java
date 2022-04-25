/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author khalifa
 */
public class headerDialog extends JDialog{
    private JTextField customerNameField;
    private JTextField invDateField;
    private JLabel customerNameLbl;
    private JLabel invDateLbl;
    private JButton okButton;
    private JButton cancelButton;

    public headerDialog(InvoiceFrame frame) {
        customerNameLbl = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("newInvoiceOK");
        cancelButton.setActionCommand("newInvoiceCancel");
        
        okButton.addActionListener(frame.getActionListener());
        cancelButton.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(invDateLbl);
        add(invDateField);
        add(customerNameLbl);
        add(customerNameField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
    }

    public JTextField getCustNameField() {
        return customerNameField;
    }

    public JTextField getInvDateField() {
        return invDateField;
    }
    
}


