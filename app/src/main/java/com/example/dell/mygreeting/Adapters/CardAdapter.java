package com.example.dell.mygreeting.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dell.mygreeting.EditActivity;
import com.example.dell.mygreeting.R;
import com.example.dell.mygreeting.Models.FlickerModel;
import com.squareup.picasso.Picasso;

import java.util.List;

//import com.example.dell.greetingsapplication.Mangers.ImageManager;

/**
 * Created by DELL on 1/12/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<FlickerModel> images;
     Context context;



    public CardAdapter(List<FlickerModel> images, Context context) {
        this.images = images;
        this.context = context;

    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.flicker_image , parent , false);
        return new CardAdapter.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final CardAdapter.ViewHolder holder,final int position) {
        ((CardView)holder.flickerImageContainer.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               context.startActivity(new Intent(context , EditActivity.class)
                       .putExtra("url" , images.get(position).getUrl()));
            }
       });

        //ImageManager.getInstance().displayImage(activity , holder.flickerImageView , images.get(position).getUrl()  , ImageManager.FROM_INTERNET);
       // holder.flickerImageView.setImageResource(R.drawable.greeting);

       String url= images.get(position).getUrl();
        if(url!=null){
        Picasso
                .with(context)
                .load(images.get(position).getUrl())

                .into(holder.flickerImageView);}
        else {
            holder.flickerImageView.setImageResource(images.get(position).getSource());
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView flickerImageView;
       RelativeLayout flickerImageContainer;

//
        public ViewHolder(View itemView) {
            super(itemView);
            flickerImageView = (ImageView) itemView.findViewById(R.id.flickerImageView);

            flickerImageContainer = (RelativeLayout) itemView.findViewById(R.id.ImageContainer);

           // favBtn = (ImageButton) itemView.findViewById(R.id.favIcon);
        }
    }
}
