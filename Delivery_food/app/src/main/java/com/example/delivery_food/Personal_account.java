package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Personal_account extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_account);

        sp = getSharedPreferences("Data", MODE_PRIVATE);
        String email = sp.getString("email","");

        DBHelper DB = new DBHelper(this);

        Button logout = (Button) findViewById(R.id.exit);
        Button delete_account = (Button) findViewById(R.id.delete_account);
        Button address_edit = (Button) findViewById(R.id.address_edit);

        TextView my_surname = findViewById(R.id.my_surname);
        TextView my_name = findViewById(R.id.my_name);
        TextView my_patronymic = findViewById(R.id.my_patronymic);
        TextView my_email = findViewById(R.id.myemail);
//        TextView my_address = findViewById(R.id.address_order);
        TextView my_phone = findViewById(R.id.my_phone);

        String[] userdata = DB.get_user_data(email);

        my_surname.setText(userdata[0]);
        my_name.setText(userdata[1]);
        my_patronymic.setText(userdata[2]);
        my_email.setText(userdata[3]);
        my_phone.setText(userdata[4]);

        sp = getSharedPreferences("Data", MODE_PRIVATE);
        editor=sp.edit();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                Intent log_out = new Intent(Personal_account.this, MainActivity.class);
                startActivity(log_out);
                finish();
            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                Intent delete_account = new Intent(Personal_account.this, MainActivity.class);
                startActivity(delete_account);
                int del_user = DB.delete_user(email);
                Toast.makeText(Personal_account.this, "Аккунт успешно удалён", Toast.LENGTH_SHORT).show();
            }
        });

        address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_to_address_edit = new Intent(Personal_account.this, Address_edit.class);
                startActivity(switch_to_address_edit);
            }
        });
    }
}