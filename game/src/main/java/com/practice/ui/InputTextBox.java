package com.practice.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public abstract class InputTextBox implements ActionListener {
    public JTextField textField;
    protected String text = "";

    public InputTextBox() {
        this.textField = new JTextField(10);
        textField.addActionListener(this);

    }

    public void setTextBoxVisible(boolean bool) {
        textField.setVisible(bool);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        text = textField.getText();
        System.out.println(text);
        textField.setText("");
        textField.setVisible(false);

    }

    public String getText() {
        return text;
    }
}
