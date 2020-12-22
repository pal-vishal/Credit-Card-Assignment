package com.example.creditcardinputexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText cardNo, date, securityCode, firstName, lastName;
    private String cardNoString, dateString, codeString, firstNameString, lastNameString;

    private MaterialButton submit;
    ArrayList<String> listOfPattern;
    private String Visa, MasterCard, AmericanExpress, Discover;
    public static String expiryDateChecker = "(?:0[1-9]|1[0-2])/[0-9]{2}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardNo = findViewById(R.id.creditCardNumber);
        date = findViewById(R.id.date);
        securityCode = findViewById(R.id.securityCode);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        cardNoString = cardNo.getText().toString();
        dateString = date.getText().toString();
        codeString = securityCode.getText().toString();
        firstNameString = firstName.getText().toString();
        lastNameString = lastName.getText().toString();
        submit = findViewById(R.id.button);

        listOfPattern = new ArrayList<>();
        Visa = "^4[0-9]{6,}$";
        listOfPattern.add(Visa);
        MasterCard = "^5[1-5][0-9]{5,}$";
        listOfPattern.add(MasterCard);
        AmericanExpress = "^3[47][0-9]{5,}$";
        listOfPattern.add(AmericanExpress);
        Discover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(Discover);

        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                if (charSequence.equals("")) {
                    return charSequence;
                } else if (charSequence.toString().matches("[a-zA-Z ]+")) {
                    return charSequence;
                } else {
                    Toast.makeText(MainActivity.this, "Only alphabets and spaces are allowed", Toast.LENGTH_SHORT).show();

                    return "";
                }

            }
        };


        firstName.setFilters(new InputFilter[]{inputFilter});
        lastName.setFilters(new InputFilter[]{inputFilter});


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardNo.getText().toString().isEmpty() && date.getText().toString().isEmpty() &&
                        securityCode.getText().toString().isEmpty() && firstName.getText().toString().isEmpty() && lastName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    checkOtherFields();
                }
            }
        });


    }

    private void checkOtherFields() {
        if (cardNo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter card number", Toast.LENGTH_SHORT).show();
        } else if (date.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter expiry date", Toast.LENGTH_SHORT).show();
        } else if (securityCode.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter cvv", Toast.LENGTH_SHORT).show();
        } else if (firstName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter first name", Toast.LENGTH_SHORT).show();
        } else if (lastName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter last name", Toast.LENGTH_SHORT).show();
        } else if (!date.getText().toString().trim().matches(expiryDateChecker)) {
            Toast.makeText(this, "Enter valid expiry date", Toast.LENGTH_SHORT).show();
        } else {
            if (isValidCardNumber(cardNo.getText().toString())) {
                String number = cardNo.getText().toString();
                for (String p : listOfPattern) {
                    if (number.matches(p)) {
                        showPopUp();
                        break;
                    }
                }
            } else {
                Toast.makeText(MainActivity.this, "Enter valid card number", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void showPopUp() {
        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("Payment Successful")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    public static boolean isValidCardNumber(String cardNumber) {
        int nDigits = cardNumber.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {

            int d = cardNumber.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

    public static boolean isValidExpiryDate(String expiryDate) {
        if (expiryDate.matches(expiryDateChecker)) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean isValidName(String name) {
        if (name.matches("[a-zA-Z ]+")) {
            return true;
        } else {
            return false;
        }
    }


}