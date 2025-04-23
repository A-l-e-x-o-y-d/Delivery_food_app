package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button sign_up = (Button) findViewById(R.id.sign_up);
        DBHelper DB = new DBHelper(this);

        EditText user_name = (EditText) findViewById(R.id.name);
        EditText user_surname = (EditText) findViewById(R.id.surname);
        EditText user_patronymic = (EditText) findViewById(R.id.patronymic);
        EditText user_address = (EditText) findViewById(R.id.address);
        EditText user_phone = (EditText) findViewById(R.id.phone);
        EditText user_email = (EditText) findViewById(R.id.email);
        EditText user_password = (EditText) findViewById(R.id.password);
        EditText user_confirm_password = (EditText) findViewById(R.id.confirm_password);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user_name.getText().toString();
                String surname = user_surname.getText().toString();
                String patronymic = user_patronymic.getText().toString();
                String address = user_address.getText().toString();
                String phone = user_phone.getText().toString();
                String email = user_email.getText().toString();
                String password = user_password.getText().toString();
                String confirm_password = user_confirm_password.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(patronymic) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)|| TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(Signup.this, "Заполните данные", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(password.equals(confirm_password)){
                        Boolean checkemail = DB.check_email(email);
                        if(checkemail == false){
                            Boolean insert = DB.insertData(email, name, surname, patronymic, phone, password);
                            if (insert == true){
                                String[] userdata = DB.get_user_data(email);
                                Boolean insert_address = DB.insert_user_address(address, userdata[5]);
                                if (insert_address == true){
                                    Toast.makeText(Signup.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                                    Intent sign_up = new Intent(Signup.this, Login.class);
                                    startActivity(sign_up);
                                }
                                else {
                                    Toast.makeText(Signup.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(Signup.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(Signup.this, "Пользователь уже существует", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Signup.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}