/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.auth;

import com.dubic.scribbles.idm.models.User;
import com.dubic.scribbles.idm.spi.IdentityService;
import com.dubic.scribbles.utils.IdmCrypt;
import com.dubic.scribbles.utils.IdmUtils;
import java.util.Date;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author dubem
 */

public class AdminAuthenticationProvider implements AuthenticationProvider {

    private final Logger log = Logger.getLogger(getClass());
    @Inject
    private IdentityService userService;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        log.debug(String.format("authenticating...[%s]",a.getName().toLowerCase()));
        String userId = a.getName().toLowerCase();
        User user = userService.findUserByEmailandPasword(userId, IdmCrypt.encodeMD5(a.getCredentials().toString(), userId));
        if (user != null) {
            if (!user.isActivated()) {
                throw new DisabledException("User account is not activated - " + userId);
            }
            if (user.isLocked()) {
                throw new LockedException("User accountis locked - " + userId);
            }
            
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), a.getCredentials().toString(), IdmUtils.getGrantedAuthorities(user));
            SecurityContextHolder.getContext().setAuthentication(auth);
            try {
                //set last login date
                user.setLastLoginDate(new Date());
                userService.updateUser(user);
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
            return auth;
        } else {
            throw new ProviderNotFoundException("User not found - " + userId);
        }
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
