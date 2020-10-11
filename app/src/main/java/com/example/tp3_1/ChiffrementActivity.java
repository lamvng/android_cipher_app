package com.example.tp3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import android.os.Bundle;


public class ChiffrementActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chiffrement);

    }

    public String checkRadioButton(View view) {
        RadioGroup radioGroup;
        String algoChiffreChecked = new String();
        RadioButton radioButtonCaesar, radioButtonVigenere;

        // Check with algorithm is selected
        radioButtonCaesar = (RadioButton) findViewById(R.id.radioButton_chiffre_caesar);
        radioButtonVigenere = (RadioButton) findViewById(R.id.radioButton_chiffre_vigenere);

        if (radioButtonCaesar.isChecked()) {
            algoChiffreChecked = "Caesar";
        }
        else if (radioButtonVigenere.isChecked()) {
            algoChiffreChecked = "Vigenere";
        }
        return algoChiffreChecked;
    }


    public void showResult (View view) {
        // Pass textClairePass to ResultActivity
        EditText editTextText, editTextKey;
        String textClaire, key;
        Intent intentChiffrePassToResult;
        int chiffre_ou_pas = 1;
        String algoChiffreChecked;

        algoChiffreChecked = checkRadioButton(view);
        if (algoChiffreChecked == "Vigenere") {
            key = "Not Available";
        }
        else {
            editTextKey = findViewById(R.id.edit_text_cle);
            key = (String) editTextKey.getText().toString();
        }

        // Get user input: text
        editTextText = findViewById(R.id.edit_text_entrez_chiffre);
        textClaire = editTextText.getText().toString();

        if (TextUtils.isEmpty(textClaire)) {
            Toast.makeText(getApplicationContext(), "Entrez votre text claire SVP.", Toast.LENGTH_SHORT);
        }
        else {
            // Send Intent as Parcelable to other activity
            // chiffre_ou_pas, algo, key, text
            intentChiffrePassToResult = new Intent(ChiffrementActivity.this, ResultActivity.class);
            Parametres parametres = new Parametres();
            parametres.setChiffre_ou_pas(chiffre_ou_pas);
            parametres.setAlgo(algoChiffreChecked);
            parametres.setKey(key);
            parametres.setInputText(textClaire);

            intentChiffrePassToResult.putExtra("parametres", parametres);
            startActivity(intentChiffrePassToResult);
        }
    }


    public void backToMainMenu (View view) {
        Intent intentBackToMain = new Intent(ChiffrementActivity.this, MainActivity.class);
        startActivity(intentBackToMain);
        finish();
    }
}