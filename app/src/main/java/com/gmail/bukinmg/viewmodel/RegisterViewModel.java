package com.gmail.bukinmg.viewmodel;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.entity.User;
import com.gmail.bukinmg.model.Repository;
import com.gmail.bukinmg.utility.EventWrapper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    public MutableLiveData<String> errorEmail = new MutableLiveData<>();
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorConfirmPassword = new MutableLiveData<>();
    public MutableLiveData<String> eMail = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> loginTrigger = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public RegisterViewModel(Repository repository) {
        this.repository = repository;
        eMail.postValue("");
        password.postValue("");
        confirmPassword.postValue("");
    }

    @BindingAdapter("errorText")
    public static void setErrorText(TextInputLayout textInputLayout, String errorText) {
        if (errorText != null) {
            textInputLayout.setError(errorText);
        } else {
            textInputLayout.setError(null);
        }
    }

    public void onRegisterClicked() {

        String userEmail = eMail.getValue();
        String userPassword = password.getValue();
        String userConfirmPassword = confirmPassword.getValue();

        List<User> usersList = repository.getAllUsers();

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

        if (isPasswordValid(userPassword, userConfirmPassword) && isEmailValid(userEmail, usersList)) {

            repository.insert(new User(userEmail, userPassword, 1));

            loginTrigger.setValue(new EventWrapper<>(true));
        }
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


}
