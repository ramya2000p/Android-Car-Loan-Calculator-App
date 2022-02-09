package com.example.ramyacarloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView no_mlt, date_mlt, monthlyLoan_mlt, balance_mlt;
    double monthlyInstallment2, startingTotal2, totalMonths2, balance, newBalance;
    String loan, loanS, firstDate2;
    StringBuffer printNo = new StringBuffer();
    StringBuffer printDate = new StringBuffer();
    StringBuffer printLoan = new StringBuffer();
    StringBuffer printFirstBalance = new StringBuffer();
    StringBuffer printBalance = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Assigning XML objects for TextViews into variables
        monthlyLoan_mlt = (TextView) findViewById(R.id.monthlyLoan_mlt);
        balance_mlt = (TextView) findViewById(R.id.balance_mlt);
        no_mlt = (TextView) findViewById(R.id.no_mlt);
        date_mlt = (TextView) findViewById(R.id.date_mlt);

        //Passing data to double variables
        monthlyInstallment2 = getIntent().getDoubleExtra("MonthlyInstallment_1", monthlyInstallment2);
        startingTotal2 = getIntent().getDoubleExtra("StartingTotal_1", startingTotal2);
        totalMonths2 = getIntent().getDoubleExtra("TotalMonths_1", totalMonths2);
        firstDate2 = getIntent().getStringExtra("Date");


        //Setting the EditTexts
        //Set No.
        for(int i=1; i<totalMonths2; i++){
            printNo.append("" + i + "\t\n");
        }
        no_mlt.setText(printNo);

        //Set Date
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedDate = null;
        try {
            convertedDate = formatDate.parse(firstDate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(convertedDate);

        for(int i=1; i<totalMonths2; i++){
            c.add(Calendar.MONTH, 1);
            Date newDate = c.getTime();
            printDate.append("" + formatDate.format(newDate) + "\t\n");
        }
        date_mlt.setText(printDate);


        //Set Monthly Loan Amount
        loan = String.format("%.2f", monthlyInstallment2);

        for(int i=1; i<totalMonths2; i++){
            printLoan.append("RM "+ loan + "\t\n");
        }
        monthlyLoan_mlt.setText(printLoan);

        //Set Balance
        balance = startingTotal2 - monthlyInstallment2;
        printFirstBalance.append("RM " + balance + "\t\n");
        balance_mlt.setText(printFirstBalance);

        for(int i=1; i<totalMonths2; i++){
            newBalance = balance - monthlyInstallment2;
            loanS = String.format("%.2f", newBalance);
            printBalance.append("RM " + loanS + "\t\n");
            balance = newBalance;
        }
        balance_mlt.setText(printBalance);

    }
}