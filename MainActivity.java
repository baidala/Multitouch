package com.example.student.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity  implements View.OnTouchListener {

    private static final String LOG_TAG = "Multi---";
    MultiTouchView multiTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        multiTouchView = new MultiTouchView(this, null);
        //multiTouchView.setOnTouchListener(this);

        setContentView(multiTouchView);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d(LOG_TAG, "Touch from MainActivity");

        return true;
    }
}
