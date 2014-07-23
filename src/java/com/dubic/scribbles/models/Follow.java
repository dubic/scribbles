/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.models;

import com.dubic.scribbles.idm.models.User;
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

/**
 *
 * @author dubem
 */
@Entity
@Table(name = "follow")
public class Follow implements Serializable {

    private Long id;
    private User from;
    private User to;
    private Date created = new Date();

    public Follow() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "user_from")
    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    @OneToOne
    @JoinColumn(name = "user_to")
    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
