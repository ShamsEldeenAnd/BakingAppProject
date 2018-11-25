package com.developer.albyroni.bakingapp;


import android.appwidget.AppWidgetManager;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.developer.albyroni.bakingapp.Fragments.StepDetailsFragment;
import com.developer.albyroni.bakingapp.Utils.Common;
import com.google.gson.Gson;

public class RecipeDetails extends AppCompatActivity {

    private ImageView widgetImg;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        widgetImg = findViewById(R.id.widget_img);
        mPrefs = getSharedPreferences(Common.PREF_CONSTANT, MODE_PRIVATE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        widgetImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(Common.currentRecipe);
                prefsEditor.putString(Common.RECIPE_CONSTANT, json);
                prefsEditor.commit();

                //handling update
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(RecipeDetails.this);
                Bundle bundle = new Bundle();
                int appWidgetId = bundle.getInt(
                        AppWidgetManager.EXTRA_APPWIDGET_ID,
                        AppWidgetManager.INVALID_APPWIDGET_ID);
                BakingAppWidget.updateAppWidget(RecipeDetails.this, appWidgetManager, appWidgetId, Common.currentRecipe.getName(),
                        Common.currentRecipe.getIngredients());


                Toast.makeText(RecipeDetails.this, R.string.Added_widget, Toast.LENGTH_SHORT).show();
            }
        });
        setTitle(Common.currentRecipe.getName());
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            initFragemnt();
        }
    }


    private void initFragemnt() {

        //initialize Fragment for Tablet Size
        StepDetailsFragment detailsFragment = new StepDetailsFragment();
        detailsFragment.setPosition(0);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_fragment, detailsFragment)
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
