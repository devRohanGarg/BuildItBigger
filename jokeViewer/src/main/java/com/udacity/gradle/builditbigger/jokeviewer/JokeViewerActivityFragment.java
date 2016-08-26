package com.udacity.gradle.builditbigger.jokeviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokeViewerActivityFragment extends Fragment {

    public JokeViewerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_joke_viewer, container, false);
        TextView jokeView = (TextView) root.findViewById(R.id.joke_text_view);
        Intent i = getActivity().getIntent();
        String JOKE_TAG = "JOKE";
        if (i != null) {
            String joke = i.getStringExtra(JOKE_TAG);
            if (joke != null)
                jokeView.setText(joke);
        }
        return root;
    }
}
