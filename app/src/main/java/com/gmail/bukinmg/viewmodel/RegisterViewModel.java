package com.gmail.bukinmg.viewmodel;

import android.graphics.Color;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.DBRepository;
import com.gmail.bukinmg.model.RetrofitRepository;
import com.gmail.bukinmg.model.entity.MainEvent;
import com.gmail.bukinmg.model.entity.User;
import com.gmail.bukinmg.utility.EventWrapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    public MutableLiveData<String> raceText = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorConfirmPassword = new MutableLiveData<>();
    public MutableLiveData<String> eMail = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> loginTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> dialogTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> dialogDismissTrigger = new MutableLiveData<>();

    private List<MainEvent> mainEventList = new ArrayList<>();
    private MainEvent mainEvent;

    private DBRepository dBRepository;



    @Inject
    public RegisterViewModel(DBRepository dBRepository, RetrofitRepository retrofitRepository) {
        this.dBRepository = dBRepository;
        raceText.setValue("list of races");
        eMail.setValue("");
        password.setValue("");
        confirmPassword.setValue("");
        mainEventList = retrofitRepository.getAllMainEvents();
    }

    public void onRegisterClicked() {

        String userEmail = eMail.getValue();
        String userPassword = password.getValue();
        String userConfirmPassword = confirmPassword.getValue();

        List<User> usersList = dBRepository.getAllUsers();

        if (mainEvent == null) {
            raceText.setValue("choose race");
        } else {
            raceText.setValue(mainEvent.getName());
        }

        if (userEmail.equals("")) {
            errorEmail.setValue("Enter eMail");
        } else if (!isEmailValid(userEmail, usersList)) {
            errorEmail.setValue("Enter valid eMail");
        } else {
            errorEmail.setValue(null);
        }

        if (userPassword.equals("")) {
            errorPassword.setValue("Enter password");
        } else if (!isPasswordValid(userPassword, userConfirmPassword)) {
            errorPassword.setValue("Enter valid password");
        } else {
            errorPassword.setValue(null);
        }

        if (confirmPassword.equals("")) {
            errorConfirmPassword.setValue("Enter password");
        } else if (!isPasswordValid(userPassword, userConfirmPassword)) {
            errorConfirmPassword.setValue("Enter valid password");
        } else {
            errorConfirmPassword.setValue(null);
        }

        if (isPasswordValid(userPassword, userConfirmPassword) && isEmailValid(userEmail, usersList) && mainEvent != null) {

            dBRepository.insert(new User(userEmail, userPassword, mainEvent.getName(), mainEvent.getDate()));

            loginTrigger.setValue(new EventWrapper<>(true));
        }
    }

    public void onMainEventClick() {
        dialogTrigger.setValue(new EventWrapper<>(true));
    }

    private boolean isEmailValid(String eMail, List<User> users) {

        if (eMail.equals("")) return false;

        for (User user : users
        ) {
            if (user.getEMail().equals(eMail)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        if (password.equals("")) return false;
        return password.equals(confirmPassword);
    }

    public List<MainEvent> getAllMainEvents() {
        return mainEventList;
    }


    public void setMainEvent(MainEvent mainEvent) {
        this.mainEvent = mainEvent;
    }

    public void onMainEventSelected() {
        dialogDismissTrigger.setValue(new EventWrapper<>(true));
    }

    public void setRaceText(String raceText) {
        this.raceText.setValue(raceText);
    }

    public MainEvent getMainEvent() {
        return mainEvent;
    }



    @BindingAdapter("errorText")
    public static void setErrorText(TextInputLayout textInputLayout, String errorText) {
        if (errorText != null) {
            textInputLayout.setError(errorText);
        } else {
            textInputLayout.setError(null);
        }
    }

    @BindingAdapter({"app:color"})
    public static void setBackgroundButton(MaterialButton button, String color) {
        if (color.equals("choose race")) {
            button.setBackgroundColor(Color.parseColor("#b00020"));
        } else {
            button.setBackgroundColor(Color.parseColor("#3f51b5"));
        }
    }

}
