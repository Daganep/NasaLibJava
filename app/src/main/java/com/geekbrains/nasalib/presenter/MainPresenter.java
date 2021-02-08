package com.geekbrains.nasalib.presenter;

import android.util.Log;

import com.geekbrains.nasalib.di.App;
import com.geekbrains.nasalib.model.database.AppDatabase;
import com.geekbrains.nasalib.model.database.ElementDao;
import com.geekbrains.nasalib.model.entity.Element;
import com.geekbrains.nasalib.model.entity.Item;
import com.geekbrains.nasalib.model.entity.NasaResponse;
import com.geekbrains.nasalib.model.retrofit.RetrofitApi;
import com.geekbrains.nasalib.view.main.MainView;

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
    private List<Element> elements;
    private final CompositeDisposable subscriptions;

    public MainPresenter(){
        App.getAppComponent().inject(this);
        elementDao = appDatabase.elementDao();
        subscriptions = new CompositeDisposable();
    }

    public void requestFromServer(String query){
        Observable<NasaResponse> single = retrofitApi.requestServer(query);
        subscriptions.add(single.observeOn(AndroidSchedulers.mainThread()).subscribe(emitter -> {
            elements = itemsToElement(emitter);
            getViewState().updateRecyclerView(elements);
        }, throwable -> Log.e("Error", "onError" + throwable)));
    }

    private List<Element> itemsToElement(NasaResponse response){
        List<Element> info = new ArrayList<>();
        if(response.getCollection().getItems() != null){
            for(Item x : response.getCollection().getItems()){
                info.add(new Element(x));
            }
        }
        return info;
    }

    public void requestFromDB(){
        subscriptions.add(elementDao.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( elements1 -> {
                            elements = elements1;
                            getViewState().checkDB(elements1);
                        },
                        throwable -> Log.e("Error", "onError" + throwable)));
    }

    public void putToDB(){
        if(elements.size() > 0){
            subscriptions.add(elementDao.insertList(elements)
                    .subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(throwable -> Log.e("Error", "onError" + throwable)));
        }

    }

    public void saveLastResult(){
        subscriptions.add(elementDao.deleteAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleted -> putToDB(),
                        throwable -> {Log.e("Error", "onError" + throwable);}));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }
}
