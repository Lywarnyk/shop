package com.epam.kuliha.shop.constant;

public class Constant {

    public static final String ERRORS = "errors";
    public static final String BEAN = "bean";
    public static final String CAPTCHA_ID = "captchaId";
    public static final String CAPTCHA_SERVICE = "captchaService";
    public static final String CAPTCHA_TIME_OUT = "captchaTimeOut";
    public static final String CAPTCHA_GENERATOR = "captchaGenerator";
    public static final String USER_SERVICE = "userService";
    public static final String USER = "user";
    public static final String USER_ID = "userId";
    public static final String IMAGE_SERVICE = "imageService";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String TRANSPORT_SERVICE = "transportService";
    public static final String CART = "cart";
    public static final String TRANSPORT_ID = "transportId";
    public static final String TRANSPORT_AMOUNT = "transportAmount";
    public static final String ORDER_SERVICE = "orderService";
    public static final String CURRENT_LOCALE = "currentLocale";

    public static class CaptchaStorage {
        public static final String SESSION = "session";
        public static final String COOKIE = "cookie";
        public static final String FIELD = "field";
    }

    public static class Path {
        public static final String SUCCESS_PAGE = "success.jsp";
        public static final String REGISTER = "register";
        public static final String REGISTER_JSP = "/register.jsp";
        public static final String ALL_OFFERS_JSP = "allOffers.jsp";
        public static final String INDEX_JSP = "index.jsp";
        public static final String ERROR = "error";
        public static final String REFERER = "referer";
        public static final String IMAGES = "/images";
    }

    public static class RegisterFormFields {
        public static final String FIRST_NAME = "first-name";
        public static final String LAST_NAME = "last-name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String RE_PASSWORD = "re-password";
        public static final String MAILING = "mailing";
        public static final String CAPTCHA = "captcha";
        public static final String AVATAR = "avatar";
    }
    public static class Error{
        public static final String LOGIN_OR_PASSWORD_IS_INCORRECT = "Login or password is incorrect!";
    }

    public static class Filter{
        public static final String MIN_PRICE = "min-price";
        public static final String MAX_PRICE = "max-price";
        public static final String CATEGORIES = "categories";
        public static final String NAME = "name";
        public static final String MANUFACTURER = "manufacturer";
        public static final String ITEMS = "items";
        public static final String SORT = "sort";
        public static final String ORDER = "order";
        public static final String PAGE = "page";
    }
}
