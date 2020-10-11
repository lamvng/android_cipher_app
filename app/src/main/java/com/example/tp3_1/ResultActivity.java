package com.example.tp3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    // TODO: TP2.3, Autre Chiffre et Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intentPassed;
        TextView textView;
        int step;

        intentPassed = getIntent();
        Parametres parametres = intentPassed.getParcelableExtra("parametres");
        int chiffre_ou_pas = parametres.getChiffre_ou_pas();
        String algo = parametres.getAlgo();
        String key = parametres.getKey();
        String inputText = parametres.getInputText();

        if (chiffre_ou_pas == 1) {
            textView = (TextView) findViewById(R.id.text_show_result);

            if (algo.equals("Caesar")) {
                step = Integer.parseInt(key);
                String resultTextChiffre = chiffreCaesar(inputText, step);
                textView.setText(resultTextChiffre);
            }
            else if (algo.equals("Vigenere")) {
                key = "KEY";
                String keySequence = generateKey(inputText, key);
                String resultTextChiffre = chiffreVigenere(inputText, keySequence);
                textView.setText(resultTextChiffre);
            }
        }
        else if (chiffre_ou_pas == 0) {
            textView = (TextView) findViewById(R.id.text_show_result);

            if (algo.equals("Caesar")) {
                step = Integer.parseInt(key);
                String resultTextDechiffre = deChiffreCaesar(inputText, step);
                textView.setText(resultTextDechiffre);
            }

            else if (algo.equals("Vigenere")) {
                key = "KEY";
                String keySequence = generateKey(inputText, key);
                String resultTextDechiffre = deChiffreVigenere(inputText, keySequence);
                textView.setText(resultTextDechiffre);
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "What the fuck is going on...", Toast.LENGTH_SHORT);
        }
    }


    private String chiffreCaesar(String inputStringClaire, int step) {
        String upperString = inputStringClaire.toUpperCase();
        StringBuffer output;
        Character c;
        String newC;
        int prevAscii, newAscii;
        output = new StringBuffer();

        for (int i = 0; i < upperString.length(); i++) {
            c = upperString.charAt(i);
            prevAscii = (int) c;
            if ((prevAscii >= (int)'A' && prevAscii <= (int)'Z')) {
                newAscii = prevAscii + 3;
                if (newAscii > (int)'Z') {
                    newAscii -= 26;
                }
                newC = Character.toString((char) newAscii);
            }
            else {
                newC = Character.toString(c);
            }
            output.append(newC);
        }
        return output.toString();
    }


    private String deChiffreCaesar(String inputStringChiffre, int step) {
        String upperString = inputStringChiffre.toUpperCase();
        StringBuffer output;
        Character c;
        String newC;
        int prevAscii, newAscii;
        output = new StringBuffer();

        for (int i = 0; i < upperString.length(); i++) {
            c = upperString.charAt(i);
            prevAscii = (int) c;
            if ((prevAscii >= (int)'A' && prevAscii <= (int)'Z')) {
                newAscii = prevAscii - 3;
                if (newAscii > (int)'Z') {
                    newAscii += 26;
                }
                newC = Character.toString((char) newAscii);
            }
            else {
                newC = Character.toString(c);
            }
            output.append(newC);
        }
        return output.toString();
    }


    private String chiffreVigenere (String inputStringClaire, String key) {
        StringBuffer output = new StringBuffer();
        String upperString = inputStringClaire.toUpperCase();
        int inputLength = upperString.length();
        Character inputChar, keyChar;
        String newC;
        int prevAscii, newAscii;

        for (int i=0; i<inputLength; i++) {
            inputChar = upperString.charAt(i);
            keyChar = key.charAt(i);
            prevAscii = (int) inputChar;

            if (prevAscii >= 65 && prevAscii <= 90) {
                newAscii = ((int)inputChar + (int)keyChar) % 26;
                newAscii += (int)'A';
                newC = Character.toString((char) newAscii);
                output.append(newC);
            }
            else {
                output.append(inputChar);
            }
        }

        return output.toString();
    }


    private String deChiffreVigenere (String inputStringChiffre, String key) {
        StringBuffer output = new StringBuffer();
        String upperString = inputStringChiffre.toUpperCase();
        int inputLength = upperString.length();
        Character inputChar, keyChar;
        String newC;
        int prevAscii, newAscii;

        for (int i=0; i<inputLength; i++) {
            inputChar = upperString.charAt(i);
            keyChar = key.charAt(i);
            prevAscii = (int) inputChar;

            if (prevAscii >= (int)'A' && prevAscii <= (int)'Z') {
                newAscii = ((int)inputChar - (int)keyChar + 26) % 26;
                newAscii += (int)'A';
                newC = Character.toString((char) newAscii);
                output.append(newC);
            }
            else {
                output.append(inputChar);
            }
        }

        return output.toString();
    }

    // generate key for Vigenere Cipher
    private String generateKey (String text, String keyword) {
        keyword = keyword.toUpperCase();
        int keywordLength = keyword.length();
        int textLength = text.length();
        StringBuffer output = new StringBuffer();

        for (int i=0; ; i++) {
            if (i == keywordLength) {
                i = 0;
            }
            output.append(keyword.charAt(i));
            if (output.length() == textLength) {
                break;
            }
        }
        return output.toString();
    }

    public void backToMainMenu (View view) {
        Intent intentBackToMain = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intentBackToMain);
        finish();
    }
}