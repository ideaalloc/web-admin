package com.microentropy.admin.services;

import com.microentropy.admin.repositories.UserAuthoritiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-04-28
 */
@Service
public class UserService {
    static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserAuthoritiesRepository userAuthoritiesRepository;

    @Transactional
    public void addUser(String mobile, String email, String password, String authority) {
        LOGGER.info("Adding user with mobile {} and email {}", mobile, email);
        final String passwordHash = new BCryptPasswordEncoder().encode(password);
        final Long uid = userAuthoritiesRepository.insertUser(mobile, email, passwordHash);
        userAuthoritiesRepository.insertAuthority(uid, authority);
    }

    @Transactional
    public Boolean exists(String username) {
        return userAuthoritiesRepository.exists(username);
    }
}
