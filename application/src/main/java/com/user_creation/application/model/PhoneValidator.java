package com.user_creation.application.model;

import My_Implementation.Validators.CountryRule;
import com.user_creation.application.interfaces.Validator;

public class PhoneValidator implements Validator {
    static String returnedPhoneNumber;

    @Override
    public boolean validate(String input) {
        My_Implementation.Validators.PhoneValidator.addNewCountryRule(new CountryRule("LT", 12, "+370"));
        returnedPhoneNumber = My_Implementation.Validators.PhoneValidator.validatePhoneNumber("LT", input);
        System.out.println(returnedPhoneNumber);
        return returnedPhoneNumber != null;
    }
}
