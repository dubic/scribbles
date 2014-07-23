/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.dto;

import java.util.Date;

/**DTO class for user activation parameters
 *
 * @author dubic
 * @since idm 1.0.0
 */
public class UserActivation {

    private Long id;
    private String email;
    private Long created;
    static final long MINUTES = 60*1000;

    public UserActivation(Long id, String email, Long created) {
        this.id = id;
        this.email = email;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public UserActivation() {
    }

    public boolean linkExpired(int expirationPeriod) {
        Long duration = new Date().getTime() - created;// duration in millis of time past
        if(duration > expirationPeriod*MINUTES){
            return true;
        }
        return false;
    }
}
