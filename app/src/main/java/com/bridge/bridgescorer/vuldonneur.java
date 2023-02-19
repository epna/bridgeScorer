package com.bridge.bridgescorer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class vuldonneur extends View {
    Integer attrNumDOnne;
    public vuldonneur(Context context, AttributeSet attrs) {
        super ( context, attrs );
        Log.d("TAG","ici");


        final TypedArray a = getContext ().obtainStyledAttributes (
                attrs, R.styleable.vuldonneur, 0, 0 );

        attrNumDOnne = a.getInt (
                R.styleable.vuldonneur_numDonne,4 );
        a.recycle ();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw ( canvas );
        Paint paint = new Paint();
        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int[] arrVulnerabilite = getContext ().getResources ().getIntArray ( R.array.vulnerabilite );
        RectF rectf = new RectF (0, 0, 100, 100);
        int limit16=attrNumDOnne;

        while (limit16>16) limit16=limit16-16;
        limit16=limit16-1;

        if (arrVulnerabilite[limit16]==clConstant.PERSONNE) {
            paint.setColor ( Color.GREEN );
            canvas.drawArc ( rectf, 0 + 45, 90, true, paint );
            canvas.drawArc ( rectf, 135, 90, true, paint );
            canvas.drawArc ( rectf, 225, 90, true, paint );
            canvas.drawArc ( rectf, 315, 90, true, paint );
        }
        if (arrVulnerabilite[limit16]==clConstant.NS) {
            paint.setColor ( Color.GREEN );
            canvas.drawArc ( rectf, 135, 90, true, paint );
            canvas.drawArc ( rectf, 315, 90, true, paint );
            paint.setColor ( Color.RED );
            canvas.drawArc ( rectf, 0 + 45, 90, true, paint );
            canvas.drawArc ( rectf, 225, 90, true, paint );
        }
        if (arrVulnerabilite[limit16]==clConstant.EO) {
            paint.setColor ( Color.GREEN );
            canvas.drawArc ( rectf, 0 + 45, 90, true, paint );
            canvas.drawArc ( rectf, 225, 90, true, paint );
            paint.setColor ( Color.RED );
            canvas.drawArc ( rectf, 135, 90, true, paint );
            canvas.drawArc ( rectf, 315, 90, true, paint );
        }
        if (arrVulnerabilite[limit16]==clConstant.TOUS) {
            paint.setColor ( Color.RED );
            canvas.drawArc ( rectf, 135, 90, true, paint );
            canvas.drawArc ( rectf, 315, 90, true, paint );
            canvas.drawArc ( rectf, 0 + 45, 90, true, paint );
            canvas.drawArc ( rectf, 225, 90, true, paint );
        }
        paint.setTextSize ( 20 );
        paint.setColor ( Color.WHITE);
        if ((attrNumDOnne-1) % 4 == 0 ) canvas.drawCircle ( 50,20, 10,paint ); //N
        if ((attrNumDOnne-1) % 4 == 1 ) canvas.drawCircle ( 80,50, 10,paint ); //E
        if ((attrNumDOnne-1) % 4 == 2 ) canvas.drawCircle ( 50,80, 10,paint ); //S
        if ((attrNumDOnne-1) % 4 == 3 ) canvas.drawCircle ( 20,50, 10,paint ); //O
    }

 }