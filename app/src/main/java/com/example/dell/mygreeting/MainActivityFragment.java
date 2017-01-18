package com.example.dell.mygreeting;

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
import android.widget.Spinner;

import com.example.dell.greetingsapplication.Adapters.CardAdapter;
import com.example.dell.greetingsapplication.Connection.Listener;
import com.example.dell.greetingsapplication.Mangers.ImagesMnager;
import com.example.dell.greetingsapplication.models.FlickerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 1/10/2017.
 */

public class MainActivityFragment extends Fragment implements Listener {
     RecyclerView daysList;
    public static final int LIST_MODE = 0x0a;
    ArrayList<FlickerModel> images = new ArrayList<>();
    FlickerModel model= new FlickerModel();
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

        daysList.setLayoutManager(new LinearLayoutManager(getActivity()));

        callWebService();
        model.setUrl("https://farm1.staticflickr.com//624//32095916031_8176c71beb_b.jpg");
         images.add(model);

//        CardAdapter adapter = new CardAdapter (images ,  getActivity() , LIST_MODE);
//        daysList.setAdapter(adapter);


        Spinner dropdown = (Spinner)rootView.findViewById(R.id.spinner1);

        String[] items = new String[]{"Choose Celebration","BirthDay", "Eid", "Graduation"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),R.layout.spineer_item, items);
        dropdown.setAdapter(adapter1);
        return rootView;
    }
    public void callWebService(){

        ImagesMnager.getInstance().getPopular(this);
//        FetchPhotos task= new FetchPhotos(new MainActivityFragment());
//        task.execute("mariambio2012Eid");


    }


    public void onOrientationChange(int orientation , final List<FlickerModel> images){

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            CardAdapter adapter = new CardAdapter (images ,  getActivity() , LIST_MODE);
            daysList.setAdapter(adapter);}}
//
    @Override
    public void onDownloadFinished(List<FlickerModel> images) {
        onOrientationChange(getResources().getConfiguration().orientation , images);
    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onGetReviews(List<String> result) {

    }
}
