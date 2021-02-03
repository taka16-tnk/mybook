package com.example.mybook.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybook.MyApplication;
import com.example.mybook.R;
import com.example.mybook.data.BooksRepository;

import static com.example.mybook.services.CommonHelper.showToast;

public class UpdateActivity extends AppCompatActivity {

    EditText title_edit, author_edit, pages_edit;
    Button update_btn_submit, delete_btn_submit;

    String id, title, author, pages;

    Handler handler;

    private BooksRepository booksRepository = MyApplication.getInstance().getBooksRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_edit = findViewById(R.id.title_edit);
        author_edit = findViewById(R.id.author_edit);
        pages_edit = findViewById(R.id.pages_edit);
        update_btn_submit = findViewById(R.id.update_btn_submit);
        delete_btn_submit = findViewById(R.id.delete_btn_submit);

        handler = new Handler(this.getMainLooper());

        getAndSetIntentData();

        // Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = title_edit.getText().toString().trim();
                author = author_edit.getText().toString().trim();
                pages = pages_edit.getText().toString().trim();
                booksRepository.updateData(id, title, author, pages);

                showToast(handler, getApplicationContext(), R.string.msg_update);

            }
        });

        delete_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")
                && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            // Setting Intent Data
            title_edit.setText(title);
            author_edit.setText(author);
            pages_edit.setText(pages);
        } else {
            showToast(handler, getApplicationContext(), R.string.msg_no_data);
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                booksRepository.deleteOneRow(id);
                showToast(handler, getApplicationContext(), R.string.msg_delete);
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

    @Override
    public void onBackPressed() { }
}