package com.geekbrains.nasalib.utils.picasso;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class ImageSetter {

    public void setImage(String url, ImageView imageView){
        Picasso
                .get()
                .load(url)
                .into(imageView);
    }
}
