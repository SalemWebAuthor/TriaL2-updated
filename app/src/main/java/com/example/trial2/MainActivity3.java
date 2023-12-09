package com.example.trial2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private EditText username, password, repassword;
    private Button signup, signin;
    private DBHelperLogin DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);
        DB = new DBHelperLogin(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = repassword.getText().toString().trim();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
                    Toast.makeText(MainActivity3.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(repass)) {
                    Toast.makeText(MainActivity3.this, "Password not matching!", Toast.LENGTH_SHORT).show();
                } else {
                    if (DB.checkUsername(user)) {
                        Toast.makeText(MainActivity3.this, "User Already Exist! Please sign in", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean insert = DB.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(MainActivity3.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity3.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}