package com.geekbrains.nasalib.view.main;

import com.geekbrains.nasalib.model.entity.NasaResponse;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateRecyclerView(NasaResponse nasaResponse);
}
