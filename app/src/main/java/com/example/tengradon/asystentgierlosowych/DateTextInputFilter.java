package com.example.tengradon.asystentgierlosowych;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tengradon on 2016-06-30.
 */
public class DateTextInputFilter implements InputFilter {

    private Pattern dateFormat;
    private static final String DATE_REGEX = "\\d{4}\\-\\d{2}\\-\\d{2}";

    public DateTextInputFilter(){
        this.dateFormat = Pattern.compile(DATE_REGEX);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String tekstDoAnalizy = dest.subSequence(0, dstart).toString() + source.subSequence(start, end) + dest.subSequence(dend, dest.length()).toString();
        Matcher matcher = dateFormat.matcher(tekstDoAnalizy);
        if(!matcher.matches()){
            if(!matcher.hitEnd()){
                return "";
            }
        }
        return null;
    }
}
