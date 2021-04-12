package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.sharingbookshelf.BookApiRetrofitClient;
import com.example.sharingbookshelf.HttpRequest.RetrofitServiceApi;
import com.example.sharingbookshelf.Models.BookApiResponse;
import com.example.sharingbookshelf.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.zxing.client.result.ParsedResultType.ISBN;

public class SelfAddBookPopupActivity extends Activity {

    private EditText ISBN_field;
    private RetrofitServiceApi retrofitServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_self_add_book_popup);

        //UI 객체생성
        ISBN_field = (EditText)findViewById(R.id.ISBN_field);

    }

    /*public void mOnClose(View view) {
        //데이터 전달하기
        String ISBN = ISBN_field.getText().toString();
        Intent intent = new Intent(getApplicationContext(), TakingPhotoActivity.class);
        intent.putExtra("ISBN", ISBN);
        setResult(RESULT_OK, intent);
        finish();

    }*/

    //확인버튼 눌렀을때
    public void mOnClose(View view) {
        String ISBN = ISBN_field.getText().toString();
        callBookResponse(ISBN);
    }

    private void callBookResponse(String ISBN) {
        retrofitServiceApi = BookApiRetrofitClient.createService(RetrofitServiceApi.class);
        Call<BookApiResponse> call = retrofitServiceApi.setBookApiResponse(ISBN, "isbn");
        call.enqueue(new Callback<BookApiResponse>() {
            @Override
            public void onResponse(Call<BookApiResponse> call, Response<BookApiResponse> response) {
                BookApiResponse result = response.body();
                Log.d(MainActivity.MAIN_TAG, "책 api 통신 성공");
                if (result != null) {
                    getBookDetails(result);
                }
            }
            @Override
            public void onFailure(Call<BookApiResponse> call, Throwable t) {
                Log.e(MainActivity.MAIN_TAG, "책 api 통신 실패", t);
            }
        });
    }

    private void getBookDetails(BookApiResponse result) {
        ArrayList<BookApiResponse.Document> documentList = result.documents;
        BookApiResponse.Meta meta = result.metas;
        //BookApiResponse.Document book = (BookApiResponse.Document) books.documents.get(0);
        Intent intent = new Intent(SelfAddBookPopupActivity.this, BookDetailsPopupActivity.class);
        intent.putExtra("documentList", documentList);
        intent.putExtra("meta", meta);
        intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
        finish();
    }
}