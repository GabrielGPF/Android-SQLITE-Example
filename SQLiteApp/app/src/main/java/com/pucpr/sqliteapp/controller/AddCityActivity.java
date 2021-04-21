package com.pucpr.sqliteapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pucpr.sqliteapp.R;
import com.pucpr.sqliteapp.model.City;
import com.pucpr.sqliteapp.model.DataModel;

public class AddCityActivity extends AppCompatActivity {

    private int cityIndex;
    private EditText nameEditText, populationEditText;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_city);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        populationEditText = (EditText) findViewById(R.id.populationEditText);
        cityIndex = getIntent().getIntExtra("cityIndex", -1);

        if (cityIndex != -1) {
            String nameValue = DataModel.getInstance().getCities().get(cityIndex).getName();
            String populationValue = String.valueOf(DataModel.getInstance().getCities().get(cityIndex).getPopulation());

            id = DataModel.getInstance().getCities().get(cityIndex).getId();
            nameEditText.setText(nameValue);
            populationEditText.setText(populationValue);
        }
    }

    public void cancelButtonClick(View view) {
        finish();
    }

    public void submitButtonClick(View view) {
        nameEditText = findViewById(R.id.nameEditText);
        populationEditText = findViewById(R.id.populationEditText);

        if (nameEditText.getText().toString().length() != 0 && populationEditText.getText().toString().length() != 0) {
            City newCity = new City(nameEditText.getText().toString(), Integer.parseInt(populationEditText.getText().toString()));

            if (cityIndex == -1) {
                DataModel.getInstance().addCity(newCity);
                Toast.makeText(this, "City submitted", Toast.LENGTH_SHORT).show();
            } else {
                newCity.setId(id);
                DataModel.getInstance().editCity(newCity, cityIndex);
                Toast.makeText(this, "City editted", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}