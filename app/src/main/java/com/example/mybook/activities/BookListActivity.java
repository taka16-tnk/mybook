package com.example.mybook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mybook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        recyclerView = findViewById(R.id.recyclerView);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
}