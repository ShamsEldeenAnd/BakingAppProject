package com.developer.albyroni.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.developer.albyroni.bakingapp.Models.Ingredient;
import com.developer.albyroni.bakingapp.Models.Recipe;
import com.developer.albyroni.bakingapp.Utils.Common;
import com.google.gson.Gson;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    private SharedPreferences mPrefs;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String recipeName, List<Ingredient> ingredients) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        views.setTextViewText(R.id.recipe_name, recipeName);

        views.removeAllViews(R.id.holder);

        Log.d("Name :", recipeName);

        //check addition to widget done or not
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                RemoteViews ingredientView = new RemoteViews(context.getPackageName(),
                        R.layout.ingrediant_row);
                String full_ingredient = String.valueOf(ingredient.getQuantity()) + " " + ingredient.getMeasure() + " " + ingredient.getIngredient();
                ingredientView.setTextViewText(R.id.ingredient_item, full_ingredient);
                views.addView(R.id.holder, ingredientView);
            }
        }


        appWidgetManager.updateAppWidget(new ComponentName(context, BakingAppWidget.class), views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Gson gson = new Gson();
        mPrefs = context.getSharedPreferences(Common.PREF_CONSTANT, MODE_PRIVATE);
        String json = mPrefs.getString(Common.RECIPE_CONSTANT, "");
        Recipe recipe = gson.fromJson(json, Recipe.class);
        String recipeName;
        List<Ingredient> ingredients;

        //recipe validation
        if (recipe != null) {
            recipeName = recipe.getName();
            ingredients = recipe.getIngredients();
        } else {
            recipeName = "No Recipe are Added ";
            ingredients = null;
        }
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipeName, ingredients);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

