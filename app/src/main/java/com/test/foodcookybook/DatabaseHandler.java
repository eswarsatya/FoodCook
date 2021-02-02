package com.test.foodcookybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.foodcookybook.Models.MyList;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mealfav";
    public static final String TABLE_FAV = "favourites";
    public static final String KEY_ID = "idmeal";
    public static final String KEY_NAME = "strMeal";
    public static final String KEY_IMG_URl = "strMealThumb";
    public static final String KEY_CATEGORY = "strCategory";
    public static final String KEY_AREA = "strArea";
    public static final String KEY_SELECTED ="fav_issel";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_FAV + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_IMG_URl + " TEXT," + KEY_CATEGORY + " TEXT," + KEY_AREA + " TEXT," +  KEY_SELECTED + " INTEGER"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAV);

        // Create tables again
        onCreate(db);
    }
    void addfav(MyList contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,contact.idMeal);
        values.put(KEY_NAME, contact.strMeal); // Contact Name
        values.put(KEY_IMG_URl, contact.strMealThumb);
        values.put(KEY_CATEGORY, contact.strCategory);
        values.put(KEY_AREA, contact.strArea);// Contact Phone
        values.put(KEY_SELECTED, contact.issel);
        // Inserting Row
        db.insert(TABLE_FAV, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    MyList getfav(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FAV, new String[] { KEY_ID,
                        KEY_NAME, KEY_IMG_URl, KEY_CATEGORY,KEY_AREA }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        MyList contact = new MyList((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
        // return contact
        return contact;
    }
    public List<MyList> getAllfav() {
        List<MyList> contactList = new ArrayList<MyList>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FAV;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyList contact = new MyList();
                contact.setIdMeal((cursor.getString(0)));
                contact.setStrMeal(cursor.getString(1));
                contact.setStrMealThumb(cursor.getString(2));
                contact.setStrCategory(cursor.getString(3));
                contact.setStrArea(cursor.getString(4));
                contact.setIssel(cursor.getInt(5));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updatefav(MyList contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID,contact.idMeal);
        values.put(KEY_NAME, contact.strMeal); // Contact Name
        values.put(KEY_IMG_URl, contact.strMealThumb);
        values.put(KEY_CATEGORY, contact.strCategory);
        values.put(KEY_AREA, contact.strArea);
        values.put(KEY_SELECTED, contact.issel);
        // updating row
        return db.update(TABLE_FAV, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getIdMeal()) });
    }

}
