package com.andalus.wahbatasks;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

public class PlayAudio
{
    public static final String PLAY = "play_audio";
    public static final String PAUSE = "pause_audio";
    public static final String NEXT = "next_audio";
    public static final String PREVIOUS = "previous_audio";
    private static final String TAG=PlayAudio.class.getName();
    static MediaPlayer player;

    static int [] audioArray={R.raw.color_white,R.raw.hello,
                                R.raw.color_black,R.raw.color_brown,
                                R.raw.color_dusty_yellow,R.raw.color_gray,
                                R.raw.color_green,R.raw.color_mustard_yellow,
                                R.raw.color_red,R.raw.color_white};

    public static void specifyStatus(Context context, String action)
    {
        if(action.equals(PLAY))
        {
            Toast.makeText(context, "hello from the other side", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "hi");

            if(player !=null) player.release();

            player=MediaPlayer.create(context, audioArray[0]);
            player.start();
        }
        else if(action.equals(PAUSE))
        {
            player.pause();
        }
        else if(action.equals(NEXT))
        {
            if(player !=null) player.release();

            player=MediaPlayer.create(context, audioArray[1]);
            player.start();
        }
        else if(action.equals(PREVIOUS))
        {
            if(player !=null) player.release();

            player=MediaPlayer.create(context, audioArray[2]);
            player.start();
        }
    }
}
