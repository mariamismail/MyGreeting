package com.example.dell.mygreeting;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.dell.mygreeting.Adapters.CardAdapter;
import com.example.dell.mygreeting.Connection.Listener;
import com.example.dell.mygreeting.Mangers.ImagesMnager;
import com.example.dell.mygreeting.Models.FlickerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 1/10/2017.
 */

public class MainActivityFragment extends Fragment {
    Spinner dropdown;
     RecyclerView daysList;
    int y;
    public static final int LIST_MODE = 0x0a;
    ArrayList<FlickerModel> images = new ArrayList<>();
    int []drwable ={R.drawable.ran2,R.drawable.ran1,R.drawable.ran3,R.drawable.ran4,R.drawable.ran5};


    public MainActivityFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_main, container, false);

        daysList = (RecyclerView) rootView.findViewById(R.id.daysList);
      daysList.setHasFixedSize(true);

        daysList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        for (int i:drwable){
            FlickerModel model= new FlickerModel();
         model.setSource(i);
        images.add(model);}



         dropdown = (Spinner)rootView.findViewById(R.id.spinner1);

        String[] items = new String[]{"New Year","BirthDay", "Eid", "Graduation"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),R.layout.spineer_item, items);
        dropdown.setAdapter(adapter1);

        Button starter= (Button)rootView.findViewById(R.id.button);
        starter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                openGallery();
            }
        });


        onOrientationChange(getResources().getConfiguration().orientation , images);
        return rootView;
    }
    public void openGallery(){
        String text=dropdown.getSelectedItem().toString();

        if(text=="New Year"){
            y=ImagesMnager.NEW;
        }
        else if(text=="BirthDay"){
            y=ImagesMnager.BiRTH;
        }
        else if(text=="Eid"){
            y=ImagesMnager.EID;
        }
        else if(text=="Graduation"){
            y=ImagesMnager.GRAD;
        }
        Intent onclickIntent = new Intent(getActivity(), GalleryActivity.class);


        onclickIntent .putExtra("type", y);
        startActivity(onclickIntent);

       // ImagesMnager.getInstance().getPopular(this);
//        FetchPhotos task= new FetchPhotos(new MainActivityFragment());
//        task.execute("mariambio2012Eid");


    }


    public void onOrientationChange(int orientation , final List<FlickerModel> images){

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            CardAdapter adapter = new CardAdapter (images ,  getActivity());
            daysList.setAdapter(adapter);}}


}
