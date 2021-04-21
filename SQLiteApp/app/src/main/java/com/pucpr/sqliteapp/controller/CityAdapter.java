package com.pucpr.sqliteapp.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pucpr.sqliteapp.R;
import com.pucpr.sqliteapp.model.City;
import com.pucpr.sqliteapp.model.DataModel;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private OnCityListener mOnCityListener;

    public CityAdapter(Context context, OnCityListener onCityListener) {
        this.context = context;
        this.mOnCityListener = onCityListener;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_city_recyclerview,
                        parent, false
                );

        return new ViewHolder(view, mOnCityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        City c = DataModel.getInstance().getCities().get(position);
        holder.textViewCity.setText(c.getName());
        holder.textViewPopulation.setText(String.valueOf(c.getPopulation()));
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getCities().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewCity, textViewPopulation;
        OnCityListener onCityListener;

        public ViewHolder(@NonNull View itemView, OnCityListener onCityListener) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.textViewCity);
            textViewPopulation = itemView.findViewById(R.id.textViewPopulation);
            this.onCityListener = onCityListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCityListener.onCityClick(getAdapterPosition());
        }
    }

    public interface OnCityListener {
        void onCityClick(int position);
    }
}
