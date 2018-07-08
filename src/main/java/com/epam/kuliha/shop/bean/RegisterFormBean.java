package com.epam.kuliha.shop.bean;

import com.epam.kuliha.shop.constant.Constant;
import com.epam.kuliha.shop.entity.User;
import com.epam.kuliha.shop.security.Password;
import com.epam.kuliha.shop.security.Role;

import javax.servlet.http.HttpServletRequest;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RegisterFormBean {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String retypedPassword;
    private Boolean mailing;
    private String captcha;

    public RegisterFormBean() {
    }

    public RegisterFormBean(String firstName, String lastName, String email, String password, String retypedPassword, String captcha, Boolean mailing) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.retypedPassword = retypedPassword;
        this.captcha = captcha;
        this.mailing = mailing;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypedPassword() {
        return retypedPassword;
    }

    public void setRetypedPassword(String retypedPassword) {
        this.retypedPassword = retypedPassword;
    }

    public Boolean isMailing() {
        return mailing;
    }

    public void setMailing(Boolean mailing) {
        this.mailing = mailing;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public User toUser(){
        return new User(firstName, lastName, email, Password.hash(password), mailing, Role.CLIENT);
    }

    public static RegisterFormBean from(HttpServletRequest req) {
        RegisterFormBean bean = new RegisterFormBean();
        bean.setFirstName(req.getParameter(Constant.RegisterFormFields.FIRST_NAME));
        bean.setLastName(req.getParameter(Constant.RegisterFormFields.LAST_NAME));
        bean.setEmail(req.getParameter(Constant.RegisterFormFields.EMAIL));
        bean.setPassword(req.getParameter(Constant.RegisterFormFields.PASSWORD));
        bean.setRetypedPassword(req.getParameter(Constant.RegisterFormFields.RE_PASSWORD));
        bean.setCaptcha(req.getParameter(Constant.RegisterFormFields.CAPTCHA));
        bean.setMailing(Boolean.parseBoolean(req.getParameter(Constant.RegisterFormFields.MAILING)));
        return bean;
    }

    public void resetPasswords() {
        password = EMPTY;
        retypedPassword = EMPTY;
    }
}
