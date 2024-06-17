package com.rgzn.zt;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

    private MediaPlayer mediaPlayer;
    private final IBinder binder = new MusicBinder();

    public class MusicBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void startMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.prepareAsync();
        }
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int position) {
        mediaPlayer.seekTo(position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
