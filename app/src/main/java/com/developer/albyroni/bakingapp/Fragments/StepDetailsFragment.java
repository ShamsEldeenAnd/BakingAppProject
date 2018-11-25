package com.developer.albyroni.bakingapp.Fragments;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.developer.albyroni.bakingapp.R;
import com.developer.albyroni.bakingapp.Utils.Common;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class StepDetailsFragment extends Fragment {

    private TextView full_desc;
    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer mExoPlayer;
    private int position;
    private String url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_details, container, false);
        exoPlayerView = rootView.findViewById(R.id.playerView);
        full_desc = rootView.findViewById(R.id.full_description);
        url = Common.currentRecipe.getSteps().get(position).getVideoURL();

        if (url == null || url.isEmpty()) {
            Toast.makeText(getActivity(), "No Video Available", Toast.LENGTH_SHORT).show();
        }
        setDesctext();
        url = Common.currentRecipe.getSteps().get(position).getVideoURL();
        initializePlayer(url);
        setupUi();
        return rootView;
    }


    public void setPosition(int position) {
        this.position = position;
    }


    private void initializePlayer(String videoUrl) {
        //parse the url
        Uri mediaUri = Uri.parse(videoUrl);

        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            exoPlayerView.setPlayer(mExoPlayer);
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity(), "BakingApp");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    private void setDesctext() {
        full_desc.setText(Common.currentRecipe.getSteps().get(position).getDescription());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    private void setupUi() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ViewGroup.LayoutParams params = exoPlayerView.getLayoutParams();
            params.width = params.MATCH_PARENT;
            params.height = params.MATCH_PARENT;
            exoPlayerView.setLayoutParams(params);
        }
    }
}
