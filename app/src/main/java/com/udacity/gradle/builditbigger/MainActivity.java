package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.Utils.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.jokeviewer.JokeViewerActivity;

import dev.rg.ProgressButton.iml.ActionProcessButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        final ActionProcessButton button = (ActionProcessButton) view;
        button.setClickable(false);
        button.setMode(ActionProcessButton.Mode.ENDLESS);
        button.setProgress(50);
        new EndpointsAsyncTask(this, new EndpointsAsyncTask.Listener() {
            @Override
            public void onJokeLoaded(String joke) {
                if (joke.equals(getResources().getString(R.string.no_joke)))
                    button.setProgress(-1);
                else button.setProgress(100);
                Intent intent = new Intent(getApplicationContext(), JokeViewerActivity.class);
                intent.putExtra("JOKE", joke);
                startActivity(intent);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button.setProgress(0);
                        button.setClickable(true);
                    }
                }, 1500);
            }
        }).execute();
    }


}
