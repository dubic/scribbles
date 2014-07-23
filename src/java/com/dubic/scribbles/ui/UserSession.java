/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dubem
 */
@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession {
private boolean authenticated = false;

//<editor-fold defaultstate="collapsed" desc="getter setters">
public boolean isAuthenticated() {
    return authenticated;
}

public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
}
//</editor-fold>

    /**
     * Creates a new instance of UserSession
     */
    public UserSession() {
    }
    
}
