package com.example.sharingbookshelf.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsPopupActivity extends Activity {

    private ImageView iv_thumbNail;
    private TextView tv_ISBN, tv_title, tv_authors, tv_publisher;
    private Button btn_addBook;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바 없애기
        setContentView(R.layout.activity_book_details_popup);

        Log.d(MainActivity.MAIN_TAG, "데이터 전달 성공");
        initializeView();
        BookApiResponse.Document book = getBook();
        setView(book);

        btn_addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //책 추가하기 눌렀을 때
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetailsPopupActivity.this, SelfAddBookPopupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeView() {
        iv_thumbNail = findViewById(R.id.iv_thumbnail);
        tv_ISBN = findViewById(R.id.tv_ISBN);
        tv_title = findViewById(R.id.tv_title);
        tv_authors = findViewById(R.id.tv_authors);
        tv_publisher = findViewById(R.id.tv_publisher);
        btn_addBook = findViewById(R.id.btn_addBook);
        btn_back = findViewById(R.id.btn_back);
    }

    private BookApiResponse.Document getBook() {
        Intent intent = getIntent();
        ArrayList<BookApiResponse.Document> documentList = (ArrayList<BookApiResponse.Document>) intent.getSerializableExtra("documentList");
        //BookApiResponse.Meta meta = (BookApiResponse.Meta) intent.getSerializableExtra("meta");
        return documentList.get(0);
    }

    private void setView(BookApiResponse.Document book) {
        String isbn = book.getIsbn();
        String title = book.getTitle();
        List<String> authors = book.getAuthors();
        String publisher = book.getPublisher();
        String thumbnail = book.getThumbnail();
        Glide.with(this).load(thumbnail).into(iv_thumbNail);

        tv_ISBN.setText(isbn);
        tv_title.setText(title);
        tv_authors.setText(authors.get(0));
        tv_publisher.setText(publisher);
    }

   /* public void mOnClose(View view) {
        //데이터 전달하기
        String res = ISBN_field.getText().toString();
        Intent intent = new Intent(getApplicationContext(), TakingPhotoActivity.class);
        intent.putExtra("ISBN", res);
        setResult(RESULT_OK, intent);
        finish();

    }*/
}