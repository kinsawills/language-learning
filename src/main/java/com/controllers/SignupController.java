package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.model.Facade;
import com.model.Language;
import com.model.LanguageList;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import com.language.App;

public class SignupController implements Initializable {
    private LanguageList languageList = LanguageList.getInstance();

    @FXML
    private ChoiceBox<String> choicebox_language;

    @FXML
    private DatePicker datepicker_birthday;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;
    
    @FXML
    private TextField txt_firstName;

    @FXML
    private TextField txt_lastName;

    @FXML
    private TextField txt_emailAddress;

    @FXML
    private TextField txt_birthday;

    @FXML
    private void Signup(ActionEvent event) throws IOException {
        String firstName = txt_firstName.getText().toString();
        String lastName = txt_lastName.getText().toString();
        String username = txt_username.getText().toString();
        String emailAddress = txt_emailAddress.getText().toString();
        String password = txt_password.getText().toString();
        Date birthday = getBirthdayFromDatePicker();
        Language language = getLanguageFromChoiceBox();

        

        // TODO convert String language to Language language
        
        // TODO convert 

        User newUser = Facade.getInstance().createAccount(firstName, lastName, username, password, birthday,
                emailAddress, language);
        if (newUser != null) {
            App.setRoot("Welcome");
        } else {
            System.out.println("See above error creating user.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get languages
        ArrayList<Language> languages = languageList.getLanguages();
        for (Language language : languages) {
            choicebox_language.getItems().addAll(language.getLanguage());
        }
    }

    //helper methods
    private Date getBirthdayFromDatePicker() {
        LocalDate localDate = datepicker_birthday.getValue();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    private Language getLanguageFromChoiceBox() {
        String languageStr = choicebox_language.getValue();
        Language language = null;
        ArrayList<Language> languages = this.languageList.getLanguages();

        for (Language l : languages) {
            if (l.getLanguage().equals(languageStr)) {
                language = l;
            }
        }

        return language;
    }
}