package com.andalus.wahbatasks;

import android.app.IntentService;
import android.content.Intent;

public class TaskIntentService extends IntentService {



    public TaskIntentService(String name) {
        super("TaskIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        String action =intent.getAction();
        PlayAudio.specifyStatus(this, action);
    }


}
