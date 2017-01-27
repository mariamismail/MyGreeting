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

import com.example.dell.mygreeting.Adapters.CardAdapter;
import com.example.dell.mygreeting.Connection.Listener;
import com.example.dell.mygreeting.Mangers.ImagesMnager;
import com.example.dell.mygreeting.Models.FlickerModel;

import java.util.List;

/**
 * Created by DELL on 1/25/2017.
 */

public class GalleryActivityFragment extends Fragment  implements Listener {
    RecyclerView gallery;
    public int type;
    public   Bundle bundle = new Bundle();
    public GalleryActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        gallery = (RecyclerView) rootView.findViewById(R.id.gallery);
        gallery.setHasFixedSize(true);

        gallery.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        callWebService();
        return rootView;
    }

    public void callWebService(){


     type= getArguments().getInt("type");

        if(type == ImagesMnager.EID)
            ImagesMnager.getInstance().getEid(this);

        else if(type == ImagesMnager.BiRTH)
            ImagesMnager.getInstance().getBirth(this);

        else if(type == ImagesMnager.GRAD)
            ImagesMnager.getInstance().getGrad(this);

        else if(type == ImagesMnager.NEW)
            ImagesMnager.getInstance().getNew(this);
    }

    public void onOrientationChange(int orientation , final List<FlickerModel> images){

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            CardAdapter adapter = new CardAdapter (images ,  getActivity());
            gallery.setAdapter(adapter);}}




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