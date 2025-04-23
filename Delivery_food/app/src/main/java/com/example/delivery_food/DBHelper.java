package com.example.delivery_food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="deliveryfood.db";
    public DBHelper(Context context) {
        super(context, "deliveryfood.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT unique, name TEXT, surname TEXT, patronymic TEXT, phone TEXT, password TEXT)");

        db.execSQL("create table user_address(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "address TEXT, " +
                "id_user TEXT)");

        db.execSQL("create table store(id INTEGER PRIMARY KEY AUTOINCREMENT, name_product TEXT, cost TEXT)");

        db.execSQL("create table orders(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_product INTEGER, " +
                "id_user INTEGER, " +
                "FOREIGN KEY ('id_product') REFERENCES store ('id'), " +
                "FOREIGN KEY ('id_user') REFERENCES users ('id'))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists user_address");
        db.execSQL("drop table if exists store");
        db.execSQL("drop table if exists orders");
    }

    public Boolean insertData(String email, String name, String surname, String patronymic, String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("name", name);
        values.put("surname", surname);
        values.put("patronymic", patronymic);
        values.put("email", email);
        values.put("phone", phone);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean check_email(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email = ?", new String[] {email});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean check_email_password(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email = ? and password = ?", new String[] {email, password});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public String[] get_user_data(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email = ?", new String[] {email});
        cursor.moveToPosition(0);

        String my_email = cursor.getString(1);
        String my_name = cursor.getString(2);
        String my_surname = cursor.getString(3);
        String my_patronymic = cursor.getString(4);
        String my_phone = cursor.getString(5);
        String my_id = cursor.getString(0);

        String[] user_data = new String[7];

        user_data[0] = my_surname.toString();
        user_data[1] = my_name.toString();
        user_data[2] = my_patronymic.toString();
        user_data[3] = my_email.toString();
        user_data[4] = my_phone.toString();
        user_data[5] = my_id.toString();

        return (user_data);
    }

    public int delete_user(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users","email = ?", new String[]{email});
    }

    public Boolean insert_user_address(String address, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("address", address);
        values.put("id_user", id);

        long result = db.insert("user_address", null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public String[] get_user_address(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_address where id_user = ?", new String[] {id});
        cursor.moveToPosition(0);
        String[] user_address = new String[cursor.getCount()];
        int i = 0;
        do{
            user_address[i] = cursor.getString(1);
            i++;
        }while (cursor.moveToNext());
        return (user_address);
    }

    public Boolean insert_new_address(String address, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("address", address);
        values.put("id_user", id);
        long result = db.insert("user_address", null, values);

        if (result == -1)
            return false;
        else
            return true;
    }

    public void delete_user_address(String address, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user_address","address = ? and id_user = ?",  new String[]{address, id});
    }

    public void insert_store(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("namep_product", "Яблоко");
        values.put("cost", "50");
        db.insert("store", null, values);

        values.put("name_product", "Груша");
        values.put("cost", "40");
        db.insert("store", null, values);

        values.put("name_product", "Вишня");
        values.put("cost", "70");
        db.insert("store", null, values);

        values.put("name_product", "Апельсин");
        values.put("cost", "60");
        db.insert("store", null, values);

        values.put("name_product", "Банан");
        values.put("cost", "55");
        db.insert("store", null, values);

        values.put("name_product", "Капуста");
        values.put("cost", "100");
        db.insert("store", null, values);

        values.put("name_product", "Картофель");
        values.put("cost", "120");
        db.insert("store", null, values);

        values.put("name_product", "Огурец");
        values.put("cost", "90");
        db.insert("store", null, values);

        values.put("name_product", "Помидор");
        values.put("cost", "95");
        db.insert("store", null, values);

        values.put("name_product", "Морковь");
        values.put("cost", "100");
        db.insert("store", null, values);

        values.put("name_product", "Говядина");
        values.put("cost", "300");
        db.insert("store", null, values);

        values.put("name_product", "Баранина");
        values.put("cost", "400");
        db.insert("store", null, values);

        values.put("name_product", "Свинина");
        values.put("cost", "350");
        db.insert("store", null, values);

        values.put("name_product", "Курица");
        values.put("cost", "340");
        db.insert("store", null, values);

        values.put("name_product", "Вода");
        values.put("cost", "100");
        db.insert("store", null, values);

        values.put("name_product", "Сoca-cola");
        values.put("cost", "130");
        db.insert("store", null, values);

        values.put("name_product", "Fanta");
        values.put("cost", "120");
        db.insert("store", null, values);

        values.put("name_product", "Seven-up");
        values.put("cost", "110");
        db.insert("store", null, values);

        values.put("name_product", "Pepsi");
        values.put("cost", "115");
        db.insert("store", null, values);

        values.put("name_product", "Белый хлеб");
        values.put("cost", "50");
        db.insert("store", null, values);

        values.put("name_product", "Чёрный хлеб");
        values.put("cost", "60");
        db.insert("store", null, values);

        values.put("name_product", "Пончики");
        values.put("cost", "100");
        db.insert("store", null, values);

        values.put("name_product", "Вишнёвая булочка");
        values.put("cost", "55");
        db.insert("store", null, values);
    }
 }
