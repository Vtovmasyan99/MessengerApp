package com.example.messenger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messenger.R;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.UserModel;

public class LoginActivity extends AppCompatActivity {
    private String userId;
    private String password;
    EditText user_id;
    EditText user_password;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_id = (EditText) findViewById(R.id.et_user_id);
        user_password = (EditText) findViewById(R.id.et_password);
        userModel = SecurePrefsHelper.getMyUserInSecurePrefs(this);
        if (userModel!=null) {
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(myIntent);
        }
    }

    public void onSubmit(View v) {
        userId = user_id.getText().toString();
        password = user_password.getText().toString();
        if (userId.equals("User1") && password.equals("password1")) {
            UserModel myUser = new UserModel("User1", "password1", "Vahe Tovmasyan", 1,R.drawable.avatar, "Male", "vahetovmasyan99@gmail.com");
            SecurePrefsHelper.saveMyUserInSecurePrefs(myUser, this);
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(myIntent);

        } else {
            CharSequence text = "Wrong username or password!";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

        }
        user_password.setText("");
        user_id.setText("");
    }

    public void toRegister(View v) {
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(myIntent);
    }
}