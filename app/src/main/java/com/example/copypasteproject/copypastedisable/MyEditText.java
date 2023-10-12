package com.example.copypasteproject.copypastedisable;

import android.content.Context;
import android.util.AttributeSet;

import java.util.ArrayList;


public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {
    ArrayList<MyEditTextListener> listeners;

    public MyEditText(Context context) {
        super(context);
        listeners = new ArrayList<>();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        listeners = new ArrayList<>();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        listeners = new ArrayList<>();
    }

    public void addListener(MyEditTextListener listener) {
        try {
            listeners.add(listener);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onTextContextMenuItem(int id) {
        boolean consumed = super.onTextContextMenuItem(id);
        switch (id) {
            case android.R.id.cut:
                onTextCut();
                break;
            case android.R.id.paste:
                onTextPaste();
                break;
            case android.R.id.copy:
                onTextCopy();
        }
        return consumed;
    }

    public void onTextCut() {
    }

    public void onTextCopy() {

    }

    /**
     * adding listener for Paste for example
     */
    public void onTextPaste() {
        for (MyEditTextListener listener : listeners) {
            listener.onUpdate();
        }
    }

}