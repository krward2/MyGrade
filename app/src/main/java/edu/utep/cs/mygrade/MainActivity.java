package edu.utep.cs.mygrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    //Variables to take in the username and password
    private EditText userEdit;
    private EditText pinEdit;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userEdit = (EditText)findViewById(R.id.userEdit);
        pinEdit = (EditText)findViewById(R.id.pinEdit);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this::loginClicked);
    }

    public void loginClicked(View view){
        //Creating an intent that passes the username and password to the grade activity
        Intent gradeActivity = new Intent(this, GradeActivity.class);
        username = userEdit.getText().toString();
        password = pinEdit.getText().toString();
        gradeActivity.putExtra("username", username);
        gradeActivity.putExtra("password", password);
        startActivity(gradeActivity);
    }


}
