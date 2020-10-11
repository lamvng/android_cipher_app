package com.example.tp3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class DechiffrementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dechiffrement);
    }

    public String checkRadioButton(View view) {
        RadioGroup radioGroup;
        String algoDechiffreChecked = new String();
        RadioButton radioButtonCaesar, radioButtonVigenere;
        // Check with algorithm is selected
        radioButtonCaesar = (RadioButton) findViewById(R.id.radioButton_dechiffre_caesar);
        radioButtonVigenere = (RadioButton) findViewById(R.id.radioButton_dechiffre_vigenere);

        if (radioButtonCaesar.isChecked()) {
            algoDechiffreChecked = "Caesar";
        }
        else if (radioButtonVigenere.isChecked()) {
            algoDechiffreChecked = "Vigenere";
        }
        return algoDechiffreChecked;
    }


    public void showResult (View view) {
        // Pass textChiffrePass to ResultActivity
        EditText editTextText, editTextKey;
        String textChiffre, key;
        Intent intentDechiffrePassToResult;
        int chiffre_ou_pas = 0;
        String algoDechiffrechecked;

        algoDechiffrechecked = checkRadioButton(view);

        if (algoDechiffrechecked == "Vigenere") {
            key = "Not Available";
        }
        else {
            editTextKey = findViewById(R.id.edit_text_cle);
            key = (String) editTextKey.getText().toString();
        }

        editTextText = findViewById(R.id.edit_text_entrez_dechiffre);
        textChiffre = editTextText.getText().toString();

        if (TextUtils.isEmpty(textChiffre)) {
            Toast.makeText(getApplicationContext(), "Entrez votre texte chiffré SVP", Toast.LENGTH_SHORT);
        }
        else {
            // Send Intent as Parcelable to other activity
            // chiffre_ou_pas, algo, key, text
            intentDechiffrePassToResult = new Intent(DechiffrementActivity.this, ResultActivity.class);
            Parametres parametres = new Parametres();
            parametres.setChiffre_ou_pas(chiffre_ou_pas);
            parametres.setAlgo(algoDechiffrechecked);
            parametres.setKey(key);
            parametres.setInputText(textChiffre);

            intentDechiffrePassToResult.putExtra("parametres", parametres);
            startActivity(intentDechiffrePassToResult);
        }
    }


    public void backToMainMenu (View view) {
        Intent intentBackToMain = new Intent(DechiffrementActivity.this, MainActivity.class);
        startActivity(intentBackToMain);
        finish();
    }
}