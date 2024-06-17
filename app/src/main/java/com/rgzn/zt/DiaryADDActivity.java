package com.rgzn.zt;



import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Calendar;
public class DiaryADDActivity extends AppCompatActivity {
    private EditText theme;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private EditText content;
    private Button selectImageButton;
    private Button btnadd;
    private ImageView selectedImageView;
    private Bitmap selectedImage;
    private SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_addactivity);

        theme = (EditText) findViewById(R.id.diary_theme_et);
        content = (EditText) findViewById(R.id.diary_content_et);
        selectImageButton = (Button) findViewById(R.id.diary_select_image_button);
        btnadd = (Button) findViewById(R.id.diary_publish_button_true);
        selectedImageView = (ImageView) findViewById(R.id.diary_selected_image_view);

        findViewById(R.id.back_buttondiary_output).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent it = new Intent(DiaryADDActivity.this, MainDiary.class);
//                startActivity(it);
            }
        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stheme = theme.getText().toString();
                String scontent = content.getText().toString();
                Calendar rl = Calendar.getInstance();

                String year = String.valueOf(rl.get(Calendar.YEAR));
                String month = String.valueOf(rl.get(Calendar.MONTH));

                String day = String.valueOf(rl.get(Calendar.DATE));
                String hour = String.valueOf(rl.get(Calendar.HOUR));
                String minute = String.valueOf(rl.get(Calendar.MINUTE));
                String date = year +"年" + month + "月" +day + "日-" + hour + ":" + minute;


                // 将Bitmap转换为字节数组
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] imageBytes = outputStream.toByteArray();

                db = openOrCreateDatabase("/data/data/com.rgzn.zt/diary.db", MODE_PRIVATE, null);

                ContentValues values = new ContentValues();
                values.put("theme", stheme);
                values.put("content", scontent);
                values.put("add_time",date);
                values.put("photo", imageBytes);

                // 插入数据
                db.insert("diary_table", null, values);
                Toast.makeText(getApplicationContext(), "添加成功！", Toast.LENGTH_SHORT).show();

                finish();
//                Intent it = new Intent(DiaryADDActivity.this,MainDiary.class);
//                startActivity(it);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(selectedImageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                selectedImageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
