package com.user_creation.application.service;

import com.user_creation.application.interfaces.Validator;
import com.user_creation.application.model.PasswordValidator;
import com.user_creation.application.model.User;
import com.user_creation.application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public ArrayList<String> errorMessage = new ArrayList<>();

    private final UserRepository userRepository;

    private final ValidatorFactory validatorFactory;

    public UserService(UserRepository userRepository, ValidatorFactory validatorFactory) {
        this.userRepository = userRepository;
        this.validatorFactory = validatorFactory;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public void deleteById(long userId){
        userRepository.deleteById(userId);
    }

    public User findById(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
        {
            return user.get();
        }
        return null;
    }

    public boolean validate(User user) throws Exception {
        Validator emailValidator = validatorFactory.getValidator("EMAIL");
        Validator passwordValidator = validatorFactory.getValidator("PASSWORD");
        Validator phoneValidator = validatorFactory.getValidator("PHONE");

        boolean isEmailValid = emailValidator.validate(user.getEmail());
        boolean isPasswordValid = passwordValidator.validate(user.getSlaptazodis());
        boolean isPhoneValid = phoneValidator.validate(user.getTelefonoNumeris());

        String errorMessage = "";
        if(!isEmailValid)
        {
            errorMessage = errorMessage + "wrong email\n";
        }
        if(!isPasswordValid)
        {
            errorMessage = errorMessage + "wrong password\n";
        }
        if(!isPhoneValid)
        {
            errorMessage = errorMessage + "wrong phone number\n";
        }
        if(isEmailValid && isPasswordValid && isPhoneValid)
        {
            return true;
        }
        else {
            throw new Exception(errorMessage);
        }
    }

    public void update(User user) throws Exception {
        if(validate(user))
        {
            userRepository.save(user);
        }
    }

    public User add(User user) throws Exception {
        if(validate(user)) {
            return userRepository.save(user);
        }
            return null;
    }
}
