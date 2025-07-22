package com.practice.ui;

import javax.swing.JTextField;

public class InputTextField extends JTextField{

    public InputTextField() {
        setOpaque( false );
        setColumns( 10 );
        setBorder( null );
        setSize( getPreferredSize() );
        setColumns( 0 );
    }
}
