package com.developer.albyroni.bakingapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.developer.albyroni.bakingapp.Adapters.StepsAdapter;

import com.developer.albyroni.bakingapp.IngredientDetails;
import com.developer.albyroni.bakingapp.R;
import com.developer.albyroni.bakingapp.StepDetails;
import com.developer.albyroni.bakingapp.Utils.Common;
import com.developer.albyroni.bakingapp.Utils.OnClickItemListner;


public class RecipeFragment extends Fragment implements OnClickItemListner {

    private RecyclerView recipe_steps;

    private TextView recipe_ingredient;

    private RecyclerView.LayoutManager manager;

    private CardView ingrediants;
    private StepsAdapter stepsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_fragment, container, false);
        recipe_steps = rootView.findViewById(R.id.steps_menu);
        recipe_ingredient = rootView.findViewById(R.id.recipe_main_ingredient);

        ingrediants = rootView.findViewById(R.id.ingrediants);


        recipe_ingredient.setText(Common.currentRecipe.getName() + " Ingredients");
        initrecyclerview();
        initStepAdapter();


        ingrediants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), IngredientDetails.class);
                getActivity().startActivity(myIntent);
            }
        });
        return rootView;
    }

    private void initStepAdapter() {
        if (Common.currentRecipe != null) {
            stepsAdapter = new StepsAdapter(Common.currentRecipe.getSteps(), RecipeFragment.this);
            recipe_steps.setAdapter(stepsAdapter);

        } else {
            Log.e("ERROR", "NULL");
        }
    }

    private void initrecyclerview() {
        manager = new LinearLayoutManager(getContext());
        recipe_steps.setHasFixedSize(true);
        recipe_steps.setLayoutManager(manager);
    }


    @Override
    public void OnClickItem(int position) {
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        if (!tabletSize) {
            Intent myIntent = new Intent(getActivity(), StepDetails.class);
            myIntent.putExtra("index", position);
            getActivity().startActivity(myIntent);
        } else {
            initFragemnt(position);
        }
    }

    private void initFragemnt(int position) {
        StepDetailsFragment detailsFragment = new StepDetailsFragment();
        detailsFragment.setPosition(position);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_fragment, detailsFragment)
                .commit();
    }
}
