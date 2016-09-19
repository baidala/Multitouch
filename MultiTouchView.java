package com.example.student.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by student on 19.09.2016.
 */
public class MultiTouchView extends View {

    private static final String LOG_TAG = "Multi---";
    Paint paint;
    SparseArray<PointF> points;


    public MultiTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        points = new SparseArray<PointF>();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < points.size(); i++) {
            Random random = new Random(256);
            PointF pointF = points.valueAt(i);

            if( pointF != null ) {
                paint.setColor(Color.rgb(random.nextInt(), random.nextInt(), random.nextInt()));
                canvas.drawCircle(pointF.x, pointF.y, 50, paint);
            }

        }

        canvas.drawText("Total pointers:  " + points.size(), 10 , 40, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LOG_TAG, "Touch from MultiTouchView");
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                PointF point = new PointF();
                point.x = event.getX(pointerIndex);
                point.y = event.getY(pointerIndex);
                points.put(pointerId, point);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                points.remove(pointerId);
                break;

            case MotionEvent.ACTION_MOVE:
                PointF pointF = points.get(event.getPointerId(pointerId));
                pointF.x = event.getX(pointerId);
                pointF.y = event.getY(pointerId);
                break;

        }

        invalidate();

        return true;

    }
}
