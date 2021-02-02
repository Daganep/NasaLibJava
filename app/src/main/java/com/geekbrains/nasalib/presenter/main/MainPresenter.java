package com.geekbrains.nasalib.presenter.main;

import android.util.Log;

import com.geekbrains.nasalib.di.App;
import com.geekbrains.nasalib.model.entity.Item;
import com.geekbrains.nasalib.model.entity.NasaResponse;
import com.geekbrains.nasalib.model.retrofit.RetrofitApi;
import com.geekbrains.nasalib.view.main.MainView;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    RetrofitApi retrofitApi;
    @Inject
    NasaResponse nasaResponse;

    private Disposable disposable;
    private final String TAG = "Retrofit";
    private List<Item> items;

    public MainPresenter(){
        App.getAppComponent().inject(this);
    }

    public void requestFromServer(){
        Observable<NasaResponse> single = retrofitApi.requestServer();

        disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(emitter -> {
            nasaResponse = emitter;
            getViewState().updateRecyclerView();
        }, throwable -> {
            Log.e("Error", "onError" + throwable);
        });
    }

    public List<Item> getMedia(){
        items = nasaResponse.getCollection().getItems();
        Log.d(TAG, "Total: " + nasaResponse.getCollection().getMetadata().getTotalHits());
        return items;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
