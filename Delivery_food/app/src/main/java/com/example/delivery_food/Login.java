package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.BoringLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button log_in = (Button) findViewById(R.id.log_in);
        EditText user_email = findViewById(R.id.my_email);
        EditText user_password = findViewById(R.id.my_password);

        DBHelper DB = new DBHelper(this);

        sp = getSharedPreferences("Data", MODE_PRIVATE);
        editor = sp.edit();
        boolean is_login = sp.getBoolean("IsLogIn", false);
        if( is_login == true){
            Intent login = new Intent(Login.this, Catalog.class);
            startActivity(login);
        }

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_email.getText().toString();
                String password = user_password.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Заполните данные", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkemailpassword = DB.check_email_password(email, password);
                    if (checkemailpassword == true){

                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.putBoolean("IsLogIn", true);
                        editor.apply();

                        Toast.makeText(Login.this, "Успешный вход", Toast.LENGTH_SHORT).show();
                        Intent log_in = new Intent(Login.this, Catalog.class);
                        startActivity(log_in);
                        finish();
                    }
                    else {
                        Toast.makeText(Login.this, "Ошибка входа", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}