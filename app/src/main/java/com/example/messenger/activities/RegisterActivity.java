package com.example.messenger.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messenger.R;
import com.example.messenger.helpers.SecurePrefsHelper;
import com.example.messenger.models.UserModel;

public class RegisterActivity extends AppCompatActivity {
    EditText mUserName, mPasswordOne, mPasswordTwo,mEmail,mRealName,mGender,mBirthday;
    Button mRegisterButton;
    UserModel myUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUserName = (EditText) findViewById(R.id.et_username_register);
        mPasswordOne = (EditText) findViewById(R.id.et_password1_register);
        mPasswordTwo = (EditText) findViewById(R.id.et_password2_register);
        mRegisterButton = (Button) findViewById(R.id.btn_register);

        mEmail = (EditText)findViewById(R.id.et_email_register);
        mRealName = (EditText)findViewById(R.id.et_real_name_register);
        mGender = (EditText)findViewById(R.id.et_gender_register);
        mBirthday = (EditText)findViewById(R.id.et_birthday_register);


    }

    public void register(View v) {
        String password_one = mPasswordOne.getText().toString();
        String password_two = mPasswordTwo.getText().toString();
        String userName = mUserName.getText().toString();
        String email = mEmail.getText().toString();
        String realName = mRealName.getText().toString();
        String gender = mGender.getText().toString();
        String birthday = mBirthday.getText().toString();
        if (password_one.equals(password_two) && !password_one.isEmpty() && !userName.isEmpty() && !email.isEmpty() &&
            !realName.isEmpty() && !gender.isEmpty() && !birthday.isEmpty())
        {
            myUser = new UserModel(userName, password_one, realName, 1,R.drawable.avatar, gender, email);
            myUser.setBirthday(birthday);
            SecurePrefsHelper.saveMyUserInSecurePrefs(myUser, this);
            Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
            RegisterActivity.this.startActivity(myIntent);

            mPasswordTwo.setText("");
            mPasswordOne.setText("");
            mUserName.setText("");
            mEmail.setText("");
            mRealName.setText("");
            mGender.setText("");
            mBirthday.setText("");
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Both passwords should be the same!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


}