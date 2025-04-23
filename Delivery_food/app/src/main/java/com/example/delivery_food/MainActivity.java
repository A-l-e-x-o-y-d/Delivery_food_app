package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper DB = new DBHelper(this);

        sp = getSharedPreferences("Data", MODE_PRIVATE);
        editor=sp.edit();
        boolean is_login = sp.getBoolean("IsLogIn", false);
        if( is_login == true){
            Intent login = new Intent(MainActivity.this, Catalog.class);
            startActivity(login);
        }

        Button log_in = (Button) findViewById(R.id.login);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_log_in = new Intent(MainActivity.this, Login.class);
                startActivity(switch_log_in);
            }
        });

        Button sign_up = findViewById(R.id.signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switch_sing_up = new Intent(MainActivity.this, Signup.class);
                startActivity(switch_sing_up);
            }
        });

//        Button insert_store = findViewById(R.id.insertDataStore);
//        insert_store.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DB.insert_store();
//            }
//        });
    }

    @Override
    public void onBackPressed(){
        this.finishAffinity();
    }
}