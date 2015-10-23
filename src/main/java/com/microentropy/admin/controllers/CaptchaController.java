package com.microentropy.admin.controllers;

import com.microentropy.admin.constants.MimeTypeConstants;
import com.microentropy.admin.models.Captcha;
import com.microentropy.admin.services.CaptchaService;
import com.microentropy.admin.utils.CaptchaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-05-03
 */
@RestController
public class CaptchaController {
    static final Logger LOGGER = LoggerFactory.getLogger(CaptchaController.class);

    @Value("${upload.captcha.path}")
    String uploadCaptchaPath;

    @Autowired
    CaptchaService captchaService;

    @RequestMapping(method = RequestMethod.POST, value = "/captcha/validate")
    public ResponseEntity<Boolean> validate(@RequestBody Captcha captcha) {
        final Boolean valid = captchaService.validateChallenge(captcha.getChallenge());
        return new ResponseEntity<>(valid, HttpStatus.OK);
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getCaptcha() {
        final HttpHeaders headers = new HttpHeaders();

        final String uniqueName = UUID.randomUUID().toString();
        LOGGER.info("upload path: {}", uploadCaptchaPath);
        final File captchaFile = Paths.get(uploadCaptchaPath, uniqueName).toFile();
        if (!captchaFile.exists()) {
            try {
                captchaFile.createNewFile();
            } catch (IOException e) {
                LOGGER.info("create captcha file error", e);
            }
        }
        final String challenge;
        try {
            challenge = CaptchaUtils.getChallenge(new BufferedOutputStream(new FileOutputStream(captchaFile)));
        } catch (FileNotFoundException e) {
            LOGGER.info("captcha file creation error");
            headers.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

        captchaService.addCaptcha(challenge, uniqueName);

        try {
            InputStreamResource inputStreamResource = new InputStreamResource(new BufferedInputStream(new FileInputStream(captchaFile)));
            headers.setContentLength(captchaFile.length());
            final String fileName = captchaFile.getName() + ".png";
            final String mimeType = MimeTypeConstants.getMimeType(fileName.substring(fileName.lastIndexOf('.') + 1));
            headers.setContentType(MediaType.parseMediaType(mimeType));
            headers.add("Content-Disposition", "inline; filename=" + fileName);
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            LOGGER.error("stored file not found", e);
            headers.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }
}
