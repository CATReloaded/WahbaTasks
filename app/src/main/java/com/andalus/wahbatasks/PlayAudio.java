package com.andalus.wahbatasks;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayAudio
{
    public static final String PLAY = "play_audio";
    public static final String PAUSE = "pause_audio";
    public static final String NEXT = "next_audio";
    public static final String PREVIOUS = "previous_audio";
    static MediaPlayer player;

    static int [] audioArray={R.raw.hi,R.raw.hello,
                                R.raw.color_black,R.raw.color_brown,
                                R.raw.color_dusty_yellow,R.raw.color_gray,
                                R.raw.color_green,R.raw.color_mustard_yellow,
                                R.raw.color_red,R.raw.color_white};

    public static void specifyStatus(Context context, String action)
    {
        if(action.equals(PLAY))
        {

            MediaPlayer x=MediaPlayer.create(context, R.raw.hi);
            x.start();

//
//            int index=PreferenceUtilities.getMediaPlayerIndex(context);
//            player=MediaPlayer.create(context, audioArray[index]);
//            player.start();
        }
        else if(action.equals(PAUSE))
        {
            player.pause();
        }
        else if(action.equals(NEXT))
        {
            player.release();
            PreferenceUtilities.incrementMediaPlayerIndex(context);
            int index=PreferenceUtilities.getMediaPlayerIndex(context);
            player=MediaPlayer.create(context, audioArray[index]);
            player.start();
        }
        else if(action.equals(PREVIOUS))
        {
            player.release();
            PreferenceUtilities.decrementMediaPlayerIndex(context);
            int index=PreferenceUtilities.getMediaPlayerIndex(context);
            player=MediaPlayer.create(context, audioArray[index]);
            player.start();
        }
    }
}
