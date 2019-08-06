package com.sanfen.aidl;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public int add(int aInt, int bInt) {
            return aInt + bInt;
        }

        @Override
        public int min(int aInt, int bInt) {
            return aInt < bInt ? aInt : bInt;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

}
