package com.tpcmswebadmin.webpages.authentication.validator;

import com.tpcmswebadmin.service.authentication.domain.model.SignInPassCodeModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class AuthenticationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return SignInPassCodeModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignInPassCodeModel signInPassCodeModel = (SignInPassCodeModel) o;
    }
}
