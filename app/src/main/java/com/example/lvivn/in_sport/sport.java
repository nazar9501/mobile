package com.example.lvivn.in_sport;

import java.io.IOException;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ContextMenu;
import android.view.MenuItem;


public class sport extends AppCompatActivity implements OnPreparedListener, OnCompletionListener, View.OnClickListener {

    final String DATA_STREAM = "http://online.radiorecord.ru:8101/rr_128";
    final String DATA_SD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/music.mp3";
    final Uri DATA_URI = ContentUris.withAppendedId(android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 13359);

    final int MENU_SCALE_ID = 1;
    final int MENU_ROTATE_ID = 2;

    MediaPlayer mediaPlayer;
    AudioManager am;
    TextView tv;
    Button btnStartStream;
    Button btnStartSD;
    Button btnStartUri;
    Button btnPause;
    Button btnResume;
    Button btnStop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport);
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        tv = (TextView) findViewById(R.id.textView);
        registerForContextMenu(tv);

        btnStartStream = (Button) findViewById(R.id.btnStartStream);
        btnStartSD = (Button) findViewById(R.id.btnStartSD);
        btnStartUri = (Button) findViewById(R.id.btnStartUri);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnResume = (Button) findViewById(R.id.btnResume);
        btnStop = (Button) findViewById(R.id.btnStop);

        btnStartStream.setOnClickListener(this);
        btnStartSD.setOnClickListener(this);
        btnStartUri.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnResume.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    public void onClickStart(View view) {
        releaseMP();

        try {
            switch (view.getId()) {
                case R.id.btnStartStream:
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_STREAM);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
                case R.id.btnStartSD:
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_SD);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    break;
                case R.id.btnStartUri:
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(this, DATA_URI);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaPlayer == null)
            return;

        mediaPlayer.setOnCompletionListener(this);
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        try {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
            case R.id.btnPause:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                break;
            case R.id.btnResume:
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                break;
            case R.id.btnStop:
                mediaPlayer.stop();
                break;
            case R.id.btnStartStream:
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_STREAM);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
                break;
            case R.id.btnStartSD:
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(DATA_SD);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();
                break;
            case R.id.btnStartUri:
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(this, DATA_URI);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();
                break;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
        if (mediaPlayer == null)
            return;

        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMP();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }


    public void onButtonClick1(View v)
    {
        if(v.getId() == R.id.Fprogram)
        {
            Intent i = new Intent(sport.this, program.class);
            startActivity(i);
        }
    }

    public void onButtonClick2(View v)
    {
        if(v.getId() == R.id.Ftraning)
        {
            Intent i = new Intent(sport.this, traning.class);
            startActivity(i);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()) {
            case R.id.textView:
                menu.add(0, MENU_SCALE_ID, 0, "scale");
                menu.add(0, MENU_ROTATE_ID, 0, "rotate");
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Animation anim = null;
        switch (item.getItemId()) {
            case MENU_SCALE_ID:
                anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
                break;
            case MENU_ROTATE_ID:
                anim = AnimationUtils.loadAnimation(this, R.anim.myrotate);
                break;
        }
        tv.startAnimation(anim);
        return super.onContextItemSelected(item);
    }


}
