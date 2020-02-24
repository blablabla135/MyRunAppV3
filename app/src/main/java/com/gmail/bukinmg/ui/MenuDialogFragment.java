package com.gmail.bukinmg.ui;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.DialogFragmentBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.viewmodel.MainMenuViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;


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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment, container, false);
        mainMenuViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(MainMenuViewModel.class);
        dialogFragmentBinding.setMainMenuViewModel(mainMenuViewModel);
        dialogFragmentBinding.setLifecycleOwner(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new MaterialAlertDialogBuilder(getActivity())
                .setView(R.layout.dialog_fragment)
                .setNegativeButton("CANCEL", null)
                .setPositiveButton("ADD", null)
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();

        AlertDialog dialog = (AlertDialog) getDialog();

        if(dialog != null)
        {
            Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> {
                if (mainMenuViewModel.distance.getValue().equals("")) {
                    mainMenuViewModel.showError();
                } else {
                    mainMenuViewModel.addEvent();
                    dialog.dismiss();
                }
            });

        }
    }

}
