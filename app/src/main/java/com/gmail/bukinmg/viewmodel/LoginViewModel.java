package com.gmail.bukinmg.viewmodel;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.Repository;
import com.gmail.bukinmg.utill.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private Repository repository;
    private List<User> users;


    public MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> eMail = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public MutableLiveData<Boolean> mainTrigger = new MutableLiveData<>();
    public MutableLiveData<Boolean> registerTrigger = new MutableLiveData<>();

    @Inject
    public LoginViewModel(Repository repository) {
        this.repository = repository;
        users = repository.getUsers();
    }

    public void onLoginClicked() {

        String userEmail = eMail.getValue();
        String userPassword = password.getValue();

        if (userEmail == null) {
            errorEmail.setValue("Enter eMail");
        } else if (!isEmailValid(userEmail, users)) {
            errorEmail.setValue("Enter valid eMail");
        } else {
            errorEmail.setValue(null);
        }

        if (userPassword == null) {
            errorPassword.setValue("Enter password");
        } else if (!isPasswordValid(userEmail, userPassword, users)) {
            errorPassword.setValue("Enter valid password");
        } else {
            errorPassword.setValue(null);
        }

        if (isPasswordValid(userEmail, userPassword, users) && isEmailValid(userEmail, users)) {
            mainTrigger.setValue(true);
        }
    }

    private boolean isEmailValid(String eMail, List<User> users) {

        for (User user : users
        ) {
            if (user.getEMail().equals(eMail)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPasswordValid(String eMail, String password, List<User> users) {

        for (User user : users
        ) {
            if (user.getEMail().equals(eMail) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void onRegisterClicked() {
        registerTrigger.setValue(true);
    }

    @BindingAdapter("errorText")
    public static void setErrorText(TextInputLayout textInputLayout, String errorText) {
            if (errorText != null) {
                textInputLayout.setError(errorText);
            } else {
                textInputLayout.setError(null);
            }
    }

}
