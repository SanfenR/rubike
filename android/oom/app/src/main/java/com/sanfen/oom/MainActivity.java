package com.sanfen.oom;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_heapSize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
                int heapSize = manager.getMemoryClass();
                int maxHeapSize = manager.getLargeMemoryClass();
                Log.d(TAG, "heapSize" + heapSize + "maxHeapSize" + maxHeapSize);

            }
        });
    }
}
