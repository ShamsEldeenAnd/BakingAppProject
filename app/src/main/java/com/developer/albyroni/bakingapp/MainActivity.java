package com.developer.albyroni.bakingapp;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.developer.albyroni.bakingapp.Adapters.RecipeAdapter;
import com.developer.albyroni.bakingapp.IdilingResource.SimpleIdlingResource;
import com.developer.albyroni.bakingapp.Models.Recipe;
import com.developer.albyroni.bakingapp.RetrofitService.RetrofitInctance;
import com.developer.albyroni.bakingapp.Utils.Common;
import com.developer.albyroni.bakingapp.Utils.OnClickItemListner;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnClickItemListner {

    private RecyclerView recipe_menu;
    private RecyclerView.LayoutManager manager;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipes;
    private ProgressBar progressBar;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipe_menu = findViewById(R.id.Recipe_Menu);
        progressBar = findViewById(R.id.progress_bar);

        getIdlingResource();


        if (Common.CheckNetwork(this)) {
            getRecipes();
        } else {
            closeError();
        }


    }

    private void populateView() {
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (tabletSize) {
            manager = new GridLayoutManager(this, 3);
        } else {
            manager = new LinearLayoutManager(this);

        }
        recipe_menu.setHasFixedSize(true);
        recipe_menu.setLayoutManager(manager);
        recipe_menu.setAdapter(recipeAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void getRecipes() {
        if(mIdlingResource!=null){
            mIdlingResource.setIdleState(false);
        }
        progressBar.setVisibility(View.VISIBLE);
        Call<List<Recipe>> call = RetrofitInctance.getInstance().getRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.body() != null && response.body().size() != 0) {
                    recipes = response.body();
                    recipeAdapter = new RecipeAdapter(recipes, MainActivity.this);
                    populateView();
                    if(mIdlingResource!=null){
                        mIdlingResource.setIdleState(true);
                    }

                } else {
                    closeError();
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                closeError();
            }
        });


    }


    @Override
    public void OnClickItem(int index) {
        Common.currentRecipe = recipes.get(index);
        Intent intent = new Intent(this, RecipeDetails.class);
        startActivity(intent);
    }

    //error message
    private void closeError() {
        finish();
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }
}
