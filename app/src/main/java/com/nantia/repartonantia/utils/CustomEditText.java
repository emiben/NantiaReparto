package com.nantia.repartonantia.utils;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by Emi on 3/6/2018.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
    }

    @Override
    public boolean isSuggestionsEnabled() {
        return false;
    }
}
