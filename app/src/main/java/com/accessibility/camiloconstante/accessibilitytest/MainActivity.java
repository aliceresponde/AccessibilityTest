package com.accessibility.camiloconstante.accessibilitytest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton btn1;
    TextView coolText;
    TextView tvSpannable;
    CheckBox checkbox;
    TextView numbers;
    LinearLayout container;
    TextView text1;
    TextView text2;
    TextView replaceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (ImageButton) findViewById(R.id.btn1);
        btn1.setContentDescription("Content Programmatically");

        coolText = (TextView) findViewById(R.id.coolText);
        tvSpannable = (TextView) findViewById(R.id.tvSpannable);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        numbers = (TextView) findViewById(R.id.numbers);
        container = (LinearLayout) findViewById(R.id.container);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        replaceText = (TextView) findViewById(R.id.replaceText);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coolText.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
            }
        });
        checkbox.setContentDescription("mente demente");
        //makeSpannable();

        //getDescriptionWithIndividualChars(numbers, "ID Ending 321");

        //container.setContentDescription(text1.getContentDescription().toString() + text2.getText());

        setContainerDescription(container);
        String firstText = replaceText.getText().toString();
        String secondText = firstText.replaceAll("\\b1\\b", "2");
        replaceText.setText(secondText);
    }

    public void setContainerDescription(ViewGroup container) {
        int childCount = container.getChildCount();
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < childCount; index++) {
            View groupCurrentView = container.getChildAt(index);
            CharSequence sequence;
            if ((sequence = groupCurrentView.getContentDescription()) != null) {
                builder.append(sequence.toString());
            } else {
                if (groupCurrentView instanceof TextView) {
                    sequence = ((TextView) groupCurrentView).getText();
                    builder.append(sequence);
                }
            }
        }
        container.setContentDescription(builder.toString());
    }

    private void makeSpannable() {

        Spannable spannableText1 = new SpannableString("Text");
        spannableText1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpannable.setText(spannableText1);
        ClickableSpan clickableSpan = new IDFinderClickableSpan();
        Spannable spannableLink1 = new SpannableString("This is my link");
        spannableLink1.setSpan(clickableSpan, 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpannable.append(spannableLink1);
        tvSpannable.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void getDescriptionWithIndividualChars(View view, String description) {

        String stringOne = description.replaceAll("[^0-9]", "\u00A0");
        String[] stringTwo = stringOne.split("\u00A0");

        //StringBuilder builder1 = new StringBuilder();
        for (int index1 = 0; index1 < stringTwo.length; index1++) {
            StringBuilder builder2 = new StringBuilder();
            char[] chars = stringTwo[index1].toCharArray();
            for (int index2 = 0; index2 < chars.length; index2++) {
                builder2.append(chars[index2]);
                builder2.append("\u00A0");
            }
            //builder1.append(builder2.toString());
            description = description.replace(stringTwo[index1], builder2.toString());
        }

        Toast.makeText(this, description, Toast.LENGTH_LONG).show();

    }

    private class IDFinderClickableSpan extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            Toast.makeText(MainActivity.this, "WOW", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setUnderlineText(false);
            textPaint.setColor(getResources().getColor(R.color.colorAccent));
        }
    }

}
