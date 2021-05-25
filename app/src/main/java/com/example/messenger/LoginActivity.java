package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private String userId;
    private String password;
    EditText user_id;
    EditText user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_id = (EditText) findViewById(R.id.et_user_id);
        user_password = (EditText) findViewById(R.id.et_password);
    }

    public void onSubmit(View v) {
        userId = user_id.getText().toString();
        password = user_password.getText().toString();
        if (userId.equals("User1") && password.equals("password1")) {
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(myIntent);

        } else {
            Context context = getApplicationContext();
            CharSequence text = "Wrong username or password!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        user_password.setText("");
        user_id.setText("");
    }

    public void toRegister(View v) {
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(myIntent);
    }
}