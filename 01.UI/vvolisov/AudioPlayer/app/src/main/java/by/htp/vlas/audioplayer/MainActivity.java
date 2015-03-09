package by.htp.vlas.audioplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by VlasEL on 23.02.2015 14:15
 */
public class MainActivity extends Activity {

    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnPlayPause = (Button) findViewById(R.id.btn_play_pause);
        Button btnStop = (Button) findViewById(R.id.btn_stop);

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(MainActivity.this, PlayerService.class);
                if (isPlaying) {
                    playIntent.setAction(PlayerService.ACTION_PAUSE);
                    btnPlayPause.setText(getString(R.string.btn_play));
                } else {
                    playIntent.setAction(PlayerService.ACTION_PLAY);
                    btnPlayPause.setText(getString(R.string.btn_pause));
                }
                isPlaying = !isPlaying;
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(MainActivity.this, PlayerService.class);
                playIntent.setAction(PlayerService.ACTION_STOP);
                isPlaying = false;
            }
        });


    }


}
