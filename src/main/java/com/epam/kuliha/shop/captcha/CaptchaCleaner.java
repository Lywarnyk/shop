package com.epam.kuliha.shop.captcha;

import org.apache.log4j.Logger;

import java.util.Map;

public class CaptchaCleaner {

    private static final Logger LOG = Logger.getLogger(CaptchaCleaner.class);

    private Map<Long, Captcha> captchaMap;
    private long timeOut;

    public CaptchaCleaner(Map<Long, Captcha> captchaMap, long timeOut) {
        this.captchaMap = captchaMap;
        this.timeOut = timeOut;
        Thread cleaner = new CleanerThread();
        cleaner.setDaemon(true);
        cleaner.start();
    }

    private class CleanerThread extends Thread {

        @Override
        public void run() {
            while (!isInterrupted()) {
                captchaMap.entrySet().stream()
                        .filter(entry -> entry.getValue().isExpired(timeOut))
                        .map(Map.Entry::getKey)
                        .forEach(captchaMap::remove);
                try {
                    sleep(timeOut / 3);
                } catch (InterruptedException e) {
                    interrupt();
                    LOG.error(e);
                }
            }
        }
    }
}
