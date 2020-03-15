package com.gmail.bukinmg.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.DialogFragmentMainEventBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.viewmodel.RegisterViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MainEventDialogFragment extends DialogFragment {

    @Inject
    ViewModelFactory viewModelFactory;
    private DialogFragmentMainEventBinding dialogFragmentBinding;
    private RegisterViewModel registerViewModel;


    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        dialogFragmentBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_fragment_main_event, null, false);

        registerViewModel = new ViewModelProvider(requireActivity(), viewModelFactory).get(RegisterViewModel.class);
        dialogFragmentBinding.setRegisterViewModel(registerViewModel);
        dialogFragmentBinding.setLifecycleOwner(this);

        return new MaterialAlertDialogBuilder(getActivity())
                .setView(dialogFragmentBinding.getRoot()).create();
    }

}
