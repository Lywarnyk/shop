package com.epam.kuliha.shop.service.validator;

import com.epam.kuliha.shop.bean.PaymentInfoBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentInfoBeanValidator {

    private static final int CASH = 0;
    private List<String> expectedPayTypes;
    private List<String> expectedDelivery;

    public PaymentInfoBeanValidator() {
        expectedPayTypes = new ArrayList<>(Arrays.asList("cash","card"));
        expectedDelivery = new ArrayList<>(Arrays.asList("express","pickup"));
    }

    public boolean isValid(PaymentInfoBean bean){
        if(!expectedPayTypes.contains(bean.getPayType()))
            return false;
        if(!expectedDelivery.contains(bean.getDelivery()))
            return false;
        return isCardNumberInvalid(bean.getCardNumber(), bean.getPayType());
    }

    private boolean isCardNumberInvalid(String cardNumber, String payType) {
        if(expectedPayTypes.get(CASH).equals(payType))
            return cardNumber == null || cardNumber.isEmpty();

        Pattern pattern = Pattern.compile("^[0-9]{16}$");
        Matcher matcher = pattern.matcher(cardNumber);
        return matcher.find();
    }


}
