package com.msapps.gravitylayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GravityLayout mGravityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGravityLayout = (GravityLayout) findViewById(R.id.gravity_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGravityLayout.enable();
    }

    @Override
    protected void onStop() {
        mGravityLayout.disable();
        super.onStop();
    }
}
