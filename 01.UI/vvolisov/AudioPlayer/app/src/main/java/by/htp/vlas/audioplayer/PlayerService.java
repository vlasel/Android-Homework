package by.htp.vlas.audioplayer;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

/**
 * Created by VlasEL on 23.02.2015 14:18
 */
public class PlayerService extends IntentService{

    public final static String ACTION_PLAY = "by.htp.vlas.audioplayer.ACTION_PLAY";
    public final static String ACTION_PAUSE = "by.htp.vlas.audioplayer.ACTION_PAUSE";
    public final static String ACTION_STOP = "by.htp.vlas.audioplayer.ACTION_STOP";

    private final MediaPlayer sMediaPlayer = new MediaPlayer();

    public PlayerService() {
        super(PlayerService.class.getSimpleName());

    }

    @Override
    protected void onHandleIntent(Intent intent) {

        switch (intent.getAction()) {
            case ACTION_PLAY:
                play();
                break;

            case ACTION_PAUSE:
                pause();
                break;

            case ACTION_STOP:
                stop();
                break;

        }

    }

    private void play(){
        sMediaPlayer.start();
    }

    private void pause(){
        if(sMediaPlayer.isPlaying()) {
            sMediaPlayer.pause();
            sMediaPlayer.release();
        }
    }

    private void stop(){
        if(sMediaPlayer.isPlaying()) {
            sMediaPlayer.stop();
        }
    }
}
