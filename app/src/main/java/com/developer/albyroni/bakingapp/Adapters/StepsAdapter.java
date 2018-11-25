package com.developer.albyroni.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.albyroni.bakingapp.Models.Step;
import com.developer.albyroni.bakingapp.R;
import com.developer.albyroni.bakingapp.Utils.OnClickItemListner;

import com.squareup.picasso.Picasso;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private List<Step> steps;
    private OnClickItemListner monClickItemListner;


    public StepsAdapter(List<Step> steps, OnClickItemListner monClickItemListner) {
        this.steps = steps;
        this.monClickItemListner = monClickItemListner;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.step_row, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {

        String desc = steps.get(position).getShortDescription();
        String url = steps.get(position).getThumbnailURL();
        if (!url.isEmpty() && url != null) {
            Picasso.get().load(url).into(holder.step_img);
        } else {
            holder.step_img.setImageResource(R.drawable.restauran_icon);
        }
        holder.step_desc.setText(desc);


    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView step_img;

        TextView step_desc;

        public StepViewHolder(View itemView) {
            super(itemView);
            step_img = itemView.findViewById(R.id.step_img);
            step_desc = itemView.findViewById(R.id.step_desc);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            monClickItemListner.OnClickItem(position);
        }
    }
}
