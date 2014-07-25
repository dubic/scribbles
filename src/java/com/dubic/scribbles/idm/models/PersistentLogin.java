/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.idm.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author dubic
 * @since idm 1.0.0
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogin implements Serializable {

    private String username;
    private String series;
    private String token;
    private Date last_used;

    @Column(name = "username", length = 64, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "series", length = 64)
    @Id
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Column(name = "token", length = 64, nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "last_used", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getLast_used() {
        return last_used;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }
}
