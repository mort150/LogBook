package com.example.logbook.tools;

import android.widget.EditText;

public class InputTool {
    public static boolean validate(EditText... editTexts) {
        if (editTexts == null || editTexts.length == 0) {
            return false;
        } else {
            for (EditText editText : editTexts) {
                if (editText.getText().toString().isEmpty()) {
                    editText.setError("Invalid input. Please enter again!!!");
                    return false;
                }
            }
            return true;
        }
    }
}
