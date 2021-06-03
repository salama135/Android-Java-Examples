package com.example.android_java_examples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CurrencyConverterActivity extends AppCompatActivity {

    public static final double exchangeRateEGP = 18.997785;
    public static final double exchangeRateUSD = 1.20935;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        final EditText valueFrom = (EditText) findViewById(R.id.editTextNumber_ValueFrom);
        final EditText valueTo = (EditText) findViewById(R.id.editTextNumber_ValueTo);

        Button buttonClear = (Button) findViewById(R.id.button_Clear);
        Button buttonConvert = (Button) findViewById(R.id.button_Convert);

        buttonConvert.setOnClickListener(v -> {
            if (valueFrom.getText().toString().isEmpty() && valueTo.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Invalid Data - try again", Toast.LENGTH_LONG).show();
            } else {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean valueFrom_Focus = valueFrom.getId() == getCurrentFocus().getId();
                boolean valueTo_Focus = valueTo.getId() == getCurrentFocus().getId();

                if(valueFrom_Focus) {
                    double valueFrom_Double  = Double.parseDouble(valueFrom.getText().toString());
                    double USDtoEGP = exchangeRateEGP / exchangeRateUSD * valueFrom_Double;
                    valueTo.setText(String.valueOf(USDtoEGP));
                } else
                if(valueTo_Focus) {
                    double valueTo_Double =  Double.parseDouble(valueTo.getText().toString());
                    double EGPtoUSD = exchangeRateUSD / exchangeRateEGP * valueTo_Double;
                    valueFrom.setText(String.valueOf(EGPtoUSD));
                }
            }
        });

        buttonClear.setOnClickListener(v -> {
            valueFrom.setText("");
            valueTo.setText("");
        });
    }
}