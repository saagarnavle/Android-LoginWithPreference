package com.example.priyanka.loginwithpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener {

    private EditText userEditText, passEditText;
    private CheckBox saveCheckBox;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userEditText = (EditText)findViewById(R.id.userEditText);
        passEditText = (EditText)findViewById(R.id.passEditText);
        saveCheckBox = (CheckBox)findViewById(R.id.saveCheckBox);

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            saveCheckBox.setChecked(true);
        else
            saveCheckBox.setChecked(false);

        userEditText.addTextChangedListener(this);
        passEditText.addTextChangedListener(this);
        saveCheckBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs() {
        if(saveCheckBox.isChecked()){
            editor.putString(KEY_USERNAME, userEditText.getText().toString().trim());
            editor.putString(KEY_PASS, passEditText.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }
        else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
    }

}
