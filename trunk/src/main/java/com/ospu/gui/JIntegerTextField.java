package com.ospu.gui;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * JIntegerTextField.
 *
 * @author vkolodrevskiy
 */
public class JIntegerTextField extends JTextField {

    final static String badchars
            = "`~!@#$%^&*()_+=\\|\"':;?/>.<, ";

    public void processKeyEvent(KeyEvent ev) {

        char c = ev.getKeyChar();

        if((Character.isLetter(c) && !ev.isAltDown())
                || badchars.indexOf(c) > -1) {
            ev.consume();
            return;
        }
        if(c == '-' && getDocument().getLength() > 0)
            ev.consume();
        else super.processKeyEvent(ev);
    }
}

