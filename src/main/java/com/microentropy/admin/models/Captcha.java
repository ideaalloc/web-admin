package com.microentropy.admin.models;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-05-04
 */
public class Captcha {
    String challenge;

    public Captcha() {
    }

    public Captcha(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "challenge='" + challenge + '\'' +
                '}';
    }
}
