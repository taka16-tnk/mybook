package com.example.mybook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybook.activities.BookListActivity;
import com.example.mybook.activities.UpdateActivity;
import com.example.mybook.data.Book;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private static final String TAG = "CustomAdapter";

    private Context context;
    Activity activity;
    private ArrayList book_id, book_title, book_author, book_pages;
    protected List<Book> bookDataSet = new ArrayList<Book>(){};

    public void updateDataSet(List<Book> dataSet) {

        bookDataSet.clear();
        bookDataSet.addAll(dataSet);
        notifyDataSetChanged();

        // デバッグ出力。
        for (int i = 0; i < bookDataSet.size(); i++) {
            Book b = bookDataSet.get(i);
            Log.d(TAG, String.format("%s, %s, %d", b.getBookTitle(), b.getBookAuthor(), b.getBookPages() ));
        }
    }

    public CustomAdapter(Activity activity, Context context,
                         ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages)
    {
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_row, parent, false);
        // 表示するレイアウトを設定
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // データ表示
        Book book = bookDataSet.get(position);
        holder.book_id_txt.setText(String.valueOf(book.getId())); ;
        holder.book_title_txt.setText(book.getBookTitle());
        holder.book_author_txt.setText(book.getBookAuthor());
        holder.book_pages_txt.setText(String.valueOf(book.getBookPages()));


//        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
//        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
//        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
//        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("id", book.getId());
                intent.putExtra("title", book.getBookTitle());
                intent.putExtra("author", book.getBookAuthor());
                intent.putExtra("pages", book.getBookPages());
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookDataSet.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
