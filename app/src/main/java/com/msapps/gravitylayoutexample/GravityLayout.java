package com.msapps.gravitylayoutexample;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by sujith on 10/10/16.
 */
public class GravityLayout extends FrameLayout implements SensorEventListener {

    private static final String TAG = "GravityLayout";

    private float mWidth;
    private float mHeight;
    private SensorManager mSensorManager;

    public GravityLayout(Context context) {
        super(context);
        init();
    }

    public GravityLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GravityLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Enable the gravity whenever you want (Mostly in activity onStart())
     */
    public void enable() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_GAME);
    }


    /**
     * Disable the gravity when the ui is not visible (Mostly in activity onStop())
     */
    public void disable() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int orientation = getResources().getConfiguration().orientation;
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRotationX(y * 2f);
            setRotationY(x * 2f);
        } else {
            setRotationX(x * 2f);
            setRotationY(-y * 2f);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        setPivotX(mWidth / 2);
        setPivotY(mHeight / 2);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
