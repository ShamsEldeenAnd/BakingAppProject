package com.developer.albyroni.bakingapp.RetrofitService;

import com.developer.albyroni.bakingapp.Models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();
}
