package com.example.temperatureconverter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etTemperature;
    Spinner spinnerUnit;
    Button btnConvert;
    TextView tvCelsius, tvFahrenheit, tvKelvin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etTemperature = findViewById(R.id.etTemperature);
        spinnerUnit = findViewById(R.id.spinnerUnit);
        btnConvert = findViewById(R.id.btnConvert);
        tvCelsius = findViewById(R.id.tvResultCelsius);
        tvFahrenheit = findViewById(R.id.tvResultFahrenheit);
        tvKelvin = findViewById(R.id.tvResultKelvin);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.temperature_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);


        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {

        if (etTemperature.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter temperature", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputTemp = Double.parseDouble(etTemperature.getText().toString());
        String unit = spinnerUnit.getSelectedItem().toString();

        double celsius, fahrenheit, kelvin;

        if (unit.equals("Celsius")) {
            celsius = inputTemp;
            fahrenheit = (celsius * 9 / 5) + 32;
            kelvin = celsius + 273.15;

        } else if (unit.equals("Fahrenheit")) {
            fahrenheit = inputTemp;
            celsius = (fahrenheit - 32) * 5 / 9;
            kelvin = celsius + 273.15;

        } else {
            kelvin = inputTemp;
            celsius = kelvin - 273.15;
            fahrenheit = (celsius * 9 / 5) + 32;
        }

        tvCelsius.setText("Celsius: " + String.format("%.2f", celsius));
        tvFahrenheit.setText("Fahrenheit: " + String.format("%.2f", fahrenheit));
        tvKelvin.setText("Kelvin: " + String.format("%.2f", kelvin));
    }
}