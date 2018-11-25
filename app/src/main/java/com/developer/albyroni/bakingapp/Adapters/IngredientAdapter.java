package com.developer.albyroni.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.albyroni.bakingapp.Models.Ingredient;
import com.developer.albyroni.bakingapp.R;


import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {


    private List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.ingrediant_row, parent, false);

        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

        String qty = String.valueOf(ingredients.get(position).getQuantity());
        String measure = ingredients.get(position).getMeasure();
        String name = ingredients.get(position).getIngredient();

        holder.name.setText(qty + " " + measure + " " + name);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {


        TextView name;


        public IngredientViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ingredient_item);

        }
    }
}
