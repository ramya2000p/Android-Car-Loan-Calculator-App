package com.example.ramyacarloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mPreferences;
    SharedPreferences.Editor myEditor;

    Spinner spinner_brand, spinner_model;
    Button button_1;
    ImageView imageView;
    String chosenBrand, monthlyInstallmentF, balanceF, monthsF, date;
    private EditText loan_a, length_a, interest_a, date_a;
    double loanAmount, interestRate, loanLength, yearlyInstallment, totalInstallment, startingTotal,
            monthlyInstallment, balance, totalMonths;
    CheckBox check_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the references
        spinner_brand = findViewById(R.id.spinner_brand);
        spinner_model = findViewById(R.id.spinner_model);
        button_1 = findViewById(R.id.button_1);
        imageView = findViewById(R.id.imageView);
        length_a = findViewById(R.id.length_a);
        loan_a = findViewById(R.id.loan_a);
        interest_a = findViewById(R.id.interest_a);
        date_a = findViewById(R.id.date_a);
        check_box = findViewById(R.id.check_box);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myEditor = mPreferences.edit();

        //Call the sharedPreference method to allow user to save data to access later on
        checkSharedPreferences();

        //Disable last 4 EditTexts and Button
        date_a.setEnabled(false);
        interest_a.setEnabled(false);
        length_a.setEnabled(false);
        button_1.setEnabled(false);

        //Enable EditText for date if EditText for loan is not empty
        loan_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    date_a.setEnabled(false);
                }
                else{
                    date_a.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Enable EditText for interest if EditText for date is not empty
        date_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    interest_a.setEnabled(false);
                }
                else{
                    interest_a.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Enable EditText for length if EditText for interest is not empty
        interest_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    length_a.setEnabled(false);
                }
                else{
                    length_a.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Enable Button  if EditText for length is not empty
        length_a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equals("")){
                    button_1.setEnabled(false);
                }
                else{
                    button_1.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    //Change the contents of spinner_model according to option in spinner_brand
    spinner_brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            chosenBrand = parent.getItemAtPosition(position).toString();

            switch(chosenBrand){

                case "Toyota":
                    spinner_model.setAdapter(new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.toyota_list)));

                    //Load different images when different options are chosen
                    if(position == 0)
                        imageView.setImageResource(R.drawable.vios);

                    break;

                case "Honda":
                    spinner_model.setAdapter(new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.honda_list)));

                    if(position == 1)
                        imageView.setImageResource(R.drawable.city);


                    break;

                case "Perodua":
                    spinner_model.setAdapter(new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.perodua_list)));

                    if(position == 2)
                        imageView.setImageResource(R.drawable.axia);

                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

    //Change the pictures shown in ImageView based on the option chosen in spinner_model
    spinner_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(spinner_model.getSelectedItem().toString().equals("Vios"))
                imageView.setImageResource(R.drawable.vios);
            else if (spinner_model.getSelectedItem().toString().equals("Fortuner"))
                imageView.setImageResource(R.drawable.fortuner);
            else if (spinner_model.getSelectedItem().toString().equals("Vellfire"))
                imageView.setImageResource(R.drawable.vellfire);
            else if (spinner_model.getSelectedItem().toString().equals("City"))
                imageView.setImageResource(R.drawable.city);
            else if (spinner_model.getSelectedItem().toString().equals("Jazz"))
                imageView.setImageResource(R.drawable.jazz);
            else if (spinner_model.getSelectedItem().toString().equals("Odyssey"))
                imageView.setImageResource(R.drawable.odyssey);
            else if (spinner_model.getSelectedItem().toString().equals("Axia"))
                imageView.setImageResource(R.drawable.axia);
            else if (spinner_model.getSelectedItem().toString().equals("Bezza"))
                imageView.setImageResource(R.drawable.bezza);
            else
                imageView.setImageResource(R.drawable.myvi);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if the user chooses to save their information and closes the app
                //And display the data
                if(check_box.isChecked()){
                    myEditor.putString(getString(R.string.checkbox), "True");
                    myEditor.commit();

                    String loan = loan_a.getText().toString();
                    myEditor.putString(getString(R.string.loanSF), loan);
                    myEditor.commit();

                    String date = date_a.getText().toString();
                    myEditor.putString(getString(R.string.dateSF), date);
                    myEditor.commit();

                    String interest = interest_a.getText().toString();
                    myEditor.putString(getString(R.string.interestSF), interest);
                    myEditor.commit();

                    String length = length_a.getText().toString();
                    myEditor.putString(getString(R.string.lengthSF), length);
                    myEditor.commit();

                }else{

                    myEditor.putString(getString(R.string.checkbox), "False");
                    myEditor.commit();

                    String loan = loan_a.getText().toString();
                    myEditor.putString(getString(R.string.loanSF), "");
                    myEditor.commit();

                    String date = date_a.getText().toString();
                    myEditor.putString(getString(R.string.dateSF), "");
                    myEditor.commit();

                    String interest = interest_a.getText().toString();
                    myEditor.putString(getString(R.string.interestSF), "");
                    myEditor.commit();

                    String length = length_a.getText().toString();
                    myEditor.putString(getString(R.string.lengthSF), "");
                    myEditor.commit();

                }

                //Get data from EditText widgets and convert to String then convert to double
                interestRate = Double.parseDouble(interest_a.getText().toString());
                loanLength  = Double.parseDouble(length_a.getText().toString());
                loanAmount  = Double.parseDouble(loan_a.getText().toString());

                //Calculate the user's total interest, monthly installment and monthly balance
                yearlyInstallment = (loanAmount * (interestRate/100));
                totalInstallment = yearlyInstallment * loanLength;
                startingTotal = loanAmount + totalInstallment;
                monthlyInstallment = (startingTotal/(loanLength * 12));
                balance = startingTotal - monthlyInstallment;
                totalMonths = loanLength * 12;

                date = (date_a.getText().toString());

                //If the loan length that the user input is appropriate thenn activity_second is launched
                if(validateYear()){

                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("MonthlyInstallment_1", monthlyInstallment);
                    intent.putExtra("StartingTotal_1", startingTotal);
                    intent.putExtra("TotalMonths_1", totalMonths);
                    intent.putExtra("Date", date);
                    startActivity(intent);

                }

            }
        });

    }

    //Method to validate user input for loan length
    private boolean validateYear(){
        loanLength  = Integer.parseInt(length_a.getText().toString());

        if(loanLength < 3 || loanLength > 10){
            length_a.setError("Must be between 3 to 10 years!");
            return false;
        }
        else{
            return true;
        }

    }

    //Method to allow user to save data to access later on
    private  void checkSharedPreferences(){
        String checkBox = mPreferences.getString(getString(R.string.checkbox), "False");
        String loanSF = mPreferences.getString(getString(R.string.loanSF), "");
        String dateSF = mPreferences.getString(getString(R.string.dateSF), "");
        String interestSF = mPreferences.getString(getString(R.string.interestSF), "");
        String lengthSF = mPreferences.getString(getString(R.string.lengthSF), "");

        loan_a.setText(loanSF);
        date_a.setText(dateSF);
        interest_a.setText(interestSF);
        length_a.setText(lengthSF);

        if(checkBox.equals("True")){
            check_box.setChecked(true);
        }
        else{
            check_box.setChecked(false);
        }

    }


    public void launchSecondActivity(View view){

    }

}
