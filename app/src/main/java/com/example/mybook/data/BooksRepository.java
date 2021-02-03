package com.example.mybook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class BooksRepository {

    public static final String TABLE_NAME = "book";

    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "book_title";
    public static final String COL_AUTHOR = "book_author";
    public static final String COL_PAGES = "book_pages";

    SQLiteDatabase myDB;

    // リポジトリ生成時に必ずSQLiteデータベースへの接続を受け取る
    public BooksRepository(SQLiteDatabase db) {
        myDB = db;
    }


    public void addData(String title, String author, int pages) {

        ContentValues cv = new ContentValues();

        cv.put(COL_TITLE, title);
        cv.put(COL_AUTHOR, author);
        cv.put(COL_PAGES, pages);
        myDB.insert(TABLE_NAME, null, cv);

    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;
        if(myDB != null) {
            cursor = myDB.rawQuery(query, null);
        }
        return cursor;
    }

    // 更新処理
    public void updateData(String row_id, String title, String author, String pages) {

        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, title);
        cv.put(COL_AUTHOR, author);
        cv.put(COL_PAGES, pages);

        myDB.update(TABLE_NAME, cv, COL_ID + " = ?", new String[]{row_id});

    }

    // 削除処理
    public void deleteOneRow(String row_id) {

        long result = myDB.delete(TABLE_NAME, COL_ID + " = ?", new String[]{row_id});

    }

    // 全削除処理
    public void deleteAllData() {
        myDB.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
