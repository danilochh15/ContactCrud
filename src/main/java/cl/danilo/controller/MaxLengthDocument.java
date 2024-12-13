package cl.danilo.controller;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLengthDocument extends PlainDocument {
    private int maxLength;

    public MaxLengthDocument(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        //validar si la longitud actual mas la nueva supera el limito
        if ((getLength() + str.length()) <= maxLength) {
            super.insertString(offset, str, attr);
        }
    }
}