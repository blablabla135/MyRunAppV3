package com.gmail.bukinmg.ui;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.DialogFragmentBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.viewmodel.MainMenuViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MenuDialogFragment extends DialogFragment {

    @Inject
    ViewModelFactory viewModelFactory;
    private DialogFragmentBinding dialogFragmentBinding;
    private MainMenuViewModel mainMenuViewModel;


    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        dialogFragmentBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_fragment, null, false);

        mainMenuViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MainMenuViewModel.class);
        dialogFragmentBinding.setMainMenuViewModel(mainMenuViewModel);
        dialogFragmentBinding.setLifecycleOwner(this);

        return new MaterialAlertDialogBuilder(getActivity())
                .setView(dialogFragmentBinding.getRoot()).create();
    }
}
