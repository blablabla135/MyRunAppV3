package com.gmail.bukinmg.viewmodel;

import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.Repository;

import java.util.List;

import javax.inject.Inject;

public class MainMenuViewModel extends ViewModel {

    private Repository repository;
    private List<String> dates;

    @Inject
    public MainMenuViewModel(Repository repository) {
        this.repository = repository;
        dates = repository.getDates();
    }

    public List<String> getDates() {
        return dates;
    }
}
