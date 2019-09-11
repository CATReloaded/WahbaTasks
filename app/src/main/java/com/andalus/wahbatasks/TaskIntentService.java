package com.andalus.wahbatasks;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class TaskIntentService extends IntentService {


    private static final String TAG=TaskIntentService.class.getName();

    public TaskIntentService() {
        super("TaskIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent)
    {
        String action =intent.getAction();
        PlayAudio.specifyStatus(this, action);
        Log.d(TAG, action);
    }


}
