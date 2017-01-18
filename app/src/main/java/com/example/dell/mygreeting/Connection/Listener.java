package com.example.dell.mygreeting.Connection;

import com.example.dell.greetingsapplication.models.FlickerModel;

import java.util.List;

/**
 * Created by DELL on 1/17/2017.
 */

public interface Listener {

    public void onDownloadFinished(List<FlickerModel> images);

    public void onFail(Exception e);



    public void onGetReviews(List<String> result);

}
