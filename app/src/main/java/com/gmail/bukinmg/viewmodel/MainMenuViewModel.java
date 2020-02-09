package com.gmail.bukinmg.viewmodel;

import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.Repository;


import javax.inject.Inject;

public class MainMenuViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public MainMenuViewModel(Repository repository) {
        this.repository = repository;
    }

}
