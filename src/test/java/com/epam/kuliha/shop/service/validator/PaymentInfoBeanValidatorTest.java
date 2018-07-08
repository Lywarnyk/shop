package com.epam.kuliha.shop.service.validator;

import com.epam.kuliha.shop.bean.PaymentInfoBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PaymentInfoBeanValidatorTest {

    PaymentInfoBeanValidator validator;
    String description;
    String payType;
    String cardNumber;
    String delivery;

    public PaymentInfoBeanValidatorTest(String description, String payType, String cardNumber, String delivery) {
        this.description = description;
        this.payType = payType;
        this.cardNumber = cardNumber;
        this.delivery = delivery;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> setUpParams(){
        Object[][] params = new Object[][]{
                {null, null, null, null},
                {"any", null, null, null},
                {"any", "cash", null, null},
                {"any", "cash", "1234567898745632", null},
                {"any", "cash", "1234567898745632", "express1234"},
                {"any", "cash1234", "1234567898745632", "express"},
                {"any", "card", "1234567898745632asdf", "express"},
                {"any", "card", "123456789874", "express"},
                {"any", "card", "123456789874111111111", "express"},
                {"any", "card", "validnaya", "express"}

        };
        return Arrays.asList(params);
    }

    @Before
    public void setUp() throws Exception {
        validator = new PaymentInfoBeanValidator();
    }

    @Test
    public void validate_ShouldReturnTrue() {
        assertTrue(validator.isValid(new PaymentInfoBean("card", "1234567898745632", "express", "Some description")));
        assertTrue(validator.isValid(new PaymentInfoBean("cash", null, "pickup", "Some description")));
    }

    @Test
    public void validate_ShouldReturnFalse() {
        assertFalse(validator.isValid(new PaymentInfoBean(payType, cardNumber, delivery, description)));
    }
}