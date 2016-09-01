package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.gradle.builditbigger.Utils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.jokeviewer.JokeViewerActivity;

import dev.rg.ProgressButton.iml.ActionProcessButton;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ActionProcessButton mButton;

    public MainActivityFragment() {
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
        return root;
    }

    public void tellJoke() {
        mButton.setClickable(false);
        mButton.setMode(ActionProcessButton.Mode.ENDLESS);
        mButton.setProgress(50);
        new EndpointsAsyncTask(getActivity(), new EndpointsAsyncTask.Listener() {
            @Override
            public void onJokeLoaded(String joke) {
                if (joke.equals(getResources().getString(R.string.no_joke)))
                    mButton.setProgress(-1);
                else mButton.setProgress(100);
                Intent intent = new Intent(getActivity(), JokeViewerActivity.class);
                intent.putExtra("JOKE", joke);
                startActivity(intent);
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
