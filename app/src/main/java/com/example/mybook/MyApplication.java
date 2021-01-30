package com.example.mybook;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.mybook.data.BooksRepository;
import com.example.mybook.data.DatabaseHelper;

public class MyApplication extends Application {

    private DatabaseHelper dbHelper = null;

    private static MyApplication sInstance;

    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    @Override
    public void onTerminate() {
        dbHelper.close();
        dbHelper = null;

        super.onTerminate();
    }

    private SQLiteDatabase getDB() {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(this);
        }

        return dbHelper.getWritableDatabase();
    }

    public BooksRepository getBooksRepository() {
        return new BooksRepository(getDB());
    }
}
