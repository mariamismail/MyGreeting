package com.example.dell.mygreeting.Connection;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.dell.mygreeting.Models.FlickerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 1/17/2017.
 */

public class FetchPhotos extends AsyncTask<String, Void, List<FlickerModel>> {
    private final String LOG_TAG=FetchPhotos.class.getSimpleName();
    public   Listener listener ;
    public String tag;

    public FetchPhotos (Listener listener){
        this.listener=listener ;
    }{
    }
    private List<FlickerModel> newy = new ArrayList<>();
    private List<FlickerModel> birth = new ArrayList<>();
    private List<FlickerModel> grad = new ArrayList<>();
    private List<FlickerModel> eid = new ArrayList<>();
    private List<FlickerModel> type = new ArrayList<>();


    @Override
    protected List<FlickerModel>doInBackground(String... params) {

        tag=params[0];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String FlickerJsonStr = null;

        String languge = "en-US";
        int pages = 21;

        try{


            URL uri = new URL("https://api.flickr.com/services/rest/?" +
                    "method=flickr.photos.search" +
                    "&api_key=cca5c934cb35f3b62ad20ff75b5c3af0" +
                    "&format=json&nojsoncallback=1" +
                    "&extras=url_l" +
                    "&safe_search=for%20safe" +
                    "&per_page=20"+"" +
                    "&tags="+tag);

            urlConnection = (HttpURLConnection) uri.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {

                return null;
            }
            FlickerJsonStr = buffer.toString();
            Log.v(LOG_TAG, "movie json string" + FlickerJsonStr);
            try {
                return getDataFromJson(FlickerJsonStr, pages);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);

            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }


    private List<FlickerModel> getDataFromJson(String FlickerJsonStr, int pages) throws JSONException {

        String[] imageUrls = new String[pages];


        final String LIST = "photos";

        JSONObject PhotoJson = new JSONObject(FlickerJsonStr);
        JSONArray PhotosArray = new JSONArray(PhotoJson.getJSONObject(LIST).getString("photo"));


        int len = PhotosArray.length();
        for (int i = 0; i < PhotosArray.length(); i++) {

            FlickerModel model = new FlickerModel();
            JSONObject OnePhoto = PhotosArray.getJSONObject(i);


            String id1 = OnePhoto.getString("id");
            model.setID(id1);

            String secret = OnePhoto.getString("secret");
            model.setSecret(secret);

            String server = OnePhoto.getString("server");
            model.setServer(server);



            int farm = OnePhoto.getInt("farm");
            String farm1 = String.valueOf(farm);

          model.setFarm(farm);


            String Url="https://"+"farm"+farm1+".staticflickr.com//"+server+"//"+id1+"_"+secret+".jpg";




            model.setUrl(Url);


            if (tag=="mariambio2012Eid"){
                eid.add(model);

                type=eid;}

            else if (tag=="mariambio2012Birth"){
                birth.add(model);
                type=birth;}

            if (tag=="mariambio2012Ran"){
                grad.add(model);

                type=grad;}

            else if (tag=="mariambio2012New"){
                newy.add(model);
                type=newy;}


        }
        return type;

    }


    @Override
    protected void onPostExecute(final List<FlickerModel> result) {
        listener.onDownloadFinished(result);
    }
}
