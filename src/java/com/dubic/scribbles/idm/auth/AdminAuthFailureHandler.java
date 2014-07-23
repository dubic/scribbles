/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

/**
 *
 * @author dubem
 */
public class AdminAuthFailureHandler implements AuthenticationFailureHandler {

    private final Logger log = Logger.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
        log.info("AUTH failed redirecting : " + ae.getClass());
        if (ae instanceof ProviderNotFoundException) {
            hsr.setAttribute("error", "Email or Password wrong. Check letter casing");
            hsr.getRequestDispatcher("login.jsp").forward(hsr, hsr1);
        } else if (ae instanceof DisabledException) {
            hsr.setAttribute("error", "Account not active.Please contact a ");
            hsr.getRequestDispatcher("login.jsp").forward(hsr, hsr1);
        } else if (ae instanceof LockedException) {
            hsr.setAttribute("error", "Account locked");
            hsr.getRequestDispatcher("login.jsp").forward(hsr, hsr1);
        } else if (ae instanceof SessionAuthenticationException) {
            hsr.setAttribute("error", "Session Invalid");
//            SecurityContextHolder.getContext().
            hsr.getRequestDispatcher("login.jsp").forward(hsr, hsr1);
        } else {
            hsr.getRequestDispatcher("login.jsp").forward(hsr, hsr1);
        }
    }

}
