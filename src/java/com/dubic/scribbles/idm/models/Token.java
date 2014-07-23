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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author dubic
 * @since idm 1.0.0
 */
@Entity
@Table(name = "idm_tokens")
public class Token implements Serializable {

    public static final String USER_ACTIVATION_TOKEN = "USER ACTIVATION";
    public static final String PASSWORD_RESET_TOKEN = "PASSWORD RESET";

    private Long id;
    private User user;
    private String token;
    private String type;
    private boolean active = true;
    private Date createDt = new Date();
    private Date usedDt;
    private Date expiryDt;

    public Token(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public Token() {
    }

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "no user assigned to token")
    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotEmpty(message = "Token string must not be null or empty")
    @Column(name = "token", nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "create_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    @Column(name = "used_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getUsedDt() {
        return usedDt;
    }

    public void setUsedDt(Date usedDt) {
        this.usedDt = usedDt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Future(message = "Token has expired")
    @Column(name = "expiry_dt")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getExpiryDt() {
        return expiryDt;
    }

    public void setExpiryDt(Date expiryDt) {
        this.expiryDt = expiryDt;
    }

}
