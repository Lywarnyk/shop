package com.epam.kuliha.shop.service;

import com.epam.kuliha.shop.bean.RegisterFormBean;
import com.epam.kuliha.shop.service.validator.RegisterFormBeanValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class RegisterFormBeanValidatorTest {

    RegisterFormBeanValidator registerFormBeanValidator;
    RegisterFormBean bean;
    List<String> errorKeys;

    public RegisterFormBeanValidatorTest(List<String> errorKeys, RegisterFormBean bean) {
        this.errorKeys = errorKeys;
        this.bean = bean;
    }

    @Parameters
    public static Collection<Object[]> setUpParams(){
        Object[][] params = new Object[][]{
                {Arrays.asList(), new RegisterFormBean("Test", "Test", "test@rich.com", "asdfasdf", "asdfasdf","captcha", false)},
                {Arrays.asList("first-name"), new RegisterFormBean("test", "Test", "test@rich.com", "asdfasdf", "asdfasdf","captcha", false)},
                {Arrays.asList("first-name"), new RegisterFormBean(null, "Test", "test@rich.com", "asdfasdf", "asdfasdf","captcha", false)},
                {Arrays.asList("last-name"), new RegisterFormBean("Test", "test", "test@rich.com", "asdfasdf", "asdfasdf","captcha", false)},
                {Arrays.asList("last-name"), new RegisterFormBean("Test", null, "test@rich.com", "asdfasdf", "asdfasdf","captcha", false)},
                {Arrays.asList("email"), new RegisterFormBean("Test", "Test", "tes&t@richchchc.com", "asdfasdf", "asdfasdf","captcha",  false)},
                {Arrays.asList("email"), new RegisterFormBean("Test", "Test", null, "asdfasdf", "asdfasdf","captcha",  false)},
                {Arrays.asList("password"), new RegisterFormBean("Test", "Test", "test@rich.com", "asdf asdf", "asdf asdf","captcha",  false)},
                {Arrays.asList("password"), new RegisterFormBean("Test", "Test", "test@rich.com", null, "asdf asdf","captcha",  false)},
                {Arrays.asList("re-password"), new RegisterFormBean("Test", "Test", "test@rich.com", "asdfasdf", "asdf","captcha",  false)},
                {Arrays.asList("re-password"), new RegisterFormBean("Test", "Test", "test@rich.com", "asdfasdf", null,"captcha",  false)},
                {Arrays.asList("captcha"), new RegisterFormBean("Test", "Test", "test@rich.com", "asdfasdf", "asdfasdf",null,  false)},
                {Arrays.asList("first-name", "last-name"), new RegisterFormBean("test", "test", "test@rich.com", "asdfasdf", "asdfasdf","captcha", false)},
                {Arrays.asList("first-name", "last-name", "email"), new RegisterFormBean("test", "test", "te st@rich.com", "asdfasdf", "asdfasdf","captcha", false)},
                {Arrays.asList("first-name", "last-name", "email","password"), new RegisterFormBean("test", "test", "te st@rich.com", "asdf asdf", "asdf asdf","captcha", false)},
                {Arrays.asList("first-name", "last-name", "email","password", "re-password"), new RegisterFormBean("test", "test", "te st@rich.com", "asdf asdasdf", "asdf asdf","captcha", false)},
                {Arrays.asList("first-name", "last-name", "email","password", "re-password", "captcha"), new RegisterFormBean()}
        };
        return Arrays.asList(params);
    }

    @Before
    public void setUp() throws Exception {
        registerFormBeanValidator = new RegisterFormBeanValidator();
    }

    @Test
    public void validate() {
        Map<String, String> errors = registerFormBeanValidator.validate(bean);

        assertEquals(errorKeys.size(), errors.size());
        assertTrue(errors.keySet().containsAll(errorKeys));
    }
}