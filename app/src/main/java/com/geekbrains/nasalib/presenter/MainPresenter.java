package com.geekbrains.nasalib.presenter;

import android.util.Log;

import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.di.App;
import com.geekbrains.nasalib.model.database.AppDatabase;
import com.geekbrains.nasalib.model.database.ElementDao;
import com.geekbrains.nasalib.model.entity.Element;
import com.geekbrains.nasalib.model.entity.Item;
import com.geekbrains.nasalib.model.entity.NasaResponse;
import com.geekbrains.nasalib.model.retrofit.ErrorInterceptor;
import com.geekbrains.nasalib.model.retrofit.RetrofitApi;
import com.geekbrains.nasalib.view.main.MainView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    RetrofitApi retrofitApi;
    @Inject
    AppDatabase appDatabase;
    private final ElementDao elementDao;
    private final CompositeDisposable subscriptions;
    private final String TAG = "Error";
    private List<Element> elements;

    public MainPresenter(){
        App.getAppComponent().inject(this);
        elementDao = appDatabase.elementDao();
        subscriptions = new CompositeDisposable();
    }

    public void requestData(String query){
        requestFromDB(query);
    }

    public void requestFromServer(String query){
        Observable<NasaResponse> single = retrofitApi.requestServer(query);
        subscriptions.add(single.observeOn(AndroidSchedulers.mainThread()).subscribe(emitter -> {
            elements = itemsToElement(emitter);
            if (elements.size() < 1)getViewState().showError(R.string.empty_result);
            else getViewState().updateRecyclerView(elements);
            checkResponse();
        }, throwable -> {
            if (throwable instanceof IOException) {
                getViewState().showError(R.string.load_info_network_error);
            } else {
                getViewState().showError(R.string.load_info_server_error);
            }
            Log.e(TAG, "onError" + throwable);
        }));
    }

    private void checkResponse(){
        Log.d(TAG, "Server response code: " + ErrorInterceptor.code);
    }

    private List<Element> itemsToElement(NasaResponse response){
        List<Element> info = new ArrayList<>();
        if(response.getCollection().getItems() != null){
            for(Item x : response.getCollection().getItems()){
                if(x.getLinks() != null)
                info.add(new Element(x));
            }
        }
        return info;
    }

    private void requestFromDB(String query){
        subscriptions.add(elementDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( elements1 -> {
                            elements = elements1;
                            if(elements1.size() == 0) requestFromServer(query);
                            else getViewState().updateRecyclerView(elements);
                        },
                        throwable -> Log.e(TAG, "onError" + throwable)));
    }

    private void putToDB(){
        if(elements.size() > 0){
            subscriptions.add(elementDao.insertList(elements)
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(throwable -> Log.e(TAG, "onError" + throwable)));
        }
    }

    public void saveLastResult(){
        subscriptions.add(elementDao.deleteAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleted -> putToDB(),
                        throwable -> Log.e(TAG, "onError" + throwable)));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!subscriptions.isDisposed())subscriptions.dispose();
    }
}
