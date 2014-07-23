/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.ui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author dubem
 */
public abstract class AbstractManagedBean {
    
    /**adds a FacesMessage with Error severity
     *
     * @param summary msg details
     * @param comp the clientId or null for global msgs
     */
    public void addErrorMessage(String summary, String comp){
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(comp,new FacesMessage(FacesMessage.SEVERITY_ERROR,summary, null));
    }
    
    /**adds a FacesMessage with Error severity and String formatting ability
     *
     * @param summary
     * @param comp
     * @param args parameters for the formatted message
     */
    public void addFmtErrorMessage(String summary, String comp, Object... args){
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(comp,new FacesMessage(FacesMessage.SEVERITY_ERROR,String.format(summary, args), null));
    }
    
    /**adds a FacesMessage with Info severity
     *
     * @param summary msg details
     * @param comp the clientId or null for global msgs
     */
    public void addInfoMessage(String summary, String comp){
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(comp,new FacesMessage(FacesMessage.SEVERITY_INFO,summary, null));
    }
    
    public void addFmtInfoMessage(String summary, String comp, Object... args){
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(comp,new FacesMessage(FacesMessage.SEVERITY_INFO,String.format(summary, args), null));
    }
    
    /**adds a FacesMessage with Fatal severity
     *
     * @param summary msg details
     * @param comp the clientId or null for global msgs
     */
    public void addFatalMessage(String summary, String comp){
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(comp,new FacesMessage(FacesMessage.SEVERITY_FATAL,summary, null));
    }
}
