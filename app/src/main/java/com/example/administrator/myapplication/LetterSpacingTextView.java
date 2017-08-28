package com.example.administrator.myapplication;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 类 名 称：MyApplication
 * <P/>描    述：
 * <P/>创 建 人：CM
 * <P/>创建时间：2017/8/17
 * <P/>修 改 人：CM
 * <P/>修改时间：2017/8/17
 * <P/>修改备注：
 * <P/>版    本：
 */

public class LetterSpacingTextView extends TextView {

    private float letterSpacing = LetterSpacing.NORMAL;
    private CharSequence originalText = "";


    public LetterSpacingTextView(Context context) {
        super(context);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    public float getLetterSpacing() {
        return letterSpacing;
    }

    public void setLetterSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        applyLetterSpacing();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        applyLetterSpacing();
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    private void applyLetterSpacing() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < originalText.length(); i++) {
            builder.append(originalText.charAt(i));
            if(i+1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if(builder.toString().length() > 1) {
            for(int i = 1; i < builder.toString().length(); i+=2) {
                finalText.setSpan(new ScaleXSpan((letterSpacing+1)/10), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, BufferType.SPANNABLE);
    }

    public class LetterSpacing {
        public final static float NORMAL = 0;
    }
}
