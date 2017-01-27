package com.example.dell.mygreeting.Mangers;

import android.os.Handler;
import android.widget.Toast;

import com.example.dell.mygreeting.ApplicationClass;
import com.example.dell.mygreeting.Connection.FetchPhotos;
import com.example.dell.mygreeting.Connection.Listener;
import com.example.dell.mygreeting.Utilits.Utils;
import com.example.dell.mygreeting.Models.FlickerModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 11/13/2016.
 */
public class ImagesMnager {
    public Handler handler;
    private static ImagesMnager ourInstance ;
    public static final int BiRTH = 0x0a;
    public static final int EID= 0x0b;
    public static final int GRAD= 0x0c;
    public static final int NEW= 0x0d;
    private String wish;
    private List<FlickerModel> birth = new ArrayList<>();
    private List<FlickerModel> eid = new ArrayList<>();
    private List<FlickerModel> grad = new ArrayList<>();
    private List<FlickerModel> newy = new ArrayList<>();
    private List<FlickerModel> type1 = new ArrayList<>();

    public static ImagesMnager getInstance() {
        if (ourInstance == null) {
            ourInstance =  new ImagesMnager();
            ourInstance.handler = new Handler();
        }
        return ourInstance;
    }

    private ImagesMnager() {
    }

    public void getPhotoList(final int type,Listener listener) {
        if (type==BiRTH){
            wish="mariambio2012Birth";

            type1=birth;}

        else if (type==EID){
            wish="mariambio2012Eid";
            type1=eid;}

        else if (type==GRAD){
            wish="mariambio2012Rand";
            type1=grad;}

        else if (type==NEW){
            wish="mariambio2012New";
            type1=newy;}

        if (!Utils.isNetworkConnected(
                ApplicationClass.getGreetingApp().getApplicationContext()) && ((type == BiRTH||type==EID)||(type == GRAD||type==NEW))) {
            loadType();
            listener.onDownloadFinished(type1);
        } else if (!Utils.isNetworkConnected(
                ApplicationClass.getGreetingApp().getApplicationContext())) {
            Toast.makeText(ApplicationClass.getGreetingApp().getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();}

 else {


            FetchPhotos task = new FetchPhotos(listener);
            task.execute(wish);
         }}

    /**
     * Created by DELL on 11/12/2016.
     */

    public void getBirth(Listener listener){

        if(!birth.isEmpty())
            listener.onDownloadFinished(birth);
        else
            getPhotoList(BiRTH , listener);
    }


    public void getEid(Listener listener){

        if(!eid.isEmpty())
            listener.onDownloadFinished(eid);
        else
            getPhotoList(EID , listener);
    }
    public void getGrad(Listener listener){

        if(!grad.isEmpty())
            listener.onDownloadFinished(grad);
        else
            getPhotoList(GRAD , listener);
    }
    public void getNew(Listener listener){

        if(!newy.isEmpty())
            listener.onDownloadFinished(newy);
        else
            getPhotoList(NEW , listener);
    }


    public void loadType() {

        FileInputStream fis;
        try {
            fis = ApplicationClass.getGreetingApp().getApplicationContext()
                    .openFileInput(wish);

            ObjectInputStream oi = new ObjectInputStream(fis);
           type1 = (List<FlickerModel>) oi.readObject();
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

