package com.pucpr.sqliteapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pucpr.sqliteapp.R;
import com.pucpr.sqliteapp.model.DataModel;

public class MainActivity extends AppCompatActivity implements CityAdapter.OnCityListener {
    RecyclerView recyclerView;
    CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        DataModel.getInstance().setContext(MainActivity.this);
        adapter = new CityAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                DataModel.getInstance().deleteCity(DataModel.getInstance().getCities().get(viewHolder.getAdapterPosition()), viewHolder.getAdapterPosition());
                Toast.makeText(MainActivity.this, "City Removed", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onCityClick(int position) {
        Intent intent = new Intent(this, AddCityActivity.class);
        intent.putExtra("cityIndex", position);
        startActivity(intent);
    }

    public void addCity(View view) {
        Intent intent = new Intent(this, AddCityActivity.class);
        startActivity(intent);
    }
}