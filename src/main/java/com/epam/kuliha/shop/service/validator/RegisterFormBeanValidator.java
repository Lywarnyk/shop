package com.epam.kuliha.shop.service.validator;

import com.epam.kuliha.shop.bean.RegisterFormBean;
import com.epam.kuliha.shop.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFormBeanValidator {

    private String usernamePattern;
    private String emailPattern;
    private String passPattern;

    public RegisterFormBeanValidator() {
        usernamePattern = "[A-Z][a-zA-Z0-9]{3,20}";
        emailPattern = "[a-zA-Z0-9\\-\\.]+@rich.com";
        passPattern = "[a-zA-Z0-9!\\-\\&\\?\\.]{4,20}";
    }

    public Map<String, String> validate(RegisterFormBean bean) {
        Map<String, String> errors = new HashMap<>();
        nullCheck(bean, errors);
        if (!errors.isEmpty())
            return errors;
        validateNonNullFields(bean, errors);
        return errors;
    }

    private void nullCheck(RegisterFormBean bean, Map<String, String> errors) {
        if (bean.getFirstName() == null) {
            errors.put(Constant.RegisterFormFields.FIRST_NAME, "First name is null");
        }
        if (bean.getLastName() == null) {
            errors.put(Constant.RegisterFormFields.LAST_NAME, "Last name is null");
        }
        if (bean.getEmail() == null) {
            errors.put(Constant.RegisterFormFields.EMAIL, "Email name is null");
        }
        if (bean.getPassword() == null) {
            errors.put(Constant.RegisterFormFields.PASSWORD, "Passwords is null");
        }
        if (bean.getRetypedPassword() == null) {
            errors.put(Constant.RegisterFormFields.RE_PASSWORD, "Retyped password is null");
        }
        if (bean.getCaptcha() == null) {
            errors.put(Constant.RegisterFormFields.CAPTCHA, "You did not type symbols");
        }
    }

    private void validateNonNullFields(RegisterFormBean bean, Map<String, String> errors) {
        if (nameIsInvalid(bean.getFirstName())) {
            errors.put(Constant.RegisterFormFields.FIRST_NAME, "First name is invalid");
        }
        if (nameIsInvalid(bean.getLastName())) {
            errors.put(Constant.RegisterFormFields.LAST_NAME, "Last name is invalid");
        }
        if (emailIsInvalid(bean.getEmail())) {
            errors.put(Constant.RegisterFormFields.EMAIL, "Email is invalid");
        }
        if (passwordIsInvalid(bean.getPassword())) {
            errors.put(Constant.RegisterFormFields.PASSWORD, "Password is invalid");
        }
        if (!StringUtils.equals(bean.getPassword(), bean.getRetypedPassword())) {
            errors.put(Constant.RegisterFormFields.RE_PASSWORD, "The passwords do not match.");
        }
    }

    private boolean nameIsInvalid(String name) {
        return !match(name, usernamePattern);
    }

    private boolean emailIsInvalid(String email) {
        return !match(email, emailPattern);
    }

    private boolean passwordIsInvalid(String password) {
        return !match(password, passPattern);
    }

    private boolean match(String name, String usernamePattern) {
        Pattern pattern = Pattern.compile(usernamePattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
