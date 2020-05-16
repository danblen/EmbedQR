package com.example.app02;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app02.utils.ImageUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.net.URI;

import static com.example.app02.MainActivity.REQUEST_EMBED;

public class CreateEmbedActivity extends AppCompatActivity {

    int request;
    Button button;
    ImageView imageOrigin;
    ImageView imageThresh;
    ImageView imageEmbed;
    TextView editText;
    Bitmap qrBitmap;
    Bitmap threshBitmap;
    Bitmap embedQrBitmap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState );
        setContentView(R.layout.activity_createembed);
        imageOrigin = findViewById(R.id.image_origin);
        imageThresh = findViewById(R.id.image_thresh);
        imageEmbed = findViewById(R.id.image_embed);
        editText = findViewById(R.id.edit_content);
        Button button = findViewById(R.id.button_create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textContent =  editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(CreateEmbedActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText.setText("");
                qrBitmap = CodeUtils.createImage(textContent, 800, 800, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                createEmbed();
                imageThresh.setImageBitmap(qrBitmap);
                imageEmbed.setImageBitmap(embedQrBitmap);
            }
        });

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_EMBED);


    }

    public static Bitmap createEmbed(){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                product();
                return null;
            }
        }.execute();
        return mBitmap;
    }

    public static Bitmap product(){

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Uri uri = data.getData();
        imageOrigin.setImageURI(uri);
        threshBitmap = getBitmapFromUri(uri);


    }
    public Bitmap getBitmapFromUri(Uri imageUri) {
        getContentResolver().notifyChange(imageUri, null);
        ContentResolver cr = getContentResolver();
        Bitmap bitmap;
        try {
            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, imageUri);
//            float scale = Math.min((float) 1.0 * MAX_INPUT_BITMAP_WIDTH / bitmap.getWidth(), (float) 1.0 * MAX_INPUT_BITMAP_HEIGHT / bitmap.getHeight());
//            if (scale < 1) {
//                bitmap = CuteR.getResizedBitmap(bitmap, scale, scale);
//            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
