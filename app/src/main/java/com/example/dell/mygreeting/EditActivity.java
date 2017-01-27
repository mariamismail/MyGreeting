package com.example.dell.mygreeting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dell.mygreeting.GifEncoder.AnimatedGifEncoder;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import  com.example.dell.mygreeting.ScreenGif;

/**
 * Created by DELL on 1/25/2017.
 */

public class EditActivity extends AppCompatActivity {
    private static final String LOG_TAG=EditActivity.class.getSimpleName();
   static Bitmap frame1;
   static Bitmap frame2;
     static   View rootView1;
    static View view1;
    static   View rootView2;
     static ImageView imageView;

    static Button starter;
    static String albumName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent sentIntent =getIntent();
        Bundle sentBundle = sentIntent.getExtras();

        PhotoFragment fragment1= new PhotoFragment();
        fragment1.setArguments(sentBundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fle, fragment1).commit();


        EditFragment fragment2 = new EditFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fe, fragment2).commit();
    }


    /**
     * Created by DELL on 1/25/2017.
     */

    public static class EditFragment extends Fragment {
        public EditFragment() {
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           rootView1= inflater.inflate(R.layout.fragmnt_edit, container, false);
            EditText editText = (EditText)rootView1.findViewById(R.id.text);
            editText.setCursorVisible(true);
            EditText editText2 = (EditText)rootView1.findViewById(R.id.signature);
            editText2.setCursorVisible(true);
               view1 = rootView1.findViewById(R.id.linear);
//
//             imageView=(ImageView)rootView1.findViewById(R.id.galleryImageView);
//
//            imageView.setImageResource(R.drawable.placeholder);


             starter= (Button)rootView1.findViewById(R.id.go);



            return rootView1;
    }

        @Override
        public void onResume() {
            super.onResume();
            starter.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    frame1=ScreenGif.takeScreenshot(view1);
                    frame2=ScreenGif.takeScreenshot(rootView2);



                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    frame1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray1 = stream.toByteArray();


                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                    frame2.compress(Bitmap.CompressFormat.PNG, 100, stream1);
                    byte[] byteArray2 = stream1.toByteArray();


//                    saveType();

                    Intent onclickIntent = new Intent(getActivity(), PreviewActivity.class);
                    onclickIntent.putExtra("picture1", byteArray1);
                    onclickIntent.putExtra("picture2", byteArray2);
                    startActivity(onclickIntent);


//                    String name=getAlbumStorageDir(ApplicationClass.getGreetingApp().getApplicationContext(),albumName).getName();
//                    FileOutputStream outStream = null;
//                    try{
//
//                        FileOutputStream fos =getActivity()
//                                .openFileOutput(getAlbumStorageDir(getActivity(),albumName).getName(), MODE_PRIVATE);
//                        ObjectOutputStream of = new ObjectOutputStream(fos);
//                        of.writeObject(GenerateGif());
//                        of.flush();
//                        of.close();
//                        fos.close();
//
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
                }
            });
        }

    }

//        public static void saveType() {
//            try {
//                FileOutputStream fos = ApplicationClass.getGreetingApp().getApplicationContext()
//                        .openFileOutput("mariam", MODE_PRIVATE);
//                ObjectOutputStream of = new ObjectOutputStream(fos);
//                of.writeObject(GenerateGif());
//                of.flush();
//                of.close();
//                fos.close();
//            }
//            catch (Exception e) {
//                e.printStackTrace();
//            }
//
//    }


    public static byte[] GenerateGif(){

    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    AnimatedGifEncoder encoder = new AnimatedGifEncoder();
    encoder.start(bos);

                    encoder.setDelay(1000);
                    encoder.addFrame(frame1);

                    encoder.addFrame(frame2);
    encoder.finish();

    return bos.toByteArray();

}
    public static class PhotoFragment extends Fragment {


        public PhotoFragment() {
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            rootView2= inflater.inflate(R.layout.fragment_image_edit, container, false);

            String url = getArguments().getString("url");
            ImageView image=(ImageView)rootView2.findViewById(R.id.ImageView);
            Picasso
                    .with(getActivity())
                    .load(url)

                    .into(image);

            return rootView2;
        }

    }}



