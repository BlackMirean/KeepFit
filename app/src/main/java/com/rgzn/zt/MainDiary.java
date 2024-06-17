package com.rgzn.zt;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rgzn.zt.R;
import com.rgzn.zt.adapter.DiaryAdapter;
import com.rgzn.zt.entity.DiaryItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainDiary extends AppCompatActivity {
    private List<DiaryItem> diaryList = new ArrayList<>();
    private DiaryAdapter adapter;

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_diary);
        Button btn_goadd = (Button)findViewById(R.id.btn_adddiary);

        btn_goadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainDiary.this, DiaryADDActivity.class);
                startActivity(it);
            }
        });

        findViewById(R.id.back_button_maindiary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 获取 RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recy_diary);

        // 创建 LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 从数据库获取所有的 DiaryItem
        db = openOrCreateDatabase("/data/data/com.rgzn.zt/diary.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS diary_table(theme TEXT, content TEXT, add_time TEXT, photo BLOB);");
        //还没有插入数据

        Cursor cursor = db.rawQuery("SELECT * FROM diary_table", null);
        if (cursor.moveToFirst()) {
            do {
                String theme = cursor.getString(0);
                String content = cursor.getString(1);
                String add_time = cursor.getString(2);
                byte[] imageBytes = cursor.getBlob(3);
                // 将字节流转换为Bitmap
                Bitmap image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                DiaryItem item = new DiaryItem(theme, content,add_time,image);
                diaryList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        //初始化数据库

        // 创建适配器
        Collections.reverse(diaryList);
        adapter = new DiaryAdapter(diaryList);

        // 设置适配器
        recyclerView.setAdapter(adapter);
    }
}
