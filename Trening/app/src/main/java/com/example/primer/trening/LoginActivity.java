package com.example.primer.trening;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    String email = "u";
    String password = "p";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void toMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.email);

        String input_email = email.getText().toString();
        String input_password = password.getText().toString();

        if (email.equals(input_email) && password.equals(input_password)){
            startActivity(intent);
        }
        else{
            email.setText("error");
        }
    }

}
