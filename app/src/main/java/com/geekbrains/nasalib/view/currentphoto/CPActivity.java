package com.geekbrains.nasalib.view.currentphoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.databinding.ActivityCPBinding;
import com.geekbrains.nasalib.model.entity.Element;
import com.geekbrains.nasalib.utils.picasso.ImageSetter;
import java.util.Objects;

public class CPActivity extends AppCompatActivity {

    ImageSetter imageSetter;
    private ActivityCPBinding cpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cpActivity = DataBindingUtil
                .setContentView(this, R.layout.activity_c_p);
        initToolbar();
        imageSetter = new ImageSetter();
        Element element = (Element) getIntent().getSerializableExtra(Element.class.getSimpleName());
        setData(element);
    }

    public void setData(Element element) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(element.getTitle());
        String creator = getString(R.string.unknown);
        if(element.getCreator() != null) creator = element.getCreator();
        imageSetter.setImage(element.getURL(), cpActivity.currentPhotoIV);
        cpActivity.CPTitleTV.setText(String.format("%s%s", getString(R.string.CP_title), element.getTitle()));
        cpActivity.CPCreatorTV.setText(String.format("%s%s", getString(R.string.sec_creator), creator));
        cpActivity.CPDateTV.setText(String.format("%s%s", getString(R.string.date_creation), element.getData()));
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