package com.developer.albyroni.bakingapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.developer.albyroni.bakingapp.Models.Recipe;

public class Common {

    public static Recipe currentRecipe;

    public static String RECIPE_CONSTANT="recipe";
    public static String PREF_CONSTANT="mypreference";
    //check  the connection
    public static boolean CheckNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
