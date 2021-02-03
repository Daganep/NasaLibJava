package com.geekbrains.nasalib.view.currentphoto;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.databinding.ActivityCPBinding;
import com.geekbrains.nasalib.model.entity.CEInfo;
import com.geekbrains.nasalib.utils.picasso.ImageSetter;
import com.geekbrains.nasalib.presenter.CPPresenter;
import java.util.Objects;
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
        initToolbar();
        imageSetter = new ImageSetter();
        ceInfo = (CEInfo) getIntent().getSerializableExtra(CEInfo.class.getSimpleName());
        setData(ceInfo);
    }

    public void setData(CEInfo ceInfo) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(ceInfo.getTitle());
        String creator = "unknown";
        if(ceInfo.getData() == null) creator = ceInfo.getData();
        imageSetter.setImage(ceInfo.getURL(), cpActivity.currentPhotoIV);
        cpActivity.CPTitleTV.setText(String.format("%s%s", getString(R.string.CP_title), ceInfo.getTitle()));
        cpActivity.CPCreatorTV.setText(String.format("%s%s", getString(R.string.sec_creator), creator));
        cpActivity.CPDateTV.setText(String.format("%s%s", getString(R.string.date_creation), ceInfo.getData()));
    }

    private void initToolbar() {
        setSupportActionBar(cpActivity.cpToolbar);
        if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override public boolean onSupportNavigateUp () {
        onBackPressed() ;
        return true;
    }
}