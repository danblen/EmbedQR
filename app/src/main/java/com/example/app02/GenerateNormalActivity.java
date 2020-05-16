package com.example.app02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CodeUtils;

public class GenerateNormalActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    ImageView imageView;
    Bitmap mBitmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generatenormal);

        initView();
    }

    private void initView() {

        editText = (EditText) findViewById(R.id.edit_content);
        button = (Button) findViewById(R.id.button_content);
        imageView = (ImageView) findViewById(R.id.image_content);

        /**
         * 生成二维码图片
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textContent = editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(GenerateNormalActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText.setText("");
                mBitmap = CodeUtils.createImage(textContent, 800, 800, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                imageView.setImageBitmap(mBitmap);
            }
        });
    }
}
