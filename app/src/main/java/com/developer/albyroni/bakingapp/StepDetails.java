package com.developer.albyroni.bakingapp;

import android.content.res.Configuration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.developer.albyroni.bakingapp.Fragments.StepDetailsFragment;
import com.developer.albyroni.bakingapp.Utils.Common;

public class StepDetails extends AppCompatActivity {


    private int position;
    private Button back, forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("index");
        } else if (getIntent() != null) {
            position = getIntent().getExtras().getInt("index");
        }
        back = findViewById(R.id.backBtn);
        forward = findViewById(R.id.nextBtn);

        initFragemnt();
        setupUi();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("index", position);
        super.onSaveInstanceState(outState);
    }

    public void back(View view) {
        if (position >= 1) {
            position--;
            initFragemnt();
        }
    }

    public void next(View view) {
        if (position < Common.currentRecipe.getSteps().size() - 1) {
            position++;
            initFragemnt();
        }
    }


    private void hideUielements() {
        back.setVisibility(View.INVISIBLE);
        forward.setVisibility(View.INVISIBLE);
    }

    private void setupUi() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideUielements();
        }
    }

    private void initFragemnt() {
        StepDetailsFragment detailsFragment = new StepDetailsFragment();
        detailsFragment.setPosition(position);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.master_step_detail, detailsFragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
