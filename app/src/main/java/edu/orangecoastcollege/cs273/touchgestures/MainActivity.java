package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Define a GestureDetector to listen to events on the ScrollView
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapsTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapsTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressesTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollsTextView);
        flingTextView = (TextView) findViewById(R.id.flingsTextView);

        // Hook up the GestureDetector to the ScrollView
        // 4 out of 5 gestures handled, double tap is not handled yet
        gestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
        // Special Case: Double Tap
        gestureDetector.setOnDoubleTapListener(this);
    }

    // Dispatches all touch events from the Activity to the ScrollView
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        singleTaps++;
        // Append to our GestureLog (since setText() will wipe out everything in the TextView)
        singleTapTextView.setText(String.valueOf(singleTaps));
        gesturesLogTextView.append("\nonSingleTouchConfirmed touch event.");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap touch event");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        doubleTaps++;
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        gesturesLogTextView.append("\nonDoubleTapConfirmed touch event");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown touch event");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress touch event");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleTapUp touch event");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        scrolls++;
        scrollTextView.setText(String.valueOf(scrolls));
        gesturesLogTextView.append("\nonScroll: distanceX is " + v + " distanceY is" + v1);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        longPresses++;
        longPressTextView.setText(String.valueOf(longPresses));
        gesturesLogTextView.append("\nonLongPress touch event");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        flings++;
        flingTextView.setText(String.valueOf(flings));
        gesturesLogTextView.append("\nonFling: velocityX is " + v + " velocityY is" + v1);
        return false;
    }

    public void clearAll(View view) {
        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;

        gesturesLogTextView.setText("");

        singleTapTextView.setText("0");
        doubleTapTextView.setText("0");
        longPressTextView.setText("0");
        flingTextView.setText("0");
        scrollTextView.setText("0");
    }
}