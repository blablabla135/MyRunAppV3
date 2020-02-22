package com.gmail.bukinmg.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.entity.User;
import com.gmail.bukinmg.model.Repository;
import com.gmail.bukinmg.utility.EventWrapper;

import java.util.List;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> eMail = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> mainTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> registerTrigger = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public LoginViewModel(Repository repository) {
        this.repository = repository;
        eMail.postValue("");
        password.postValue("");
    }

    public void onLoginClicked() {

        List<User> usersList = repository.getAllUsers();

        String userEmail = eMail.getValue();
        String userPassword = password.getValue();

        if (userEmail.equals("")) {
            errorEmail.setValue("Enter eMail");
        } else if (!isEmailValid(userEmail, usersList)) {
            errorEmail.setValue("Enter valid eMail");
        } else {
            errorEmail.setValue(null);
        }

        if (userPassword.equals("")) {
            errorPassword.setValue("Enter password");
        } else if (!isPasswordValid(userEmail, userPassword, usersList)) {
            errorPassword.setValue("Enter valid password");
        } else {
            errorPassword.setValue(null);
        }

        if (isPasswordValid(userEmail, userPassword, usersList) && isEmailValid(userEmail, usersList)) {
            mainTrigger.setValue(new EventWrapper<>(true));
        }
    }

    private boolean isEmailValid(String eMail, List<User> users) {

        if (eMail.equals("")) return false;

        for (User user : users
        ) {
            if (user.getEMail().equals(eMail)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPasswordValid(String eMail, String password, List<User> users) {

        if (eMail.equals("") || password.equals("")) return false;

        for (User user : users
        ) {
            if (user.getEMail().equals(eMail) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void onRegisterClicked() {
        registerTrigger.setValue(new EventWrapper<>(true));
    }

}
