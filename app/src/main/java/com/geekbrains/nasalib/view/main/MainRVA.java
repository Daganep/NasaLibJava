package com.geekbrains.nasalib.view.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.model.entity.Link;
import com.geekbrains.nasalib.model.picasso.ImageSetter;
import com.geekbrains.nasalib.presenter.main.MainPresenter;

import java.util.List;

import moxy.presenter.InjectPresenter;

public class MainRVA extends RecyclerView.Adapter<MainRVA.ImageViewHolder> {

    private List<Link> photos;
    private MainPresenter presenter;
    private ImageSetter imageSetter;

    public MainRVA(List<Link> photos, MainPresenter presenter){
        this.photos = photos;
        this.presenter = presenter;
        imageSetter = new ImageSetter();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.main_vh;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        int position;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.previewIV);
        }

        void bind(int position){
            this.position = position;
            imageSetter.setImage("Here will be URL", imageView);
        }
    }
}
