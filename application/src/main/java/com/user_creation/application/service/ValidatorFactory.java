package com.user_creation.application.service;

import com.user_creation.application.interfaces.Validator;
import com.user_creation.application.model.EmailValidator;
import com.user_creation.application.model.PasswordValidator;
import com.user_creation.application.model.PhoneValidator;
import org.springframework.stereotype.Service;

@Service
public class ValidatorFactory {

    public Validator getValidator(String validatorType)
    {
        if(validatorType == null)
        {
            return null;
        }
        if(validatorType.equalsIgnoreCase("EMAIL")){
            return new EmailValidator();
        }
        else if(validatorType.equalsIgnoreCase("PASSWORD")){
            return new PasswordValidator();
        }
        else if(validatorType.equalsIgnoreCase("PHONE")){
            return new PhoneValidator();
        }

        return null;
    }
}
