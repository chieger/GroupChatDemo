package com.example.charleshieger.groupchatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void onSignUp (View v) {
        EditText etUsernameField = (EditText) findViewById(R.id.etUsernameField);
        EditText etPasswordField = (EditText) findViewById(R.id.etPasswordField);

        String username = etUsernameField.getText().toString();
        String password = etPasswordField.getText().toString();

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "You are all signed up!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogInActivity.this, ChatActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onLogIn (View v) {
        EditText etUsernameField = (EditText) findViewById(R.id.etUsernameField);
        EditText etPasswordField = (EditText) findViewById(R.id.etPasswordField);

        String username = etUsernameField.getText().toString();
        String password = etPasswordField.getText().toString();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    Toast.makeText(getApplicationContext(),"Log In Successful!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LogInActivity.this, ChatActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
