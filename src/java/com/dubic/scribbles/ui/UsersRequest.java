/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.ui;

import com.dubic.scribbles.dto.UserData;
import com.dubic.scribbles.faces.Message;
import com.dubic.scribbles.faces.Script;
import com.dubic.scribbles.idm.models.User;
import com.dubic.scribbles.idm.spi.IdentityService;
import com.dubic.scribbles.idm.spi.IdentityServiceImpl;
import com.dubic.scribbles.idm.spi.InvalidTokenException;
import com.dubic.scribbles.idm.spi.LinkExpiredException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.PersistenceException;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author dubem
 */
@ManagedBean
@RequestScoped
public class UsersRequest extends AbstractManagedBean{

    private final Logger log = Logger.getLogger(getClass());

    private String screenName;
    private String email;
    private String password;
    private String vpassword;
    private String token;
    private boolean pwordTokenGenerated;
    private Message msg;
    private Script loginScript;
    @ManagedProperty("#{identityService}")
    private IdentityService identityService;
    @ManagedProperty(value = "#{authManager}")
    private AuthenticationManager authenticationManager = null;
    @ManagedProperty(value = "#{userSession}")
    private UserSession session = null;
    

    //<editor-fold defaultstate="collapsed" desc="getter setters">
    
    
    public boolean isPwordTokenGenerated() {
        return pwordTokenGenerated;
    }

    public void setPwordTokenGenerated(boolean pwordTokenGenerated) {
        this.pwordTokenGenerated = pwordTokenGenerated;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public UserSession getSession() {
        return session;
    }

    public void setSession(UserSession session) {
        this.session = session;
    }
    
    public Script getLoginScript() {
        return loginScript;
    }

    public void setLoginScript(Script loginScript) {
        this.loginScript = loginScript;
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
    
    

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVpassword() {
        return vpassword;
    }

    public void setVpassword(String vpassword) {
        this.vpassword = vpassword;
    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
//</editor-fold>

    /**
     * Creates a new instance of UsersRequest
     */
    public UsersRequest() {

    }

    public void register() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (!password.equalsIgnoreCase(vpassword)) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match", null));
            fc.validationFailed();
            return;
        }
        UserData u = new UserData(screenName, email, password);
        try {
            User createdUser = identityService.userRegistration(u);
            fc.addMessage(null, new FacesMessage(String.format("Welcome %s,", createdUser.getScreenName())));
            fc.addMessage(null, new FacesMessage("Your registration was successful. Please check your email"));
        } catch (PersistenceException pe) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service unavailable.Try again later", null));
            log.error("register - persistence error", pe);
        } catch (ConstraintViolationException cve) {
            for (ConstraintViolation<?> violation : cve.getConstraintViolations()) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, violation.getMessage(), null));
            }
        } catch (Exception e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service unavailable.Try again later", null));
            log.error("register - service failure", e);

        }

    }

    public void login() {
        log.debug("login attempting...");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(email, password);
            Authentication result = authenticationManager.authenticate(request);
//            SecurityContextHolder.getContext().setAuthentication(result);
            this.session.setAuthenticated(true);
            //dispatch to success
            loginScript.setPayload(Boolean.TRUE.toString());
//            RequestDispatcher dispatcher = ((ServletRequest) ec.getRequest()).getRequestDispatcher("home.jsf");
//            try {
//                dispatcher.forward((ServletRequest) ec.getRequest(), (ServletResponse) ec.getResponse());
//                fc.responseComplete();
//            } catch (ServletException ex) {
//                log.error("Login error : " + ex.getMessage(), ex);
//            } catch (IOException ex) {
//                log.error("Login path not found", ex);
//            } catch (Exception ex) {
//                log.error(ex.getMessage(), ex);
//            }
        } catch (AuthenticationException e) {
            if (e instanceof ProviderNotFoundException) {
                addErrorMessage("Email or Password wrong. Check letter casing", null);
            } else if (e instanceof DisabledException) {
                addErrorMessage("Your account has not been activated", null);
            } else if (e instanceof LockedException) {
                addErrorMessage("Your account has been locked", null);
            } else {
                addErrorMessage("Login Error", null);
            }
            log.error(e.getMessage());
        }
    }

    public void logout() {

    }

    public void forgotPassword() {
        try {
            identityService.getChangePwdToken(email);
            addInfoMessage("Enter the token sent to your email", null);
            pwordTokenGenerated = true;
        } catch (AuthenticationException e) {
            addFmtErrorMessage("%s does not exist", null, email);
            pwordTokenGenerated = false;
            log.warn(String.format("Attempting to reset password for non-existing user[%s]", email));
        } catch (PersistenceException e) {
            addErrorMessage("Service Unavailaible", null);
            pwordTokenGenerated = false;
            log.fatal(e.getMessage(), e);
        }
    }
    public void resetPassword() {
        log.debug("reset password");
        FacesContext fc = FacesContext.getCurrentInstance();
        if (!password.equalsIgnoreCase(vpassword)) {
            addErrorMessage("Passwords do not match", null);
            fc.validationFailed();
            return;
        }
        try {
            identityService.changePassword(token, password);
            addInfoMessage("Password reset successful", null);
            pwordTokenGenerated = true;
        } catch (InvalidTokenException ex) {
            addErrorMessage(ex.getMessage(), null);
        } catch (LinkExpiredException ex) {
            addErrorMessage(ex.getMessage(), null);
        }
    }

    public void nameChanged(AjaxBehaviorEvent evt) {
        log.debug("new value - " + ((UIInput) evt.getComponent()).getValue());
//        log.debug("password value - "+password);
    }
    
   

}
