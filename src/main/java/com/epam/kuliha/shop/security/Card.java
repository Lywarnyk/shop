package com.epam.kuliha.shop.security;

import org.apache.commons.lang3.StringUtils;

public class Card {

    public static String hideCardNumber(String cardNumber){
        if(cardNumber == null || cardNumber.isEmpty())
            return "";
        return StringUtils.join(cardNumber.substring(0, 2), "*********", cardNumber.substring(12));
    }
}
