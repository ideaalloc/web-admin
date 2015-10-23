package com.microentropy.admin.repositories;

import com.microentropy.admin.models.CaptchaMore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
@Repository
public class CaptchaRepository {
    static final Logger LOGGER = LoggerFactory.getLogger(CaptchaRepository.class);

    @Autowired
    JdbcTemplate jdbc;

    public void insertCaptcha(String challenge, String storageName) {
        jdbc.update("INSERT INTO WA_CAPTCHA (CHALLENGE, STORAGE_NAME) VALUES (?,?)", challenge, storageName);
    }

    public Boolean exist(String challenge) {
        final Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM WA_CAPTCHA WHERE CHALLENGE = ?", (resultSet, i) -> resultSet.getInt(1), challenge);
        return count > 0;
    }

    public void deleteCaptcha(String challenge) {
        jdbc.update("DELETE FROM WA_CAPTCHA WHERE CHALLENGE = ?", challenge);
    }

    public void deleteCaptcha(Date deadline) {
        jdbc.update("DELETE FROM WA_CAPTCHA WHERE CREATE_TIME < ?", deadline);
    }

    public List<CaptchaMore> findSessionOut(Date deadline) {
        return jdbc.query("SELECT CHALLENGE, STORAGE_NAME FROM WA_CAPTCHA WHERE CREATE_TIME < ?", (resultSet, i) -> new CaptchaMore(resultSet.getString("CHALLENGE"), resultSet.getString("STORAGE_NAME")), deadline);
    }
}
