package com.example.dell.mygreeting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dell.mygreeting.Models.FlickerModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by DELL on 1/27/2017.
 */

public class PreviewActivity extends AppCompatActivity {
    byte[] readData = new byte[1024*500];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent sentIntent =getIntent();
        Bundle extras =sentIntent.getExtras();
        byte[] byteArray1 = extras.getByteArray("picture1");
        byte[] byteArray2 = extras.getByteArray("picture2");

        Bitmap bmp1 = BitmapFactory.decodeByteArray(byteArray1, 0, byteArray1.length);

        Bitmap bmp2 = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);

       // loadType();

     //   Bitmap image =  BitmapFactory.decodeByteArray(readData, 0, readData.length);
        ImageView animView= (ImageView) findViewById(R.id.animImageView);
        ImageView animView1= (ImageView) findViewById(R.id.animImageView1);
        ImageButton button=(ImageButton) findViewById(R.id.imageButton);

      ;

        animView.setImageBitmap(bmp1);
        animView1.setImageBitmap(bmp2);

button.setOnClickListener(new View.OnClickListener() {
    public void onClick(View v) {


        shareGif();




    }
});
    }


    private void shareGif(){

        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "sharingGif.gif";

        File sharingGifFile = new File(baseDir, fileName);

        try {



            FileOutputStream fos = new FileOutputStream(sharingGifFile);

                fos.write(readData);
            fos.close();
        } catch (IOException io) {
        }
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("image/gif");
        Uri uri = Uri.fromFile(sharingGifFile);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share Emoji"));
    }


    public void loadType() {

        FileInputStream fis;
        try {


            fis = ApplicationClass.getGreetingApp().getApplicationContext()
                    .openFileInput("mariam");
            //int i = fis.read(readData);
            ObjectInputStream oi = new ObjectInputStream(fis);
            readData = (byte[])oi.readObject();
            oi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }















}
