/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.auth;

import com.dubic.scribbles.idm.spi.IdentityService;
import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author DUBIC
 */

public class CustomUserDetailsService implements UserDetailsService {

    @Inject
    private IdentityService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userService.findUserByEmail(username);
    }

}
