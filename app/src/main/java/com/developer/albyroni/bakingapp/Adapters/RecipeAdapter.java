package com.developer.albyroni.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.albyroni.bakingapp.Models.Recipe;
import com.developer.albyroni.bakingapp.R;
import com.developer.albyroni.bakingapp.Utils.OnClickItemListner;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {


    private List<Recipe> recipes;

    private OnClickItemListner monClickItemListner;

    public RecipeAdapter(List<Recipe> recipes, OnClickItemListner monClickItemListner) {
        this.recipes = recipes;
        this.monClickItemListner = monClickItemListner;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recipe_row, parent, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String name = recipes.get(position).getName();
        holder.recipeName.setText(name);

        switch (name) {
            case "Nutella Pie":
                holder.recipeimage.setImageResource(R.drawable.nuttela_pie);
                break;
            case "Brownies":
                holder.recipeimage.setImageResource(R.drawable.brownies);
                break;
            case "Yellow Cake":
                holder.recipeimage.setImageResource(R.drawable.yellow_cake);
                break;
            case "Cheesecake":
                holder.recipeimage.setImageResource(R.drawable.cheese_cake);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView recipeName;
        ImageView recipeimage;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_name);
            recipeimage = itemView.findViewById(R.id.recipe_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            monClickItemListner.OnClickItem(position);
        }
    }
}
