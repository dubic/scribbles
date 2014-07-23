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
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author dubem
 */
public class AdminAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger log = Logger.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
        log.info(String.format("[%s] successfully authenticated", (String) a.getPrincipal()));
        log.info(String.format("coming from [%s]", hsr.getRequestURI()));
        hsr1.sendRedirect("index/dashboard.jsp");
    }

}
