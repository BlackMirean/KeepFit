package com.rgzn.zt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class DoTrain extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 2;
    private static final String CHANNEL_ID = "myChannel";

    TextView intropage, subintropage,fitonetitle,fitonedesc,
            btnexercise,timerValue;
    View divpage,bgprogress;
    ImageView imgtimer;
    LinearLayout fitone;

    private MusicService musicService;
    private boolean isServiceBound = false;


    private static final long START_TIME_IN_MILLIS = 10000;
    private CountDownTimer countDownTimer;
    private boolean aTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeState = START_TIME_IN_MILLIS;

    Animation btthree,bttfour,ttbone,ttbtwo, alphagogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_train);

        createNotificationChannel();

        Intent intent = getIntent();
        if (intent != null) {
            String totalValueString = intent.getStringExtra("TOTAL_VALUE");
            mTimeState = (long)Integer.parseInt(totalValueString);
            mTimeLeftInMillis = mTimeState * 60000;
        }

        btthree = AnimationUtils.loadAnimation(this,R.anim.btthree);
        bttfour = AnimationUtils.loadAnimation(this,R.anim.bttfour);
        ttbone = AnimationUtils.loadAnimation(this,R.anim.ttbone);
        ttbtwo = AnimationUtils.loadAnimation(this,R.anim.ttbtwo);
        alphagogo = AnimationUtils.loadAnimation(this,R.anim.alphagogo);

        timerValue = findViewById(R.id.timerValue);
        intropage = (TextView) findViewById(R.id.intropage);
        subintropage = (TextView) findViewById(R.id.subintropage);
        btnexercise = (TextView) findViewById(R.id.btnexercise);
        //fit itemleri
        fitonetitle=findViewById(R.id.fitonetitle);
        fitonedesc=findViewById(R.id.fitonedesc);
        bgprogress=findViewById(R.id.bgprogress);
        divpage=findViewById(R.id.divpage);
        fitone=findViewById(R.id.fitone);
        imgtimer = findViewById(R.id.imgtimer);

        btnexercise.startAnimation(bttfour);
        bgprogress.startAnimation(btthree);
        fitone.startAnimation(ttbone);
        intropage.startAnimation(ttbtwo);
        subintropage.startAnimation(ttbtwo);
        divpage.startAnimation(ttbtwo);
        imgtimer.startAnimation(alphagogo);
        timerValue.startAnimation(alphagogo);

        Intent serviceIntent = new Intent(this, MusicService.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        updateCountDownText();
        btnexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
                playMusic(view);
            }
        });
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis=millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Congratulations!",Toast.LENGTH_LONG).show();
                sendNotification();
                try {
                    // 休眠10秒
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis/1000)/60;
        int seconds = (int) (mTimeLeftInMillis/1000)%60;
        fitonedesc.setText(minutes + " minutes left");

        int progress = (int)(100 * minutes / mTimeState);
        if (progress >= 60) fitonetitle.setText("保持呼吸，注意力集中");
        else if (progress >= 30) fitonetitle.setText("加油加油");
        else fitonetitle.setText("坚持就是胜利");
        String timeLeft = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        timerValue.setText(timeLeft);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicService = binder.getService();
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;
        }
    };

    public void playMusic(View view) {
        if (isServiceBound) {
            musicService.startMusic();
        }
    }

    // 创建通知渠道（适用于 Android 8.0 及以上版本）
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MyChannel";
            String description = "Channel for my notifications";
            int importance = NotificationManagerCompat.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.createNotificationChannel(channel);
        }
    }


    // 发送通知
    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("🎊🎊🎊🎊🎊🎊🎊")
                .setContentText("恭喜你完成训练！")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
