package com.microentropy.admin.services;

import com.microentropy.admin.models.CaptchaMore;
import com.microentropy.admin.repositories.CaptchaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-05-04
 */
@Service
public class CaptchaService {
    static final Logger LOGGER = LoggerFactory.getLogger(CaptchaService.class);

    @Value("${upload.captcha.path}")
    String uploadCaptchaPath;

    @Value("${auth.captcha.session.out}")
    Long captchaSessionOut;

    @Autowired
    CaptchaRepository captchaRepository;

    @Transactional
    public void addCaptcha(String challenge, String storageName) {
        captchaRepository.insertCaptcha(challenge, storageName);
    }

    @Transactional
    public Boolean validateChallenge(String challenge) {
        final Boolean exists = captchaRepository.exist(challenge);
        if (exists) {
            captchaRepository.deleteCaptcha(challenge);
        }
        return exists;
    }

    @Transactional
    public void tidyCaptcha() {
        final Date deadline = new Date(System.currentTimeMillis() - captchaSessionOut);
        final List<CaptchaMore> captchaMoreList = captchaRepository.findSessionOut(deadline);
        for (CaptchaMore captchaMore : captchaMoreList) {
            final File captchaFile = Paths.get(uploadCaptchaPath, captchaMore.getStorageName()).toFile();
            if (captchaFile.exists()) {
                try {
                    captchaFile.delete();
                } catch (Exception e) {
                    LOGGER.info("captcha deletion error");
                    continue;
                }
            }
            captchaRepository.deleteCaptcha(captchaMore.getChallenge());
        }
    }
}
