/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.y.mvp.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.y.mvp.R;
import com.example.y.mvp.constant.Constant;
import com.example.y.mvp.service.DownloadService;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayActivity extends Activity {

    @Bind(R.id.surface_view)
    public VideoView mVideoView;
    @Bind(R.id.progressbar)
    public ProgressBar progressbar;

    String name = "";
    String path = "";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        if (!LibsChecker.checkVitamioLibs(this)) {
            finish();
            return;
        }

        setContentView(R.layout.activity_videoview);
        ButterKnife.bind(this);


        MediaController controller = new MediaController(this);
        Uri uri = getIntent().getData();
        if (uri != null) {
            name = uri.getLastPathSegment().trim();
            path = uri.toString().trim();
            controller.setDonwloadView(false, null);
        } else {
            path = getIntent().getStringExtra(Constant.ARG_VIDEO_PATH);
            name = getIntent().getStringExtra(Constant.ARG_VIDEO_NAME);
            if (path.startsWith("http")) {
                controller.setDonwloadView(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), DownloadService.class);
                        intent.putExtras(getIntent().getExtras());
                        startService(intent);
                    }
                });
            }
        }

        mVideoView.setVideoPath(path);
        controller.setFileName(name);
        mVideoView.setMediaController(controller);
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);

            }
        });
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                if (percent > 98) {
                    progressbar.setVisibility(View.GONE);
                }
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                finish();
                return false;
            }
        });

    }

    public static Intent startSelf(Context ctx, String path, String name) {
        Intent intent = new Intent(ctx, VideoPlayActivity.class);
        intent.putExtra(Constant.ARG_VIDEO_PATH, path);
        intent.putExtra(Constant.ARG_VIDEO_NAME, name);
        return intent;
    }


}
