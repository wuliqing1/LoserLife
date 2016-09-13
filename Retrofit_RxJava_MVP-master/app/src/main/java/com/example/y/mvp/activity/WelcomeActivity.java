package com.example.y.mvp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.y.mvp.R;
import com.example.y.mvp.mvp.presenter.EmptyPresenterImpl;
import com.example.y.mvp.mvp.view.BaseView;
import com.example.y.mvp.utils.DayNight;
import com.example.y.mvp.widget.MyVideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.OnClick;

public class WelcomeActivity extends MvpActivity<BaseView.EmptyView, EmptyPresenterImpl>
        implements BaseView.EmptyView {
    public static final String VIDEO_NAME = "welcome_video.mp4";
    @Bind(R.id.videoView)
    MyVideoView videoView;
    @Bind(R.id.theme_select_group)
    RadioGroup themeSelectGroup;
    @Bind(R.id.buttonLeft)
    Button buttonLeft;
    @Bind(R.id.appName)
    TextView appName;
    @Bind(R.id.day_theme_rb)
    RadioButton dayThemeRb;
    @Bind(R.id.night_theme_rb)
    RadioButton nightThemeRb;

    @Override
    protected void initData() {
        initView();
        File videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists()) {
            videoFile = copyVideoFile();
        }

        playVideo(videoFile);
        playAnim();
    }

    private void initView() {
        if (mDayNightHelper.isDay()) {
            dayThemeRb.setChecked(true);
        } else if (mDayNightHelper.isNight()) {
            nightThemeRb.setChecked(true);
        }

        themeSelectGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.day_theme_rb:
                        if (mDayNightHelper.isNight()) {
                            mDayNightHelper.setMode(DayNight.DAY);
                        }
                        break;
                    case R.id.night_theme_rb:
                        if (mDayNightHelper.isDay()) {
                            mDayNightHelper.setMode(DayNight.NIGHT);
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected EmptyPresenterImpl createPresenter() {
        return new EmptyPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.welcome;
    }

    @Override
    public void onWindowFocusChanged(final boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (hasFocus && Build.VERSION.SDK_INT >= 19) {
                    View decorView = getWindow().getDecorView();
                    decorView.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            }
        }, 100);

    }

    private void playVideo(File videoFile) {
        videoView.setVideoPath(videoFile.getPath());
        videoView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        });
    }

    private void playAnim() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(appName, "alpha", 0, 1, 0);
        anim.setDuration(8000);
        anim.start();
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                jumpToManiActivity();
            }
        });
    }

    private void jumpToManiActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private File copyVideoFile() {
        File videoFile;
        try {
            FileOutputStream fos = openFileOutput(VIDEO_NAME, MODE_PRIVATE);
            InputStream in = getResources().openRawResource(R.raw.welcome_video);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                fos.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoFile = getFileStreamPath(VIDEO_NAME);
        if (!videoFile.exists())
            throw new RuntimeException("video file has problem, are you sure you have welcome_video.mp4 in res/raw folder?");
        return videoFile;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }

    @OnClick(R.id.buttonLeft)
    public void onClick() {
        jumpToManiActivity();
    }

    @Override
    protected boolean isSupportSwipe() {
        return false;
    }

    @Override
    protected boolean isRxUnsubscribe() {
        return false;
    }
}
