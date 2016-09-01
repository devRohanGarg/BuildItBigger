package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.Utils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.jokeviewer.JokeViewerActivity;

import dev.rg.ProgressButton.iml.ActionProcessButton;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    InterstitialAd mInterstitialAd;
    AdRequest mAdRequest;
    ActionProcessButton mButton;

    public MainActivityFragment() {
    }

    private void requestNewAd() {
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        mAdRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("F8736FA1E7970E23F98E186DD1B834FD")
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mButton = (ActionProcessButton) root.findViewById(R.id.tell_joke);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewAd();
            }
        });

        requestNewAd();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        mAdView.loadAd(mAdRequest);
        return root;
    }

    public void tellJoke() {
        mButton.setClickable(false);
        mButton.setMode(ActionProcessButton.Mode.ENDLESS);
        mButton.setProgress(50);
        mInterstitialAd.loadAd(mAdRequest);
        new EndpointsAsyncTask(getActivity(), new EndpointsAsyncTask.Listener() {
            @Override
            public void onJokeLoaded(String joke) {
                if (joke.equals(getResources().getString(R.string.no_joke)))
                    mButton.setProgress(-1);
                else mButton.setProgress(100);
                Intent intent = new Intent(getActivity(), JokeViewerActivity.class);
                intent.putExtra("JOKE", joke);
                startActivity(intent);
                if (mInterstitialAd.isLoaded())
                    mInterstitialAd.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mButton.setProgress(0);
                        mButton.setClickable(true);
                    }
                }, 1500);
            }
        }).execute();
    }
}
