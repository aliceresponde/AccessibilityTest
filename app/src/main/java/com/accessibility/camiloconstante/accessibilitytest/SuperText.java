package com.accessibility.camiloconstante.accessibilitytest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by camilo.constante on 29/11/2016.
 */
public class SuperText extends TextView {

    public SuperText(Context context) {
        super(context);
    }

    public SuperText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAccessibilityLiveRegion(int mode) {
        super.setAccessibilityLiveRegion(mode);
    }

}
