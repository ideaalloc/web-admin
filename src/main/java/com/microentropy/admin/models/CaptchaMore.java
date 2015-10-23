package com.microentropy.admin.models;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-05-08
 */
public class CaptchaMore {
    String challenge;
    String storageName;

    public CaptchaMore() {
    }

    public CaptchaMore(String challenge, String storageName) {
        this.challenge = challenge;
        this.storageName = storageName;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    @Override
    public String toString() {
        return "CaptchaMore{" +
                "challenge='" + challenge + '\'' +
                ", storageName='" + storageName + '\'' +
                '}';
    }
}
