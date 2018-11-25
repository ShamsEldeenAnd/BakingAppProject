package com.developer.albyroni.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.developer.albyroni.bakingapp.Adapters.IngredientAdapter;
import com.developer.albyroni.bakingapp.Utils.Common;



public class IngredientDetails extends AppCompatActivity {

    private RecyclerView ingredient_menu;
    private RecyclerView.LayoutManager manager;
    private IngredientAdapter ingredientAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Ingredients");
        setContentView(R.layout.activity_ingredient_details);
        ingredient_menu = findViewById(R.id.ingredient_menu);
        ingredientAdapter = new IngredientAdapter(Common.currentRecipe.getIngredients());



        initrecyclerview();
    }


    private void initrecyclerview() {
        manager = new LinearLayoutManager(this);
        ingredient_menu.setHasFixedSize(true);
        ingredient_menu.setLayoutManager(manager);
        ingredient_menu.setAdapter(ingredientAdapter);
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
