package com.example.tp3_1;

import android.os.Parcel;
import android.os.Parcelable;

// https://www.youtube.com/watch?v=WBbsvqSu0is
// https://guides.codepath.com/android/using-parcelable
public class Parametres implements Parcelable {
    // Parcel data types
    private int chiffre_ou_pas;
    private String algo;
    private String key;
    private String inputText;

    // Define the variables to be getted and setted
    public Parametres() {
    }

    protected Parametres(Parcel in) {
        chiffre_ou_pas = in.readInt();
        algo = in.readString();
        key = in.readString();
        inputText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(chiffre_ou_pas);
        dest.writeString(algo);
        dest.writeString(key);
        dest.writeString(inputText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Parametres> CREATOR = new Creator<Parametres>() {
        @Override
        public Parametres createFromParcel(Parcel in) {
            return new Parametres(in);
        }

        @Override
        public Parametres[] newArray(int size) {
            return new Parametres[size];
        }
    };

    public int getChiffre_ou_pas() { return chiffre_ou_pas; }

    public String getAlgo() {
        return algo;
    }

    public String getKey() {
        return key;
    }

    public String getInputText() {
        return inputText;
    }

    public void setChiffre_ou_pas(int chiffre_ou_pas) {
        this.chiffre_ou_pas = chiffre_ou_pas;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
}