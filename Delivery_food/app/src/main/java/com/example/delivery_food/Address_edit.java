package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Address_edit extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);

        Button delete_address = findViewById(R.id.delete_address);
        Button add_address = findViewById(R.id.add_address);
        EditText edit_add_address = findViewById(R.id.address_add_edit);

        sp = getSharedPreferences("Data", MODE_PRIVATE);
        String email = sp.getString("email","");

        DBHelper DB = new DBHelper(this);
        String[] user_data = DB.get_user_data(email);

        String[] user_address = DB.get_user_address(user_data[5]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, user_address);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner address_spinner = (Spinner) findViewById(R.id.address_delete_spinner);
        address_spinner.setAdapter(adapter);

        address_spinner.setPrompt("Выберите адрес");
        address_spinner.setSelection(0);

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean insert_res = DB.insert_user_address(edit_add_address.getText().toString(), user_data[5]);
                if (insert_res == true){
                    finish();
                    startActivity(getIntent());
                    Toast.makeText(Address_edit.this, "Адрес добавлен", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Address_edit.this, "Ошибка добавления адреса", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] user_address = DB.get_user_address(user_data[5]);
                if (user_address.length == 1){
                    Toast.makeText(Address_edit.this, "Нельзя удалить последний оставшийся адрес", Toast.LENGTH_SHORT).show();
                }
                else {
                    DB.delete_user_address(address_spinner.getSelectedItem().toString(), user_data[5]);
                    finish();
                    startActivity(getIntent());
                    Toast.makeText(Address_edit.this, "Адрес удалён", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}