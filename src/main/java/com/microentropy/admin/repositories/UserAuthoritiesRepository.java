package com.microentropy.admin.repositories;

import com.microentropy.admin.models.Authorities;
import com.microentropy.admin.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-04-28
 */
@Repository
public class UserAuthoritiesRepository {
    static final Logger LOGGER = LoggerFactory.getLogger(UserAuthoritiesRepository.class);

    @Autowired
    JdbcTemplate jdbc;

    public Optional<Users> findUser(String username) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("SELECT CU.ID, CU.MOBILE, CU.EMAIL, CU.PASSWORD_HASH FROM WA_USERS CU WHERE CU.MOBILE = ? OR CU.EMAIL = ?", (resultSet, i) -> new Users(resultSet.getLong("ID"), resultSet.getString("MOBILE"), resultSet.getString("EMAIL"), resultSet.getString("PASSWORD_HASH")), username, username));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Authorities> findAuthorities(Long uid) {
        return jdbc.query("SELECT UID, AUTHORITY FROM WA_ROLES WHERE UID = ?", (resultSet, i) -> new Authorities(resultSet.getLong("UID"), resultSet.getString("AUTHORITY")), uid);
    }

    public Boolean exists(String username) {
        final Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM WA_USERS WHERE MOBILE = ? OR EMAIL = ?", (resultSet, i) -> resultSet.getInt(1), username, username);
        return count > 0;
    }

    public Boolean existsMobile(String mobile) {
        final Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM WA_USERS WHERE MOBILE = ?", (resultSet, i) -> resultSet.getInt(1), mobile);
        return count > 0;
    }

    public Boolean existsEmail(String email) {
        final Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM WA_USERS WHERE EMAIL = ?", (resultSet, i) -> resultSet.getInt(1), email);
        return count > 0;
    }

    public Long insertUser(String mobile, String email, String passwordHash) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement("INSERT INTO WA_USERS (MOBILE, EMAIL, PASSWORD_HASH) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, mobile);
                    ps.setString(2, email);
                    ps.setString(3, passwordHash);
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().longValue();
    }

    public void insertAuthority(Long uid, String authority) {
        jdbc.update("INSERT INTO WA_ROLES (UID, AUTHORITY) VALUES (?,?)", uid, authority);
    }

}
