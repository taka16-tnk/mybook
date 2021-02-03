package com.example.mybook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mybook.MyApplication;
import com.example.mybook.R;
import com.example.mybook.data.BooksRepository;
import com.example.mybook.data.DatabaseHelper;

import static com.example.mybook.services.CommonHelper.showToast;

public class AddActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button add_btn_submit;

    Handler handler;

    private BooksRepository booksRepository = MyApplication.getInstance().getBooksRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        handler = new Handler(this.getMainLooper());

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_btn_submit = findViewById(R.id.add_btn_submit);
        add_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booksRepository.addData(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        Integer.valueOf(pages_input.getText().toString().trim()));

                showToast(handler, getApplicationContext(), R.string.msg_add);
            }
        });
    }


    @Override
    public void onBackPressed() { }
}