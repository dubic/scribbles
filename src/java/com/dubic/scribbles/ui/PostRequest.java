/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.ui;

import com.dubic.scribbles.faces.Script;
import com.dubic.scribbles.posts.JokeService;
import com.dubic.scribbles.posts.PostException;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**Handles JSF/View layer of all post() request actions.calls spring services and handles responses
 *
 * @author dubem
 */
@ManagedBean
@RequestScoped
public class PostRequest {

    private final Logger log = Logger.getLogger(getClass());
    private String postedMsg;
    private Script script;
    private final WebApplicationContext springContext;

    //<editor-fold defaultstate="collapsed" desc="getter and setters">
    public String getPostedMsg() {
        return postedMsg;
    }

    public void setPostedMsg(String postedMsg) {
        this.postedMsg = postedMsg;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }
//</editor-fold>

    /**
     * Creates a new instance of JokeRequest
     *
     */
    public PostRequest() {
        this.springContext = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
    }

    @PostConstruct
    public void created() {

    }

    public void postJoke() {
        log.debug(String.format("postJoke(%s)", postedMsg));
        try {
            springContext.getBean(JokeService.class).savePost(postedMsg);
        } catch (PersistenceException ex) {
            java.util.logging.Logger.getLogger(PostRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PostException ex) {
            java.util.logging.Logger.getLogger(PostRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConstraintViolationException ex) {
            java.util.logging.Logger.getLogger(PostRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void postComment() {
        log.debug(String.format("postComment(%s)", postedMsg));
    }

    public void like() {
        log.debug(String.format("like(%s)", postedMsg));
    }

    public void dislike() {
        log.debug(String.format("dislike(%s)", postedMsg));
    }

    public void report() {
        log.debug(String.format("report(%s)", postedMsg));
    }

    public void share() {
        log.debug(String.format("share(%s)", postedMsg));
    }

}
