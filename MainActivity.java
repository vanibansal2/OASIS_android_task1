package com.vanibansal.unitconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgCategory;
    RadioButton rbLength, rbWeight, rbTemp;
    Spinner spinnerFrom, spinnerTo;
    EditText etValue;
    Button btnConvert;
    TextView tvResult;

    String[] lengthUnits = {"Kilometer","Meter","Centimeter","Millimeter","Mile","Foot","Inch"};
    String[] weightUnits = {"Kilogram","Gram","Milligram","Pound","Ounce"};
    String[] tempUnits   = {"Celsius","Fahrenheit","Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgCategory  = findViewById(R.id.rgCategory);
        rbLength    = findViewById(R.id.rbLength);
        rbWeight    = findViewById(R.id.rbWeight);
        rbTemp      = findViewById(R.id.rbTemp);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo   = findViewById(R.id.spinnerTo);
        etValue     = findViewById(R.id.etValue);
        btnConvert  = findViewById(R.id.btnConvert);
        tvResult    = findViewById(R.id.tvResult);

        loadSpinners(lengthUnits);

        rgCategory.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbLength)      loadSpinners(lengthUnits);
            else if (checkedId == R.id.rbWeight) loadSpinners(weightUnits);
            else if (checkedId == R.id.rbTemp)   loadSpinners(tempUnits);
            tvResult.setText("Result will appear here");
        });

        btnConvert.setOnClickListener(v -> convert());
    }

    void loadSpinners(String[] units) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
    }

    void convert() {
        String input = etValue.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a value!", Toast.LENGTH_SHORT).show();
            return;
        }
        double value;
        try { value = Double.parseDouble(input); }
        catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number!", Toast.LENGTH_SHORT).show();
            return;
        }

        String from = spinnerFrom.getSelectedItem().toString();
        String to   = spinnerTo.getSelectedItem().toString();

        if (from.equals(to)) {
            tvResult.setText(value + " " + to);
            return;
        }

        double result = 0;
        int checkedId = rgCategory.getCheckedRadioButtonId();

        if (checkedId == R.id.rbLength)      result = convertLength(value, from, to);
        else if (checkedId == R.id.rbWeight) result = convertWeight(value, from, to);
        else if (checkedId == R.id.rbTemp)   result = convertTemp(value, from, to);

        String resultStr = (result == (long) result)
                ? String.valueOf((long) result)
                : String.format("%.4f", result);

        tvResult.setText(value + " " + from + " = " + resultStr + " " + to);
        tvResult.setTextColor(0xFFFFFFFF);
    }

    double convertLength(double v, String from, String to) {
        double meters = toMeters(v, from);
        return fromMeters(meters, to);
    }

    double toMeters(double v, String unit) {
        switch (unit) {
            case "Kilometer":   return v * 1000;
            case "Meter":       return v;
            case "Centimeter":  return v / 100;
            case "Millimeter":  return v / 1000;
            case "Mile":        return v * 1609.344;
            case "Foot":        return v * 0.3048;
            case "Inch":        return v * 0.0254;
            default:            return v;
        }
    }

    double fromMeters(double v, String unit) {
        switch (unit) {
            case "Kilometer":   return v / 1000;
            case "Meter":       return v;
            case "Centimeter":  return v * 100;
            case "Millimeter":  return v * 1000;
            case "Mile":        return v / 1609.344;
            case "Foot":        return v / 0.3048;
            case "Inch":        return v / 0.0254;
            default:            return v;
        }
    }

    double convertWeight(double v, String from, String to) {
        double kg = toKg(v, from);
        return fromKg(kg, to);
    }

    double toKg(double v, String unit) {
        switch (unit) {
            case "Kilogram":   return v;
            case "Gram":       return v / 1000;
            case "Milligram":  return v / 1_000_000;
            case "Pound":      return v * 0.453592;
            case "Ounce":      return v * 0.0283495;
            default:           return v;
        }
    }

    double fromKg(double v, String unit) {
        switch (unit) {
            case "Kilogram":   return v;
            case "Gram":       return v * 1000;
            case "Milligram":  return v * 1_000_000;
            case "Pound":      return v / 0.453592;
            case "Ounce":      return v / 0.0283495;
            default:           return v;
        }
    }

    double convertTemp(double v, String from, String to) {
        double celsius;
        switch (from) {
            case "Fahrenheit": celsius = (v - 32) * 5 / 9; break;
            case "Kelvin":     celsius = v - 273.15; break;
            default:           celsius = v;
        }
        switch (to) {
            case "Fahrenheit": return (celsius * 9 / 5) + 32;
            case "Kelvin":     return celsius + 273.15;
            default:           return celsius;
        }
    }
}