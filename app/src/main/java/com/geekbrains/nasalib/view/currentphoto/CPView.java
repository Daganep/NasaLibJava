package com.geekbrains.nasalib.view.currentphoto;


import com.geekbrains.nasalib.model.entity.CEInfo;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface CPView extends MvpView {
    /*@StateStrategyType(value = AddToEndSingleStrategy.class)
    void setData(CEInfo ceInfo);*/
}
