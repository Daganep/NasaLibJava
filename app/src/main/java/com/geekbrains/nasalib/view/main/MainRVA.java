package com.geekbrains.nasalib.view.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.model.entity.CEInfo;
import com.geekbrains.nasalib.model.entity.Item;
import com.geekbrains.nasalib.utils.picasso.ImageSetter;
import com.geekbrains.nasalib.view.currentphoto.CPActivity;

import java.util.List;

public class MainRVA extends RecyclerView.Adapter<MainRVA.ImageViewHolder> {

    private final ImageSetter imageSetter;
    private List<Item> items;

    public MainRVA(){
        imageSetter = new ImageSetter();
    }

    public void setMedia(List<Item> items){
        this.items = items;
        notifyDataSetChanged();
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
        if(items != null) holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        int position;
        CEInfo ceInfo;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.previewIV);
            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(imageView.getContext(), CPActivity.class);
                intent.putExtra(CEInfo.class.getSimpleName(), ceInfo);
                imageView.getContext().startActivity(intent);
            });
        }

        void bind(int position){
            this.position = position;
            ceInfo = new CEInfo(items.get(position));
            if(items.get(position).getLinks() != null)
            imageSetter.setImage(items.get(position).getLinks().get(0).getHref(), imageView);
        }
    }
}
