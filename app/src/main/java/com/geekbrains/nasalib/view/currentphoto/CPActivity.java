package com.geekbrains.nasalib.view.currentphoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.databinding.ActivityCPBinding;
import com.geekbrains.nasalib.model.entity.CEInfo;
import com.geekbrains.nasalib.model.entity.Item;
import com.geekbrains.nasalib.model.picasso.ImageSetter;
import com.geekbrains.nasalib.presenter.CPPresenter;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class CPActivity extends MvpAppCompatActivity implements CPView {

    @InjectPresenter
    CPPresenter cpPresenter;
    ImageSetter imageSetter;
    private ActivityCPBinding cpActivity;
    private CEInfo ceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cpActivity = DataBindingUtil
                .setContentView(this, R.layout.activity_c_p);
        imageSetter = new ImageSetter();
        ceInfo = (CEInfo) getIntent().getSerializableExtra(CEInfo.class.getSimpleName());
        setData(ceInfo);
    }

    public void setData(CEInfo ceInfo) {
        imageSetter.setImage(ceInfo.getURL(), cpActivity.currentPhotoIV);
        cpActivity.CPTitleTV.setText(ceInfo.getTitle());
        cpActivity.CPCreatorTV.setText(ceInfo.getCreator());
        cpActivity.CPDateTV.setText(ceInfo.getData());
    }
}