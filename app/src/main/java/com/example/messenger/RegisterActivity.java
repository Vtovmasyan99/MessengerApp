package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mUserName, mPasswordOne, mPasswordTwo;
    Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUserName = (EditText) findViewById(R.id.et_username_register);
        mPasswordOne = (EditText) findViewById(R.id.et_password1_register);
        mPasswordTwo = (EditText) findViewById(R.id.et_password2_register);
        mRegisterButton = (Button) findViewById(R.id.btn_register);
    }

    public void register(View v) {
        String password_one = mPasswordOne.getText().toString();
        String password_two = mPasswordTwo.getText().toString();
        String userName = mUserName.getText().toString();
        if (password_one.equals(password_two) && !password_one.isEmpty() && !userName.isEmpty()) {
            Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
            RegisterActivity.this.startActivity(myIntent);
            mPasswordTwo.setText("");
            mPasswordOne.setText("");
            mUserName.setText("");
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Both passwords should be the same!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public void sendOtp(View v) {

    }

}