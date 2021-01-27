package com.example.mybook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybook.R;
import com.example.mybook.data.DatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText title_edit, author_edit, pages_edit;
    Button update_btn_submit;

    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_edit = findViewById(R.id.title_edit);
        author_edit = findViewById(R.id.author_edit);
        pages_edit = findViewById(R.id.pages_edit);
        update_btn_submit = findViewById(R.id.update_btn_submit);

        getAndSetIntentData();

        update_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                title = title_edit.getText().toString().trim();
                author = author_edit.getText().toString().trim();
                pages = pages_edit.getText().toString().trim();
                myDB.updateData(id, title, author, pages);
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
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}