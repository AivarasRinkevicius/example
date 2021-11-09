package com.user_creation.application.model;

import My_Implementation.Validators.PasswordChecker;
import com.user_creation.application.interfaces.Validator;

import java.util.ArrayList;

public class PasswordValidator implements Validator {

    @Override
    public boolean validate(String input) {
        ArrayList<Character> specialSymbols = new ArrayList<>();
        specialSymbols.add('?');
        specialSymbols.add('%');
        PasswordChecker.setMinPasswordLength(9);
        PasswordChecker.setSpecialSymbolList(specialSymbols);
        return PasswordChecker.checkPassword(input);
    }
}
