package com.gmail.bukinmg.ui;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.DialogFragmentBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.utility.EventWrapper;
import com.gmail.bukinmg.viewmodel.MainMenuViewModel;



import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MenuDialogFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;
    private DialogFragmentBinding dialogFragmentBinding;
    private MainMenuViewModel mainMenuViewModel;


    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment, container, false);
        mainMenuViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MainMenuViewModel.class);
        dialogFragmentBinding.setMainMenuViewModel(mainMenuViewModel);
        dialogFragmentBinding.setLifecycleOwner(this);
        return dialogFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainMenuViewModel.addTrigger.observe(getViewLifecycleOwner(), booleanEventWrapper -> {
            if (mainMenuViewModel.addTrigger != null) {
                getActivity().onBackPressed();
            }
        });

        mainMenuViewModel.cancelTrigger.observe(getViewLifecycleOwner(), booleanEventWrapper -> {
            if (mainMenuViewModel.cancelTrigger != null) {
                getActivity().onBackPressed();
            }

        });

    }
}
