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

    private Context context;

    SQLiteDatabase myDB;

    public BooksRepository(SQLiteDatabase db) {
        myDB = db;
    }
    // リポジトリ生成時に必ずSQLiteデータベースへの接続を受け取る↑


    public void addData(String title, String author, int pages) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_TITLE, title);
        cv.put(COL_AUTHOR, author);
        cv.put(COL_PAGES, pages);
        long result = myDB.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        //SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(myDB != null) {
            cursor = myDB.rawQuery(query, null);
        }
        return cursor;
    }

    // 更新処理
    public void updateData(String row_id, String title, String author, String pages) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, title);
        cv.put(COL_AUTHOR, author);
        cv.put(COL_PAGES, pages);

        long result = myDB.update(TABLE_NAME, cv, COL_ID + " = ?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed Updated.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    // 削除処理
    public void deleteOneRow(String row_id) {
        //SQLiteDatabase db = this.getWritableDatabase();
        long result = myDB.delete(TABLE_NAME, COL_ID + " = ?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    // 全削除処理
    public void deleteAllData() {
        //SQLiteDatabase db = this.getWritableDatabase();
        myDB.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
