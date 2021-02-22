package com.example.mybook.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

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


//    public Cursor readAllData() {
//        String query = "SELECT * FROM " + TABLE_NAME;
//
//        Cursor cursor = null;
//        if(myDB != null) {
//            cursor = myDB.rawQuery(query, null);
//        }
//        return cursor;
//    }
    public List<Book> readAllData() {

        ArrayList<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        try(final Cursor cursor = myDB.rawQuery(query, null)) {
            while (cursor.moveToNext()) {
                final Book book = buildBookFromCursor(cursor);
                bookList.add(book);
//                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
//                String bookTitle = cursor.getString(cursor.getColumnIndex(COL_TITLE));
//                String bookAuthor = cursor.getString(cursor.getColumnIndex(COL_AUTHOR));
//                int bookPages = cursor.getInt(cursor.getColumnIndex(COL_PAGES));
//
//                final Book book = new Book(id, bookTitle, bookAuthor, bookPages);
//                bookList.add(book);

            }
        }
        return bookList;
    }

    private Book buildBookFromCursor(Cursor c) {
        int id = c.getInt(c.getColumnIndex(COL_ID));
        String bookTitle = c.getString(c.getColumnIndex(COL_TITLE));
        String bookAuthor = c.getString(c.getColumnIndex(COL_AUTHOR));
        int bookPages = c.getInt(c.getColumnIndex(COL_PAGES));

        final Book book = new Book(id, bookTitle, bookAuthor, bookPages);
        return book;
    }


//    public long addData(String title, String author, int pages) {
    public long addData(Book book) throws InvalidKeyException, SQLException {

        ContentValues cv = new ContentValues();

        cv.put(COL_TITLE, book.getBookTitle());
        cv.put(COL_AUTHOR, book.getBookAuthor());
        cv.put(COL_PAGES, book.getBookPages());

        return myDB.insert(TABLE_NAME, null, cv);

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
