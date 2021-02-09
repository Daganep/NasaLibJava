package com.geekbrains.nasalib.view.main;

import com.geekbrains.nasalib.model.entity.Element;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateRecyclerView(List<Element> elements);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void checkDB(List<Element> elements);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showError(int msg);
}
