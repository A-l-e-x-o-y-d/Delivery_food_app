package com.example.delivery_food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Order extends AppCompatActivity {

    SharedPreferences sp;

    private EditText editTextDate;
    private Button buttonDate;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private EditText editTextTime;
    private Button buttonTime;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        this.editTextDate = (EditText) this.findViewById(R.id.editText_date);
        this.buttonDate = (Button) this.findViewById(R.id.button_date);
        this.editTextTime = (EditText) this.findViewById(R.id.editText_time);
        this.buttonTime = (Button) this.findViewById(R.id.button_time);
        Button pay = findViewById(R.id.pay);
        TextView res_full_cost = findViewById(R.id.res_full_cost);
        EditText card_number = findViewById(R.id.number_card);
        EditText card_date = findViewById(R.id.date_card);
        EditText cvv = findViewById(R.id.cvv);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton non_cash = findViewById(R.id.non_cash);
        RadioButton cash = findViewById(R.id.cash);

        card_number.setEnabled(false);
        card_date.setEnabled(false);
        cvv.setEnabled(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.cash:
                        card_number.setEnabled(false);
                        card_date.setEnabled(false);
                        cvv.setEnabled(false);
                        break;
                    case R.id.non_cash:
                        card_number.setEnabled(true);
                        card_date.setEnabled(true);
                        cvv.setEnabled(true);
                        break;
                }
            }
        });

        String full_cost = getIntent().getStringExtra("res_full_cost");
        res_full_cost.setText(full_cost);

        this.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });

        this.buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectTime();
            }
        });

        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);

//        TextView address_order = findViewById(R.id.address_order);

        sp = getSharedPreferences("Data", MODE_PRIVATE);
        String email = sp.getString("email","");

        DBHelper DB = new DBHelper(this);
        String[] user_data = DB.get_user_data(email);

        String[] user_address = DB.get_user_address(user_data[5]);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, user_address);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner address_spinner = (Spinner) findViewById(R.id.address_user);
        address_spinner.setAdapter(adapter);

        address_spinner.setPrompt("Выберите адрес");
        address_spinner.setSelection(0);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (address_spinner.getSelectedItem().toString().equals("") || editTextDate.getText().toString().equals("") || editTextTime.getText().toString().equals("") || radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(Order.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
                else if(non_cash.isChecked() && (TextUtils.isEmpty(card_number.getText()) || TextUtils.isEmpty(card_date.getText()) || TextUtils.isEmpty(cvv.getText()))) {
                        Toast.makeText(Order.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                    }
                else {
                    Cart_model.items_product.clear();
                    Cart_model.items_cost.clear();
                    Toast.makeText(Order.this, "Заказ оплачен", Toast.LENGTH_SHORT).show();
                    Intent switch_to_catalog = new Intent(Order.this, Catalog.class);
                    startActivity(switch_to_catalog);
                }
            }
        });
    }

    private void buttonSelectDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };
        DatePickerDialog datePickerDialog = null;
        datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void buttonSelectTime()  {
        if(this.lastSelectedHour == -1)  {
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute = c.get(Calendar.MINUTE);
        }

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTextTime.setText(hourOfDay + ":" + minute );
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
            }
        };


        TimePickerDialog timePickerDialog = null;
        timePickerDialog = new TimePickerDialog(this,
                    android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timeSetListener, lastSelectedHour, lastSelectedMinute, true);
        timePickerDialog.show();
    }
}