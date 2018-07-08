package com.epam.kuliha.shop.captcha;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaGenerator {

    private static final String FONT_NAME = "Verdana";
    private char[] availableChars;

    public CaptchaGenerator() {
        availableChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy23456789".toCharArray();
    }

    public Captcha generate() {
        String captcha = generateCaptchaValue();
        BufferedImage image = drawCaptchaImage(captcha);
        return new Captcha(image, captcha);
    }

    private BufferedImage drawCaptchaImage(String captcha) {
        BufferedImage image = new BufferedImage(150, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        graphics2D.setColor(getRandomColor());
        graphics2D.fillRect(0, 0, 150, 40);
        Color c = new Color(0.662f, 0.469f, 0.232f);
        GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.black, true);
        graphics2D.setPaint(gp);
        Font font = new Font(FONT_NAME, Font.CENTER_BASELINE, 30);
        graphics2D.setFont(font);
        graphics2D.drawString(captcha, 5, 35);
        graphics2D.dispose();
        return image;
    }

    private Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return Color.getHSBColor(r, g, b);
    }

    private String generateCaptchaValue() {
        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (availableChars.length - 1));
            char characterToShow = availableChars[randomIndex];
            finalString.append(characterToShow);
        }
        return finalString.toString();
    }
}
