package com.epam.kuliha.shop.captcha;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.awt.image.BufferedImage;

public class Captcha {

    private BufferedImage image;
    private String value;
    private long creationDate;

    public Captcha(BufferedImage image, String value) {
        this.image = image;
        this.value = value;
        creationDate = System.currentTimeMillis();
    }

    public String getValue() {
        return value;
    }

    public BufferedImage getImage() {
        return image;
    }

    public long getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Captcha captcha1 = (Captcha) o;

        return new EqualsBuilder()
                .append(image, captcha1.image)
                .append(value, captcha1.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(image)
                .append(value)
                .toHashCode();
    }

    public boolean isExpired(long captchaTimeOut) {
        return (System.currentTimeMillis() - creationDate) > captchaTimeOut;
    }
}
