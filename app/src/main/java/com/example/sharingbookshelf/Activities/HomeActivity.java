package com.example.sharingbookshelf.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.sharingbookshelf.R;

public class HomeActivity extends AppCompatActivity {

    private Button btn_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_book = findViewById(R.id.btn_book);

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.menu_register_book,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.scanBarcode){ // 메뉴 홈페이지 만들고 intent로 수정
                            Toast.makeText(HomeActivity.this, "카메라로 바코드 스캔", Toast.LENGTH_SHORT).show();
                        }else if (menuItem.getItemId() == R.id.searchBookName){
                            Toast.makeText(HomeActivity.this, "ISBN 혹은 책 이름으로 검색", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(HomeActivity.this, "수동으로 정보 입력", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

}