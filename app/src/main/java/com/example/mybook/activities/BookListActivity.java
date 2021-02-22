package com.example.mybook.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybook.CustomAdapter;
import com.example.mybook.MyApplication;
import com.example.mybook.R;
import com.example.mybook.data.Book;
import com.example.mybook.data.BooksRepository;
import com.example.mybook.data.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_btn;
    ImageView empty_iv;
    TextView no_data_txt;

//    DatabaseHelper myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter = new CustomAdapter();

    private BooksRepository booksRepository = MyApplication.getInstance().getBooksRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        recyclerView = findViewById(R.id.recyclerView);
        empty_iv = findViewById(R.id.empty_iv);
        no_data_txt = findViewById(R.id.no_data_txt);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

//        myDB = new DatabaseHelper(BookListActivity.this);
//        book_id = new ArrayList<>();
//        book_title = new ArrayList<>();
//        book_author = new ArrayList<>();
//        book_pages = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(BookListActivity.this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(customAdapter);

//        customAdapter = new CustomAdapter(BookListActivity.this, this, book_id, book_title, book_author, book_pages);
    }

    protected void onResume(){
        super.onResume();

        //storeDataInArrays();    //前回リスト表示メソッド
        showBookList();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    //単語データの配列をデータベースから取得してリストに表示する
    private void showBookList() {
        List<Book> bookList = (ArrayList<Book>)booksRepository.readAllData();

        CustomAdapter customAdapter = (CustomAdapter)recyclerView.getAdapter();
        customAdapter.updateDataSet(bookList);

        if(bookList.size() == 0) {
            empty_iv.setVisibility(View.VISIBLE);
            no_data_txt.setVisibility(View.VISIBLE);
        } else {
            empty_iv.setVisibility(View.GONE);
            no_data_txt.setVisibility(View.GONE);
        }

    }

    //前回までのメソッド
//    void storeDataInArrays(){
//        List<Book> bookList = (ArrayList<Book>)booksRepository.readAllData();
//        customAdapter = (CustomAdapter)recyclerView.getAdapter();
//
//        CustomAdapter adapter = (CustomAdapter) recyclerView.getAdapter();
//
//        if (bookList.size() == 0) {
//            empty_iv.setVisibility(View.VISIBLE);
//            no_data_txt.setVisibility(View.VISIBLE);
//        } else {
////            customAdapter.updateDataSet(bookList);
//            empty_iv.setVisibility(View.GONE);
//            no_data_txt.setVisibility(View.GONE);
//        }


//        Cursor cursor = booksRepository.readAllData();
//        if (cursor.getCount() == 0) {
//            empty_iv.setVisibility(View.VISIBLE);
//            no_data_txt.setVisibility(View.VISIBLE);
//        } else {
//            while (cursor.moveToNext()) {
//                book_id.add(cursor.getString(0));
//                book_title.add(cursor.getString(1));
//                book_author.add(cursor.getString(2));
//                book_pages.add(cursor.getString(3));
//            }
//            empty_iv.setVisibility(View.GONE);
//            no_data_txt.setVisibility(View.GONE);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //DatabaseHelper myDB = new DatabaseHelper(BookListActivity.this);
                booksRepository.deleteAllData();

                // Refresh Activity
                Intent intent = new Intent(BookListActivity.this, BookListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}