package com.user_creation.application.model;
import com.user_creation.application.interfaces.Validator;

public class EmailValidator implements Validator {
    @Override
    public boolean validate(String input) {
        return My_Implementation.Validators.EmailValidator.validateEmail(input);
    }
}
