package com.microentropy.admin.services;

import com.microentropy.admin.models.Authorities;
import com.microentropy.admin.models.Users;
import com.microentropy.admin.repositories.UserAuthoritiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
public class AuthService implements UserDetailsService, Serializable {
    static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    UserAuthoritiesRepository userAuthoritiesRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("you have got username {} to authenticate", username);
        final Optional<Users> user = userAuthoritiesRepository.findUser(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
        }
        final List<Authorities> authorities = userAuthoritiesRepository.findAuthorities(user.get().getId());
        if (authorities.stream().filter(auth -> "ADMIN".equals(auth.getAuthority())).count() == 0) {
            throw new UsernameNotFoundException("Admin system only allows ADMIN role to access");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return authorities.stream().map(auth -> (GrantedAuthority) () -> auth.getAuthority()).collect(Collectors.toList());
            }

            @Override
            public String getPassword() {
                return user.get().getPasswordHash();
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.get().getIsAccountNonExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.get().getIsAccountNonLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.get().getIsCredentialsNonExpired();
            }

            @Override
            public boolean isEnabled() {
                return user.get().getIsEnabled();
            }
        };
    }

}
